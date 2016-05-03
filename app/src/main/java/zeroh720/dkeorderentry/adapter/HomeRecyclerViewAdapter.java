package zeroh720.dkeorderentry.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TwoLineListItem;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import zeroh720.dkeorderentry.R;
import zeroh720.dkeorderentry.interfaces.OnCreateViewListener;
import zeroh720.dkeorderentry.model.TransactionHistory;
import zeroh720.dkeorderentry.model.TransactionItem;
import zeroh720.dkeorderentry.ui.viewholder.TransactionHistoryViewHolder;
import zeroh720.dkeorderentry.util.DateFormatter;
import zeroh720.dkeorderentry.value.DbConstants;

public class HomeRecyclerViewAdapter extends FirebaseRecyclerAdapter<TransactionHistory, TransactionHistoryViewHolder>{
    public OnCreateViewListener listener;
    public TransactionHistoryViewHolder.ClickListener clickListener;
    private Context context;

    public HomeRecyclerViewAdapter(Context context, Query ref) {
        super(TransactionHistory.class, R.layout.row_transhistory, TransactionHistoryViewHolder.class, ref);
        this.context = context;
    }

    @Override
    public TransactionHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TransactionHistoryViewHolder vh = super.onCreateViewHolder(parent, viewType);
        if(listener != null){
            listener.doneLoading();
        }
        return vh;
    }

    @Override
    protected void populateViewHolder(TransactionHistoryViewHolder viewHolder, TransactionHistory transactionHistory, int i) {
        getItem(i).setId(getRef(i).getKey());
        viewHolder.tv_date.setText(DateFormatter.getMonthDayFromLong(transactionHistory.getTime()));
        viewHolder.tv_date_sub1.setText(DateFormatter.getDayOfWeek(transactionHistory.getTime()));// + ", " + DateFormatter.getDaysAfter(dataSnapshot.getTime()));
        viewHolder.tv_date_sub2.setText(DateFormatter.getTime(transactionHistory.getTime()));
        viewHolder.ll_transitem.removeAllViews();
        viewHolder.btn_cancelOrder.setTag(getRef(i).getKey());
        viewHolder.clickListener = clickListener;
        setStateViews(viewHolder.btn_cancelOrder, transactionHistory.getStatus());

        if(transactionHistory.getItems() != null){
            Iterator itemIterator = transactionHistory.getItems().entrySet().iterator();
            while (itemIterator.hasNext()) {
                Map.Entry<String, HashMap> entry = (Map.Entry) itemIterator.next();
                if (transactionHistory.getItems().containsKey(entry.getKey())) {
                    TransactionItem transItem = new TransactionItem();
                    transItem.setQuantity(entry.getValue().get(DbConstants.Col_Product_Qty).toString());
                    transItem.setProductCode(entry.getValue().get(DbConstants.Col_Product_Code).toString());
                    transItem.setProductName(entry.getValue().get(DbConstants.Col_Product_Name).toString());

                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    TwoLineListItem row = (TwoLineListItem) inflater.inflate(android.R.layout.simple_list_item_2, null);
                    row.getText1().setText(transItem.getProductName());
                    row.getText2().setText("Code: " + transItem.getProductCode() + "\nQuantity: " + transItem.getQuantity());
                    row.getText1().setTextColor(Color.parseColor("#272727"));
                    row.getText2().setTextColor(Color.parseColor("#272727"));
                    viewHolder.ll_transitem.addView(row);
                }
            }
        }
    }

    private void setStateViews(Button btn, String status) {
        TransactionHistory.StatusStates orderState = TransactionHistory.StatusStates.valueOf(status);
        btn.setEnabled(false);
        switch(orderState){
            case pending:
                btn.setEnabled(true);
                btn.setText("Cancel Order");
                break;
            case cancelled:
                btn.setText("Cancelled");
                break;
            case deliveryCancelled:
                btn.setText("Delivery Cancelled");
                break;
            case deliveryConfirmed:
                btn.setText("Delivery Confirmed");
                break;
            case returned:
                btn.setVisibility(View.GONE);
                break;
        }
    }
}
