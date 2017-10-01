package com.mankind.app.login;

import com.mankind.app.base.BaseContractView;

/**
 * Created by galihadityo on 2017-10-01.
 */

public interface LoginContract {

    interface View extends BaseContractView {

        void navigateToFeature();

        void navigateToHome();

        void checkSession();

    }

    interface Presenter {

        void actionLogin(String username, String password);

        void actionInsertMasterSymptom();

    }

}
