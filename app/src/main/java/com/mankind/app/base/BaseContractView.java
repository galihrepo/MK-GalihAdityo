package com.mankind.app.base;

/**
 * Created by galihadityo on 2017-09-28.
 */

public interface BaseContractView<T> {

    void onError(String message);

    void onSuccess(String message);
}
