package zeroh720.dkeorderentry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import zeroh720.dkeorderentry.R;
import zeroh720.dkeorderentry.interfaces.OnCreateViewListener;
import zeroh720.dkeorderentry.model.TransactionItem;
import zeroh720.dkeorderentry.ui.viewholder.TransactionItemViewHolder;

public class EntryOrderAdapter extends RecyclerView.Adapter<TransactionItemViewHolder>{
    public OnCreateViewListener listener;
    private LayoutInflater inflater;
    private ArrayList<TransactionItem> data;
    public View.OnClickListener btnRemoveListener;
    public boolean isEnabled;

    public EntryOrderAdapter(Context context, ArrayList<TransactionItem> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public TransactionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_entryorder, parent, false);
        TransactionItemViewHolder holder = new TransactionItemViewHolder(view);
        if(listener != null){
            listener.doneLoading();
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(TransactionItemViewHolder holder, int position) {
        holder.tv_productCode.setText(data.get(position).getProductCode());
        holder.tv_productName.setText(data.get(position).getProductName());
        holder.tv_qty.setText("Qty : " + data.get(position).getQuantity());
        holder.btn_remove.setTag(position);
        holder.btn_remove.setEnabled(isEnabled);
        holder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnRemoveListener != null && isEnabled){
                    btnRemoveListener.onClick(v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setEnabled(boolean isEnabled){
        this.isEnabled = isEnabled;
    }
}
