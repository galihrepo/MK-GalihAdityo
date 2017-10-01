package com.mankind.app.symptom;

import com.mankind.app.base.BaseContractView;
import com.mankind.app.db.symptom.SymptomModel;

import io.realm.RealmResults;

/**
 * Created by galihadityo on 2017-09-29.
 */

public interface TellsContract {

    interface View extends BaseContractView {

        void navigateAccount();

        void loadImageProfile();

    }

    interface Presenter {

        void actionInsertSymptom(String keyword);

    }

}
