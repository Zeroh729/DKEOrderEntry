package zeroh720.dkeorderentry.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;

import zeroh720.dkeorderentry.util.Res;

public class DKEOrderEntry extends Application {
    public static DKEOrderEntry dkeOrderEntry;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Res.setContext(this);

        dkeOrderEntry = this;
        if(context == null){
            context = getApplicationContext();
        }
    }
}
