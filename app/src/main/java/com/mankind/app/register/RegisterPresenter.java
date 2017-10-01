package com.mankind.app.register;

import com.mankind.app.base.BasePresenter;
import com.mankind.app.db.Query;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    @Override
    public void actionRegister(String fullname, String username, String password) {
        boolean isSuccess = Query.register(fullname, username, password);
        if (isSuccess) {
            getView().onSuccess("Successfully create user, please login");
        } else {
            getView().onError("Username already exist");
        }
    }

}
