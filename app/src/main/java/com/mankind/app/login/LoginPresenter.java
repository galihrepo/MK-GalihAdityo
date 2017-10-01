package com.mankind.app.login;

import com.mankind.app.base.BasePresenter;
import com.mankind.app.base.helper.Prefs;
import com.mankind.app.db.Query;
import com.mankind.app.register.RegisterContract;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void actionLogin(String username, String password) {

        if (Query.isLoginSuccess(username, password)) {
            Prefs.write(Prefs.LOGIN_AS_USER, username);
            getView().onSuccess("");
        } else {
            getView().onError("Invalid username or password");
        }

    }

    @Override
    public void actionInsertMasterSymptom() {

        if (Query.getAllSymptom().size() == 0) {
            Query.insertSymptom("Air keringat bercucuran");
            Query.insertSymptom("Buang air besar");
            Query.insertSymptom("Tidur gelisah");
            Query.insertSymptom("Makan tidak tentu");
            Query.insertSymptom("Keringat dingin");
            Query.insertSymptom("Mata merah");
            Query.insertSymptom("Buang angin");
            Query.insertSymptom("Sesak");
        }

        getView().checkSession();

    }

}