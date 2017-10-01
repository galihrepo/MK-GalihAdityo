package com.mankind.app.register;

import com.mankind.app.R;
import com.mankind.app.base.BaseActivity;
import com.mankind.app.base.view.BaseEditText;
import com.mankind.app.base.view.BaseToast;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    RegisterPresenter presenter = new RegisterPresenter();

    @BindView(R.id.et_full_name)
    BaseEditText etFullName;

    @BindView(R.id.et_username)
    BaseEditText etUsername;

    @BindView(R.id.et_password)
    BaseEditText etPassword;

    @Override
    protected void initInjector() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initial() {
        presenter.setView(this);
    }

    @OnClick(R.id.btn_register)
    public void register() {
        if (etFullName.getText().toString().trim().length() == 0) {
            BaseToast.showMessage(this, "Please fill fullname");
            return;
        }

        if (etUsername.getText().toString().trim().length() == 0) {
            BaseToast.showMessage(this, "Please fill username");
            return;
        }

        if (etPassword.getText().toString().trim().length() == 0) {
            BaseToast.showMessage(this, "Please fill password");
            return;
        }

        presenter.actionRegister(etFullName.getText().toString().trim(),
                etUsername.getText().toString().trim(),
                etPassword.getText().toString().trim());
    }

    @OnClick(R.id.btn_back)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onError(String message) {
        BaseToast.showMessage(this, message);
    }

    @Override
    public void onSuccess(String message) {
        BaseToast.showMessage(this, message);
        finish();
    }
}
