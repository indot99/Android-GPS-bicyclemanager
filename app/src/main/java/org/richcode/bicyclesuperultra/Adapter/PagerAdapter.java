package org.richcode.bicyclesuperultra.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.richcode.bicyclesuperultra.FragmentGPS;
import org.richcode.bicyclesuperultra.FragmentSell;
import org.richcode.bicyclesuperultra.FragmentTip;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                FragmentGPS tab1 = new FragmentGPS();
                return tab1;
            case 1:
                FragmentTip tab2 = new FragmentTip();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
