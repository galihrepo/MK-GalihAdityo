package com.mankind.app.symptom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class SymptomAdapter extends FragmentStatePagerAdapter {

    int numTabs;

    public SymptomAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TellsFragment();
        } else if (position == 1) {
            ListSymptomFragment fragment = new ListSymptomFragment();
            fragment.setMode(ListSymptomAdapter.MODE_SEARCH);
            return fragment;
        } else {
            return new MedicineFragment();
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
