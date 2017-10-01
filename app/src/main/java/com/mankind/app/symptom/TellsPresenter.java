package com.mankind.app.symptom;

import com.mankind.app.base.BasePresenter;
import com.mankind.app.db.Query;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class TellsPresenter extends BasePresenter<TellsContract.View> implements TellsContract.Presenter {


    @Override
    public void actionInsertSymptom(String keyword) {
        if (Query.findSymptomEqual(keyword).size() > 0) {
            getView().onError(keyword + " already available");
        } else {
            Query.insertSymptom(keyword);
            getView().onSuccess("Success add symptom " + keyword);
            getView().navigateAccount();
        }
    }

}