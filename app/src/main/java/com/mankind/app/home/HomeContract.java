package com.mankind.app.home;

import com.mankind.app.base.BaseContractView;

/**
 * Created by galihadityo on 2017-09-29.
 */

public interface HomeContract {

    interface View extends BaseContractView {

        void setupView();

        void setupToolbar();

        void setupDrawer();

        void setupNavigation();

        void setupFirstFragment();

        void loadImageProfile();

    }

}
