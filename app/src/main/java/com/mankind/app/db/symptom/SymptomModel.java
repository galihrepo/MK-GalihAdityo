package com.mankind.app.db.symptom;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class SymptomModel extends RealmObject {

    @PrimaryKey
    public String symptom;

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}
