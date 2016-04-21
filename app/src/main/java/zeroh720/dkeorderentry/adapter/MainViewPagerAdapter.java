package zeroh720.dkeorderentry.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import zeroh720.dkeorderentry.fragment.BarcodeScannerFragment;
import zeroh720.dkeorderentry.fragment.HomeFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter{
    HomeFragment homeFragment;
    BarcodeScannerFragment barcodeScannerFragment;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        homeFragment = new HomeFragment();
        barcodeScannerFragment = new BarcodeScannerFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return homeFragment;
            default:
                return barcodeScannerFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
