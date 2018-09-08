package com.app.CClient.config;

import android.content.Intent;
import android.net.Uri;

/**
 * Created with Android Studio.
 * User: ryan@xisue.com
 * Date: 10/1/14
 * Time: 11:00 AM
 * Desc: CropHandler
 * Revision:
 * - 10:20 2014/10/01 The basic interfaces.
 * - 13:00 2014/10/03 Able to get access to the CropParams the related Context.
 * - 23:00 2015/09/05 Remove getContext, rename onCancel, onFailed, add handleIntent.
 */
public interface CropHandler {

    void onPhotoCropped(Uri uri);

    void onCompressed(Uri uri);

    void onCancel(boolean isopen);

    void onFailed(String message);
    void succeed(String issucceed);
    void handleIntent(Intent intent, int requestCode);
    void setdevicename(String deivicename);
    void start(int type);
    void stop(boolean isstop);
    void get(int data);
    void set(int setdata);
    CropParams getCropParams();
}
