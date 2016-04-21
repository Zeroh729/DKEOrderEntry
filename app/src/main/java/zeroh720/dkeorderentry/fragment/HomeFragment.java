package zeroh720.dkeorderentry.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

import zeroh720.dkeorderentry.R;
import zeroh720.dkeorderentry.activity.EditActivity;
import zeroh720.dkeorderentry.activity.LoginActivity;
import zeroh720.dkeorderentry.activity.MainActivity;
import zeroh720.dkeorderentry.interfaces.OnCreateViewListener;
import zeroh720.dkeorderentry.model.TransactionHistory;
import zeroh720.dkeorderentry.ui.viewholder.TransactionHistoryViewHolder;
import zeroh720.dkeorderentry.value.DbConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private Firebase firebaseRef;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    public Button btn_scanbarcode;
    private OnCreateViewListener onCreateViewListener;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        btn_scanbarcode = (Button) v.findViewById(R.id.btn_scanbarcode);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_transHistory);

        firebaseRef = new Firebase(DbConstants.FirebaseURL);

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TransactionHistory, TransactionHistoryViewHolder>(TransactionHistory.class, R.layout.row_transhistory, TransactionHistoryViewHolder.class, firebaseRef.child(DbConstants.Col_TransHistory)) {
            @Override
            protected void populateViewHolder(TransactionHistoryViewHolder view, TransactionHistory data, int i) {
                view.tv_productName.setText(data.getProductName());
                view.tv_productCode.setText(data.getProductCode());
                view.tv_date.setText(data.getTransDate());
                view.tv_quantity.setText(data.getQuantity());
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

        toolbar = (Toolbar)v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        if(onCreateViewListener != null) {
            onCreateViewListener.doneLoading();
        }
        return v;
    }

    public void setOnCreateViewListener(OnCreateViewListener onCreateViewListener){
        this.onCreateViewListener = onCreateViewListener;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_editprofile:
                goToLoginActivity();
                return true;
            case R.id.item_logout:
                firebaseRef.unauth();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToLoginActivity(){
        Intent intent = new Intent(getContext(), EditActivity.class);
        startActivity(intent);
    }


}
