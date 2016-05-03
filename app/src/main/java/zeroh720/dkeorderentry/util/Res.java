package zeroh720.dkeorderentry.util;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;

import zeroh720.dkeorderentry.application.DKEOrderEntry;

public class Res {
    private static Context context;
    private static Res instance;

    public static void setContext(Context context1){
        context = context1;
    }

    public static Res get() {
        if(context == null){
            System.err.print("Res context has not been set. Call setContext() in Application class first");
            System.exit(0);
        }
        if(instance == null)
            instance = new Res();
        return instance;
    }

    private Res() {

    }
    public int dimen(@DimenRes int resId) {
        return (int) context.getResources().getDimension(resId);
    }

    public int color(@ColorRes int resId) {
        return context.getResources().getColor(resId);
    }

    public int integer(@IntegerRes int resId) {
        return context.getResources().getInteger(resId);
    }
}
