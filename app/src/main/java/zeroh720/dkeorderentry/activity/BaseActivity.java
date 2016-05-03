package zeroh720.dkeorderentry.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;

import zeroh720.dkeorderentry.model.Merchandiser;
import zeroh720.dkeorderentry.value.DbConstants;

public class BaseActivity extends AppCompatActivity{
    protected Firebase firebaseRef;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseRef = new Firebase(DbConstants.FirebaseURL);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");

    }

    public void showProgress(){
        progressDialog.show();
    }

    public void hideProgress(){
        progressDialog.dismiss();
    }

    public void updateMerchandiserViews(){};

}
