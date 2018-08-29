package com.app.CClient.Bean;


public interface SendCallBack {
     void sendError();
     void connectError();
     void netBad();
     void onStart(String rtmpAddress);
}
