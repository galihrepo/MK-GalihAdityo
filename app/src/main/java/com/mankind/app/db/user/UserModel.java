package com.mankind.app.db.user;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class UserModel extends RealmObject {

    @PrimaryKey
    public String username;
    public String fullname;
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
