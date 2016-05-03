package zeroh720.dkeorderentry.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zeroh720.dkeorderentry.interfaces.OnCreateViewListener;

public class BaseFragment extends Fragment{
    protected OnCreateViewListener onCreateViewListener;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(onCreateViewListener != null){
            onCreateViewListener.doneLoading();
        }
    }

    public void setOnCreateViewListener(OnCreateViewListener listener){
        onCreateViewListener = listener;
    }
}
