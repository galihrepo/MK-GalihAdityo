package com.mankind.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mankind.app.base.helper.Prefs;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by galihadityo on 2017-09-28.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder butterKnife;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        butterKnife = ButterKnife.bind(this);
        Prefs.init(this);
        initInjector();
        initial();
    }

    @Override
    protected void onDestroy() {
        butterKnife.unbind();
        super.onDestroy();
    }

    protected abstract void initInjector();

    protected abstract int getLayoutId();

    protected abstract void initial();

}
