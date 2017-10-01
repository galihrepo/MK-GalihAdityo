package com.mankind.app.db;

import com.mankind.app.base.BaseApplication;
import com.mankind.app.db.symptom.SymptomModel;
import com.mankind.app.db.user.UserModel;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class Query {

    public static boolean register(String fullname, String username, String password) {
        if (isUserAvailable(username)) {
            return false;
        }

        UserModel user = new UserModel();
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);

        Realm realm = BaseApplication.getRealm();

        realm.beginTransaction();
        realm.insert(user);
        realm.commitTransaction();

        return true;
    }

    public static boolean isUserAvailable(String username) {
        Realm realm = BaseApplication.getRealm();

        RealmResults<UserModel> data = realm.where(UserModel.class)
                .equalTo("username", username)
                .findAll();

        if (data.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLoginSuccess(String username, String password) {
        Realm realm = BaseApplication.getRealm();

        RealmResults<UserModel> data = realm.where(UserModel.class)
                .equalTo("username", username)
                .equalTo("password", password)
                .findAll();

        if (data.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static RealmResults<SymptomModel> getAllSymptom() {
        Realm realm = BaseApplication.getRealm();
        RealmResults<SymptomModel> data = realm.where(SymptomModel.class).findAll();
        return data;
    }

    public static RealmResults<SymptomModel> findSymptomContain(String keyword) {
        Realm realm = BaseApplication.getRealm();
        RealmResults<SymptomModel> data = realm.where(SymptomModel.class)
                .contains("symptom", keyword)
                .findAll();
        return data;
    }

    public static RealmResults<SymptomModel> findSymptomEqual(String keyword) {
        Realm realm = BaseApplication.getRealm();
        RealmResults<SymptomModel> data = realm.where(SymptomModel.class)
                .equalTo("symptom", keyword)
                .findAll();
        return data;
    }

    public static void insertSymptom(String symptom) {
        SymptomModel data = new SymptomModel();
        data.setSymptom(symptom);

        Realm realm = BaseApplication.getRealm();
        realm.beginTransaction();
        realm.insert(data);
        realm.commitTransaction();
    }

    public static void updateSymptom(String oldSymptom, String symptom) {
        Realm realm = BaseApplication.getRealm();

        SymptomModel oldData = realm.where(SymptomModel.class)
                .equalTo("symptom", oldSymptom)
                .findFirst();

        realm.beginTransaction();
        oldData.setSymptom(symptom);
        realm.commitTransaction();
    }

    public static void deleteSymptom(final String symptom) {
        Realm realm = BaseApplication.getRealm();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<SymptomModel> data = realm.where(SymptomModel.class)
                        .equalTo("symptom", symptom)
                        .findAll();
                data.deleteAllFromRealm();
            }
        });
    }

}
