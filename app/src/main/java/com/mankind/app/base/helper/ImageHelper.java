package com.mankind.app.base.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.mankind.app.base.BaseApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class ImageHelper {

    private static String directory;

    public static String base64Converter(Context context, String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();

        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public static void initialDirectory() {
        // create directory
        String path = BaseApplication.getStorage().getExternalStorageDirectory();
        directory = path + File.separator + "mankind";

        if (!BaseApplication.getStorage().isDirectoryExists(directory)) {
            BaseApplication.getStorage().createDirectory(directory);
        }

    }

    public static String getProfileImagePath() {
        return directory + File.separator + "profile.png";
    }

}
