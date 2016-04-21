package zeroh720.dkeorderentry.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import zeroh720.dkeorderentry.R;

public class TransactionHistoryViewHolder extends RecyclerView.ViewHolder{
    public TextView tv_productName;
    public TextView tv_productCode;
    public TextView tv_quantity;
    public TextView tv_date;

    public TransactionHistoryViewHolder(View itemView) {
        super(itemView);
        tv_productName = (TextView)itemView.findViewById(R.id.tv_productname);
        tv_productCode = (TextView)itemView.findViewById(R.id.tv_productcode);
        tv_quantity = (TextView)itemView.findViewById(R.id.tv_quantity);
        tv_date = (TextView)itemView.findViewById(R.id.tv_date);
    }
}
