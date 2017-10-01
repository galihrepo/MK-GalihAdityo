package com.mankind.app.account;

import com.mankind.app.base.BaseContractView;
import com.mankind.app.db.symptom.SymptomModel;

import io.realm.RealmResults;

/**
 * Created by galihadityo on 2017-09-29.
 */

public interface AccountContract {

    interface View extends BaseContractView {

        void loadImagePicture();

    }

    interface Presenter {


    }

}
