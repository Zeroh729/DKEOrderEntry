package zeroh720.dkeorderentry.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import zeroh720.dkeorderentry.R;
import zeroh720.dkeorderentry.adapter.MainViewPagerAdapter;
import zeroh720.dkeorderentry.fragment.HomeFragment;
import zeroh720.dkeorderentry.interfaces.OnCreateViewListener;
import zeroh720.dkeorderentry.value.DbConstants;

public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseRef.addAuthStateListener(authStateListener);

        viewPager = (ViewPager)findViewById(R.id.viewpager);

        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        homeFragment = (HomeFragment) mainViewPagerAdapter.getItem(0);
        homeFragment.setOnCreateViewListener(new OnCreateViewListener() {
            @Override
            public void doneLoading() {
                homeFragment.btn_scanbarcode.setOnClickListener(scanBarcodeListener);
            }
        });

        viewPager.setAdapter(mainViewPagerAdapter);

    }

    private Firebase.AuthStateListener authStateListener = new Firebase.AuthStateListener() {
        @Override
        public void onAuthStateChanged(AuthData authData) {
            if(authData == null){
                goToLoginActivity();
            }
        }
    };

    private void goToLoginActivity(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    View.OnClickListener scanBarcodeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(1);
        }
    };

}
