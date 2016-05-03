package zeroh720.dkeorderentry.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.google.android.gms.vision.barcode.BarcodeDetector;

import zeroh720.dkeorderentry.fragment.BarcodeScannerFragment;
import zeroh720.dkeorderentry.fragment.EntryOrderListFragment;
import zeroh720.dkeorderentry.fragment.HomeFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter{
    HomeFragment homeFragment;
    EntryOrderListFragment entryOrderListFragment;
    BarcodeScannerFragment barcodeScannerFragment;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                if(homeFragment == null)
                    homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                if(entryOrderListFragment == null)
                    entryOrderListFragment = new EntryOrderListFragment();
                return entryOrderListFragment;
            default:
                if(barcodeScannerFragment == null)
                    barcodeScannerFragment = new BarcodeScannerFragment();
                return barcodeScannerFragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
