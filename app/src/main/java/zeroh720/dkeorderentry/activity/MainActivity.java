package zeroh720.dkeorderentry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;

import zeroh720.dkeorderentry.R;
import zeroh720.dkeorderentry.model.Merchandiser;
import zeroh720.dkeorderentry.value.ActivityMode;
import zeroh720.dkeorderentry.value.DbConstants;
import zeroh720.dkeorderentry.value.ExtraConstants;

public class MainActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView tv_greetings;
    private TextView tv_branch;
    private Button btn_addOrder;
    private Button btn_returnProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        firebaseRef.addAuthStateListener(authStateListener);

        tv_greetings = (TextView)findViewById(R.id.tv_greeting);
        tv_branch = (TextView)findViewById(R.id.tv_branch);
        btn_addOrder = (Button)findViewById(R.id.btn_orderEntry);
        btn_returnProduct = (Button)findViewById(R.id.btn_returnProduct);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        btn_addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddOrderActivity();
            }
        });

        btn_returnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToReturnProductActivity();
            }
        });
    }

    @Override
    public void updateMerchandiserViews() {
        tv_greetings.setText("Hi " + Merchandiser.getInstance().getName() + "!");
        tv_branch.setText("You're located at " + Merchandiser.getInstance().getBranch());
    }

    private Firebase.AuthStateListener authStateListener = new Firebase.AuthStateListener() {
        @Override
        public void onAuthStateChanged(AuthData authData) {
            if(authData == null){
                goToLoginActivity();
            }else{
                getMerchandiserDetails();
            }
        }
    };

    private void goToLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void getMerchandiserDetails(){
        firebaseRef.child(DbConstants.Col_Merchandiser).child(firebaseRef.getAuth().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, String> data = ((HashMap<String, String>) dataSnapshot.getValue());
                Merchandiser.getInstance().setName(data.get(DbConstants.Col_Merch_Name));
                Merchandiser.getInstance().setBranch(data.get(DbConstants.Col_Merch_Branch));
                updateMerchandiserViews();
                return;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void goToAddOrderActivity(){
        Intent intent = new Intent(this, TransactionActivity.class);
        intent.putExtra(ExtraConstants.ACTIVITY_MODE, ActivityMode.ADD_ORDER_ENTRY);
        startActivity(intent);
    }

    private void goToReturnProductActivity(){
        Intent intent = new Intent(this, TransactionActivity.class);
        intent.putExtra(ExtraConstants.ACTIVITY_MODE, ActivityMode.RETURN_PRODUCT);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_logout:
                firebaseRef.unauth();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
