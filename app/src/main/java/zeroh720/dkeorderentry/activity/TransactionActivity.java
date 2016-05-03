package zeroh720.dkeorderentry.activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.Date;
import java.util.HashMap;

import zeroh720.dkeorderentry.R;
import zeroh720.dkeorderentry.adapter.MainViewPagerAdapter;
import zeroh720.dkeorderentry.fragment.BarcodeScannerFragment;
import zeroh720.dkeorderentry.fragment.EntryOrderListFragment;
import zeroh720.dkeorderentry.fragment.HomeFragment;
import zeroh720.dkeorderentry.fragment.OrderPopupFragment;
import zeroh720.dkeorderentry.interfaces.OnOrderActionListeners;
import zeroh720.dkeorderentry.interfaces.OnCreateViewListener;
import zeroh720.dkeorderentry.interfaces.OnScanListener;
import zeroh720.dkeorderentry.model.Merchandiser;
import zeroh720.dkeorderentry.model.TransactionHistory;
import zeroh720.dkeorderentry.model.TransactionItem;
import zeroh720.dkeorderentry.ui.viewholder.TransactionHistoryViewHolder;
import zeroh720.dkeorderentry.value.ActivityMode;
import zeroh720.dkeorderentry.value.DbConstants;
import zeroh720.dkeorderentry.value.ExtraConstants;

public class TransactionActivity extends BaseActivity {
    private ViewPager viewPager;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private HomeFragment homeFragment;
    private EntryOrderListFragment orderListFragment;
    private BarcodeScannerFragment barcodeScannerFragment;
    private OrderPopupFragment orderPopupFragment;
    private String barcodeScanned = "";
    private HashMap<String, HashMap<String, Object>> productMap;

    private ActivityMode activityMode;
    private String column;
    private String btnCaption;
    private String titleCaption;
    private String defaultStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMode = (ActivityMode) getIntent().getExtras().get(ExtraConstants.ACTIVITY_MODE);
        switch (activityMode){
            case ADD_ORDER_ENTRY:
                setTitle("Transaction History");
                column = DbConstants.Col_TransHistory;
                btnCaption = "Add Transaction Request";
                titleCaption = "Add Entry Orders";
                defaultStatus = TransactionHistory.StatusStates.pending.toString();
                break;
            case RETURN_PRODUCT:
                setTitle("Product Return History");
                column = DbConstants.Col_ProductReturns;
                btnCaption = "Return Products";
                titleCaption = "Return Products";
                defaultStatus = TransactionHistory.StatusStates.returned.toString();
                break;
        }

        productMap = new HashMap<>();
        firebaseRef.child(DbConstants.Col_Product).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                String prodCode = (String)map.get(DbConstants.Col_Product_Code);
                productMap.put(prodCode, map);
                productMap.get(prodCode).put(DbConstants.Col_Product_Id, dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                toggleSendViewsEnabled(true);
                Toast.makeText(TransactionActivity.this, firebaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        viewPager = (ViewPager)findViewById(R.id.viewpager);

        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        homeFragment = (HomeFragment) mainViewPagerAdapter.getItem(0);
        homeFragment.column = column;
        homeFragment.setOnCreateViewListener(new OnCreateViewListener() {
            @Override
            public void doneLoading() {
                homeFragment.btn_orderentry.setText(btnCaption);
                homeFragment.btn_orderentry.setOnClickListener(orderEntryListener);
                homeFragment.adapter.clickListener = btnCancelClickListener;
            }
        });

        orderListFragment = (EntryOrderListFragment)mainViewPagerAdapter.getItem(1);
        orderListFragment.setOnCreateViewListener(new OnCreateViewListener() {
            @Override
            public void doneLoading() {
                orderListFragment.tv_title.setText(titleCaption);
                orderListFragment.btn_add.setOnClickListener(btnAddEntryOrderListListener);
                orderListFragment.btn_send.setOnClickListener(btnSendEntryOrderListListener);
                orderListFragment.getAdapter().listener = new OnCreateViewListener() {
                    @Override
                    public void doneLoading() {
                        orderListFragment.getAdapter().btnRemoveListener = removeEntryListener;
                    }
                };
                toggleSendViewsEnabled(true);
            }
        });

        barcodeScannerFragment = (BarcodeScannerFragment)mainViewPagerAdapter.getItem(2);
        barcodeScannerFragment.setOnScanListener(onScanListener);

        viewPager.setAdapter(mainViewPagerAdapter);
        viewPager.addOnPageChangeListener(pageChangeListener);
    }

    OnCreateViewListener orderPopupOnCreate = new OnCreateViewListener() {
        @Override
        public void doneLoading() {
            orderPopupFragment.setViewsEnabled(true);
            orderPopupFragment.et_productCode.setText(barcodeScanned);

            if(!barcodeScanned.trim().isEmpty()) {
                orderPopupFragment.et_qty.requestFocus();
                orderPopupFragment.et_qty.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(orderPopupFragment.et_qty, InputMethodManager.SHOW_FORCED);
            }else{
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(orderPopupFragment.et_productCode, InputMethodManager.SHOW_FORCED);
            }
        }
    };

    OnScanListener onScanListener = new OnScanListener() {
        @Override
        public void onScan(final Barcode barcode) {
            if(viewPager.getCurrentItem() != 1) {
                barcodeScanned = barcode.rawValue;
                viewPager.setCurrentItem(1);
                showOrderPopupFragment();
            }
        }
    };

    View.OnClickListener btnAddEntryOrderListListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showOrderPopupFragment();
        }
    };

    TransactionHistoryViewHolder.ClickListener btnCancelClickListener = new TransactionHistoryViewHolder.ClickListener() {
        @Override
        public void onClick(String tag) {
            String key = tag;
            firebaseRef.child(column).child(key).child(DbConstants.Col_Trans_Status).setValue(TransactionHistory.StatusStates.cancelled, new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    homeFragment.adapter.notifyDataSetChanged();
                }
            });
        }
    };

    private void showOrderPopupFragment(){
        orderPopupFragment = new OrderPopupFragment();
        if(!orderPopupFragment.isAdded()) {
            orderPopupFragment.onCreateViewListener = orderPopupOnCreate;
            orderPopupFragment.callback = addOrderEntryCallback;
            orderPopupFragment.show(getSupportFragmentManager(), OrderPopupFragment.class.getName());
        }
    }

    private void toggleSendViewsEnabled(boolean isSending){
        toggleSendViewsEnabled(isSending, false);
    }

    private void toggleSendViewsEnabled(boolean isSending, boolean isDoneSending){
        orderListFragment.btn_add.setEnabled(isSending);
        orderListFragment.btn_send.setEnabled(isSending);

        orderListFragment.getAdapter().setEnabled(isSending);

        if(isSending){
            orderListFragment.progressBar.setVisibility(View.GONE);
        }else{
            orderListFragment.progressBar.setVisibility(View.VISIBLE);
            isAllLoaded = 0;
        }

        if(isDoneSending){
            orderListFragment.items.clear();
            orderListFragment.getAdapter().notifyDataSetChanged();
            orderListFragment.progressBar.setVisibility(View.GONE);
        }
    }

    int isAllLoaded;
    View.OnClickListener btnSendEntryOrderListListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(orderListFragment.items.size() == 0)
                return;

            toggleSendViewsEnabled(false);

            final Firebase firebaseTransHisRef = firebaseRef.child(column);

            String merchandiserName = Merchandiser.getInstance().getName();
            String branch = Merchandiser.getInstance().getBranch();
            String merchandiserId = firebaseRef.getAuth().getUid();

            final long time = new Date().getTime();

            HashMap<String, String> transHistory = new HashMap<>();
            transHistory.put(DbConstants.Col_Merch_Id, merchandiserId);
            transHistory.put(DbConstants.Col_Merch_Name, merchandiserName);
            transHistory.put(DbConstants.Col_Merch_Branch, branch);
            transHistory.put(DbConstants.Col_Trans_Status, defaultStatus);
            transHistory.put(DbConstants.Col_Trans_Time, time + "");

            firebaseTransHisRef.push().setValue(transHistory, new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    for (TransactionItem item : orderListFragment.items) {
                        String productName = item.getProductName();
                        String productCode = item.getProductCode();
                        String quantity = item.getQuantity();

                        HashMap<String, String> transItem = new HashMap<>();
                        transItem.put(DbConstants.Col_Product_Code, productCode);
                        transItem.put(DbConstants.Col_Product_Name, productName);
                        transItem.put(DbConstants.Col_Product_Qty, quantity);
                        firebaseTransHisRef.child(firebase.getKey()).child(DbConstants.Col_Trans_Items).push().setValue(transItem, new Firebase.CompletionListener() {
                            @Override
                            public void onComplete(FirebaseError firebaseError, final Firebase firebase) {
                                firebase.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        final HashMap<String, Object> requestedItem = (HashMap) dataSnapshot.getValue();
                                        String requestedProdCode = requestedItem.get(DbConstants.Col_Product_Code).toString();
                                        if (productMap.containsKey(requestedProdCode)) {
                                            if (++isAllLoaded >= orderListFragment.items.size()) {
                                                toggleSendViewsEnabled(true, true);
                                                Toast.makeText(TransactionActivity.this, "Transaction request sent!", Toast.LENGTH_LONG).show();
                                                return;
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                        toggleSendViewsEnabled(true);
                                        Toast.makeText(TransactionActivity.this, firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                    }
                }

            });

        }
    };

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 2:
                    if(getSupportFragmentManager().findFragmentByTag(OrderPopupFragment.class.getName()) != null)
                        orderPopupFragment.dismiss();
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    View.OnClickListener removeEntryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int dataIndex = (int)v.getTag();
            orderListFragment.items.remove(dataIndex);
            orderListFragment.getAdapter().notifyDataSetChanged();
        }
    };

    View.OnClickListener orderEntryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(1);
        }
    };

    OnOrderActionListeners addOrderEntryCallback = new OnOrderActionListeners() {
        @Override
        public void onConfirmAddOrderPressed(String productCode, long quantity) {
            orderPopupFragment.setViewsEnabled(false);
            checkProductCodeAndQuantity(productCode, quantity);
        }

        @Override
        public void onScanPressed() {
            viewPager.setCurrentItem(2);
        }
    };

    public void checkProductCodeAndQuantity(final String code, final long quantity){
        if (productMap.containsKey(code)) {
            long currentQty = (long)productMap.get(code).get(DbConstants.Col_Product_Qty);

            if(quantity <= currentQty) {
                TransactionItem transItem = new TransactionItem();
                transItem.setProductName(productMap.get(code).get(DbConstants.Col_Product_Name).toString());
                transItem.setProductCode(code);
                transItem.setQuantity(quantity+"");
                orderListFragment.items.add(transItem);
                orderListFragment.getAdapter().notifyDataSetChanged();
                orderPopupFragment.dismiss();
                barcodeScanned = "";
            }else{
                Toast.makeText(TransactionActivity.this, "Insufficient Stocks! Lower the Quantity", Toast.LENGTH_LONG).show();
                orderPopupFragment.setViewsEnabled(true);
            }
        } else {
            Toast.makeText(TransactionActivity.this, "Product Code is not listed!", Toast.LENGTH_LONG).show();
            orderPopupFragment.setViewsEnabled(true);
        }
    }

}