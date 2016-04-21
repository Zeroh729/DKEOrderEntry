package zeroh720.dkeorderentry.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.Firebase;

import zeroh720.dkeorderentry.value.DbConstants;

public class BaseActivity extends AppCompatActivity{
    protected Firebase firebaseRef;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseRef = new Firebase(DbConstants.FirebaseURL);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");

        super.onCreate(savedInstanceState);
    }

    public void showProgress(){
        progressDialog.show();
    }

    public void hideProgress(){
        progressDialog.dismiss();
    }
}
