package zeroh720.dkeorderentry.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import zeroh720.dkeorderentry.R;
import zeroh720.dkeorderentry.model.TransactionItem;

public class TransactionItemViewHolder  extends RecyclerView.ViewHolder {
    public TextView tv_productCode;
    public TextView tv_productName;
    public TextView tv_qty;
    public ImageButton btn_remove;

    public TransactionItemViewHolder(View itemView) {
        super(itemView);
        tv_productCode = (TextView)itemView.findViewById(R.id.tv_productCode);
        tv_productName = (TextView)itemView.findViewById(R.id.tv_productname);
        tv_qty = (TextView)itemView.findViewById(R.id.tv_qty);
        btn_remove = (ImageButton)itemView.findViewById(R.id.btn_close);
    }
}
