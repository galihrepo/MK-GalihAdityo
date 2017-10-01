package com.mankind.app.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.mankind.app.base.helper.ImageHelper;
import com.snatik.storage.Storage;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by galihadityo on 2017-09-28.
 */

public class BaseApplication extends MultiDexApplication {

    private static Realm realm;
    private static Storage storage;

    @Override
    public void onCreate() {
        super.onCreate();
        setupRealm();
        storage();
    }

    private void storage() {
        storage = new Storage(this);
        ImageHelper.initialDirectory();
    }

    public static Storage getStorage() {
        return storage;
    }

    private void setupRealm() {
        if (realm == null) {
            RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                    .name("mankind.realm")
                    .deleteRealmIfMigrationNeeded()
                    .build();
            realm.setDefaultConfiguration(realmConfiguration);
            realm = Realm.getDefaultInstance();
        }
    }

    public static Realm getRealm() {
        return realm;
    }

}
