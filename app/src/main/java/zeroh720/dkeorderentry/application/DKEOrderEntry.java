package zeroh720.dkeorderentry.application;

import android.app.Application;
import android.content.Context;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;

public class DKEOrderEntry extends Application {
    public static DKEOrderEntry dkeOrderEntry;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        dkeOrderEntry = this;
        if(context == null){
            context = getApplicationContext();
        }
    }

}
