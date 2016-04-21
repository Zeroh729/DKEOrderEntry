package zeroh720.dkeorderentry.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.BaseKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;

import org.w3c.dom.Text;

import zeroh720.dkeorderentry.R;

public class OrderActivity extends BaseActivity {
    private TextView tv_productname;
    private TextView tv_productcode;
    private EditText et_qty;
    private Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tv_productname = (TextView)findViewById(R.id.tv_productname);
        tv_productcode = (TextView)findViewById(R.id.tv_productcode);
        et_qty = (EditText)findViewById(R.id.et_qty);
        btn_confirm = (Button)findViewById(R.id.btn_confirm);

        Barcode barcode = getIntent().getParcelableExtra("barcode");
        tv_productname.setText(barcode.rawValue);

        et_qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    btn_confirm.setEnabled(false);
                } else {
                    btn_confirm.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
