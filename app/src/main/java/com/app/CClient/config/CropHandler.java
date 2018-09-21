package com.app.CClient.config;

import android.content.Intent;
import android.net.Uri;


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
    void onpaon();
    void onsetname();
    void onsetpassword();
    CropParams getCropParams();
}
