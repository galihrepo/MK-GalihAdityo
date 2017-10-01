package com.mankind.app.base.view;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class BaseToast extends Toast {

    public BaseToast(Context context) {
        super(context);
    }

    public static void showMessage(Context context, String message) {
        makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
