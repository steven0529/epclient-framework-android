package com.chuck.epclient.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import timber.log.Timber;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 02/04/2017
 */

public final class ManifestHelper {

    //Prevent instantiation
    private ManifestHelper() { }

    public static String getMetaDataString(Context context, String name) {
        PackageManager pm = context.getPackageManager();
        String value = null;

        try {
            ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            value = ai.metaData.getString(name);
        } catch (Exception e) {
            Timber.w("Couldn't find config value: " + name);
        }

        return value;
    }

    public static Integer getMetaDataInteger(Context context, String name) {
        PackageManager pm = context.getPackageManager();
        Integer value = null;

        try {
            ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            value = ai.metaData.getInt(name);
        } catch (Exception e) {
            Timber.w("Couldn't find config value: " + name);
        }

        return value;
    }

    public static Boolean getMetaDataBoolean(Context context, String name) {
        PackageManager pm = context.getPackageManager();
        Boolean value = false;

        try {
            ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            value = ai.metaData.getBoolean(name);
        } catch (Exception e) {
            Timber.w("Couldn't find config value: " + name);
        }

        return value;
    }
}
