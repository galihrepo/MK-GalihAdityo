package com.mankind.app.symptom;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mankind.app.R;
import com.mankind.app.base.BaseFragment;
import com.mankind.app.base.view.BaseTextView;

import butterknife.BindView;

/**
 * Created by galihadityo on 2017-09-29.
 */

public class SymptomFragment extends BaseFragment {

    @BindView(R.id.tv_fragment)
    BaseTextView tvFragment;

    @BindView(R.id.tab)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_symptom;
    }

    @Override
    protected void initial(View view) {
        setupPager(view);
    }

    private void setupPager(final View view) {
        tabLayout.addTab(tabLayout.newTab().setText("Tells"));
        tabLayout.addTab(tabLayout.newTab().setText("Symptoms"));
        tabLayout.addTab(tabLayout.newTab().setText("Medicine"));

        SymptomAdapter adapter = new SymptomAdapter(getActivity().getSupportFragmentManager(),
                tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    tvFragment.setText("Tells us about your symptom");
                } else if (tab.getPosition() == 1) {
                    tvFragment.setText("Some of symptoms inform you");
                } else {
                    tvFragment.setText("List of Medicine");
                }

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setCurrentItem(1);
    }

}
