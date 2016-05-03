package zeroh720.dkeorderentry.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import zeroh720.dkeorderentry.R;
import zeroh720.dkeorderentry.adapter.EntryOrderAdapter;
import zeroh720.dkeorderentry.interfaces.OnCreateViewListener;
import zeroh720.dkeorderentry.model.TransactionItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntryOrderListFragment extends BaseFragment {
    private RecyclerView rv_entryOrderList;
    private EntryOrderAdapter adapter;
    public TextView tv_title;
    public ProgressBar progressBar;
    public ArrayList<TransactionItem> items;
    public FloatingActionButton btn_add;
    public Button btn_send;

    public EntryOrderListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_entry_order_list, container, false);
        tv_title = (TextView)v.findViewById(R.id.tv_label_addEntryOrders);
        rv_entryOrderList = (RecyclerView)v.findViewById(R.id.rv_orderList);
        btn_add = (FloatingActionButton)v.findViewById(R.id.btn_addentry);
        btn_send = (Button)v.findViewById(R.id.btn_send);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);

        items = new ArrayList<>();

        adapter = new EntryOrderAdapter(getContext(), items);
        rv_entryOrderList.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_entryOrderList.setAdapter(adapter);

        return v;
    }

    public EntryOrderAdapter getAdapter() {
        return adapter;
    }

}
