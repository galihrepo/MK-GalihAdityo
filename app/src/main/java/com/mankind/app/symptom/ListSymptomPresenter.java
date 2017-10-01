package com.mankind.app.symptom;

import com.mankind.app.base.BasePresenter;
import com.mankind.app.db.Query;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class ListSymptomPresenter extends BasePresenter<ListSymptomContract.View> implements ListSymptomContract.Presenter {

    @Override
    public void actionGetAll() {
        getView().renderSymptoms(Query.getAllSymptom());
    }

    @Override
    public void actionSearch(String keyword) {
        getView().renderSymptoms(Query.findSymptomContain(keyword));
    }

}