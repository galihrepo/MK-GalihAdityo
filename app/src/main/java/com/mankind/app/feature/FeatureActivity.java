package com.mankind.app.feature;

import android.content.Intent;

import com.mankind.app.R;
import com.mankind.app.base.BaseActivity;
import com.mankind.app.home.HomeActivity;

import butterknife.OnClick;

public class FeatureActivity extends BaseActivity {

    @Override
    protected void initInjector() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feature;
    }

    @Override
    protected void initial() {

    }

    @OnClick(R.id.button_next)
    public void navigateHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

}