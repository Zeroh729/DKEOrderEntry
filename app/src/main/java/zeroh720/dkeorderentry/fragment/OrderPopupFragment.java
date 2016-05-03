package zeroh720.dkeorderentry.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import zeroh720.dkeorderentry.R;
import zeroh720.dkeorderentry.interfaces.OnCreateViewListener;
import zeroh720.dkeorderentry.interfaces.OnOrderActionListeners;

public class OrderPopupFragment extends DialogFragment {
    public OnCreateViewListener onCreateViewListener;
    public OnOrderActionListeners callback;
    public TextView tv_productname;
    public EditText et_productCode;
    public EditText et_qty;
    public Button btn_confirm;
    public Button btn_cancel;
    public Button btn_scan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_order, container, false);
        tv_productname = (TextView)v.findViewById(R.id.tv_productname);
        et_productCode = (EditText)v.findViewById(R.id.et_productcode);
        et_qty = (EditText)v.findViewById(R.id.et_qty);
        btn_confirm = (Button)v.findViewById(R.id.btn_confirm);
        btn_cancel = (Button)v.findViewById(R.id.btn_cancel);
        btn_scan = (Button)v.findViewById(R.id.btn_scan);

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onConfirmAddOrderPressed(et_productCode.getText().toString(), Long.parseLong(et_qty.getText().toString()));
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onScanPressed();
                }
            }
        });

        if(onCreateViewListener != null){
            onCreateViewListener.doneLoading();
        }
        return v;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setCancelable(false);
    }

    public void setViewsEnabled(boolean isEnabled){
        try {
            btn_confirm.setEnabled(isEnabled);
            btn_cancel.setEnabled(isEnabled);
            btn_scan.setEnabled(isEnabled);
            et_productCode.setEnabled(isEnabled);
            et_qty.setEnabled(isEnabled);
        }catch (Exception e){

        }
    }
}
