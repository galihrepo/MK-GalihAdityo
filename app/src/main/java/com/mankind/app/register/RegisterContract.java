package com.mankind.app.register;

import com.mankind.app.base.BaseContractView;

/**
 * Created by galihadityo on 2017-10-01.
 */

public interface RegisterContract {

    interface View extends BaseContractView {


    }

    interface Presenter {

        void actionRegister(String fullname, String username, String password);

    }

}
