package com.mankind.app.account;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mankind.app.symptom.ListSymptomAdapter;
import com.mankind.app.symptom.ListSymptomFragment;
import com.mankind.app.symptom.MedicineFragment;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class AccountAdapter extends FragmentStatePagerAdapter {

    int numTabs;

    public AccountAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            ListSymptomFragment fragment = new ListSymptomFragment();
            fragment.setMode(ListSymptomAdapter.MODE_LIST);
            return fragment;
        } else if (position == 1) {
            return new MedicineFragment();
        } else {
            return new MedicineFragment();
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}