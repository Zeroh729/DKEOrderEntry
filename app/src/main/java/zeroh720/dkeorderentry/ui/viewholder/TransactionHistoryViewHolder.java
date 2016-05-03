package zeroh720.dkeorderentry.ui.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import zeroh720.dkeorderentry.R;

public class TransactionHistoryViewHolder extends RecyclerView.ViewHolder{
    public CardView cardView;
    public LinearLayout ll_transitem;
    public TextView tv_date;
    public TextView tv_date_sub1;
    public TextView tv_date_sub2;
    public Button btn_cancelOrder;
    public ClickListener clickListener;

    public TransactionHistoryViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView)itemView.findViewById(R.id.parent_view);
        ll_transitem = (LinearLayout)itemView.findViewById(R.id.ll_orderentries);
        tv_date = (TextView)itemView.findViewById(R.id.tv_date);
        tv_date_sub1 = (TextView)itemView.findViewById(R.id.tv_date_sub1);
        tv_date_sub2 = (TextView)itemView.findViewById(R.id.tv_date_sub2);
        btn_cancelOrder = (Button)itemView.findViewById(R.id.btn_cancelOrder);

        btn_cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.onClick(btn_cancelOrder.getTag().toString());
                }
            }
        });
    }

    public interface ClickListener{
        void onClick(String tag);
    }
}
