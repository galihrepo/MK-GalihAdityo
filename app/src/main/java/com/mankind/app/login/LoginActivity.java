package com.mankind.app.login;

import android.content.Intent;

import com.mankind.app.R;
import com.mankind.app.base.BaseActivity;
import com.mankind.app.base.BaseApplication;
import com.mankind.app.base.helper.Prefs;
import com.mankind.app.base.view.BaseEditText;
import com.mankind.app.base.view.BaseToast;
import com.mankind.app.feature.FeatureActivity;
import com.mankind.app.home.HomeActivity;
import com.mankind.app.register.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    LoginPresenter presenter = new LoginPresenter();

    @BindView(R.id.et_username)
    BaseEditText etUsername;

    @BindView(R.id.et_password)
    BaseEditText etPassword;


    @Override
    protected void initInjector() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initial() {
        presenter.setView(this);
        presenter.actionInsertMasterSymptom();
    }

    @OnClick(R.id.btn_login)
    public void login() {
        if (etUsername.getText().toString().trim().length() == 0) {
            BaseToast.showMessage(this, "Please fill username");
            return;
        }

        if (etPassword.getText().toString().trim().length() == 0) {
            BaseToast.showMessage(this, "Please fill password");
            return;
        }

        presenter.actionLogin(etUsername.getText().toString().trim(),
                etPassword.getText().toString().trim());
    }

    @OnClick(R.id.btn_register)
    public void register() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void onError(String message) {
        BaseToast.showMessage(this, message);
    }

    @Override
    public void onSuccess(String message) {
        navigateToFeature();
    }

    @Override
    public void navigateToFeature() {
        startActivity(new Intent(this, FeatureActivity.class));
        finish();
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void checkSession() {
        if (Prefs.read(Prefs.LOGIN_AS_USER, "").length() > 0) {
            navigateToHome();
        }
    }
}