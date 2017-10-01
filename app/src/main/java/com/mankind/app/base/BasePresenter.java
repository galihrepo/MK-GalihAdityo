package com.mankind.app.base;

/**
 * Created by galihadityo on 2017-09-28.
 */

public abstract class BasePresenter<T extends BaseContractView> {

    private T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

}
