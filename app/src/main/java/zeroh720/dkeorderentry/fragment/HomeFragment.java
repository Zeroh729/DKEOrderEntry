package zeroh720.dkeorderentry.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

import zeroh720.dkeorderentry.R;
import zeroh720.dkeorderentry.adapter.HomeRecyclerViewAdapter;
import zeroh720.dkeorderentry.value.DbConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {
    private Firebase firebaseRef;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    public HomeRecyclerViewAdapter adapter;
    public String column;
    public ProgressBar progressBar;
    public Button btn_orderentry;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        btn_orderentry = (Button) v.findViewById(R.id.btn_scanbarcode);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_transHistory);
        progressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        firebaseRef = new Firebase(DbConstants.FirebaseURL);

        if(column == null)
            column = DbConstants.Col_TransHistory;

        adapter = new HomeRecyclerViewAdapter(getContext(), firebaseRef.child(column));

        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setReverseLayout(true);
        layout.setStackFromEnd(true);
        recyclerView.setLayoutManager(layout);

        recyclerView.setAdapter(adapter);

        toolbar = (Toolbar)v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
