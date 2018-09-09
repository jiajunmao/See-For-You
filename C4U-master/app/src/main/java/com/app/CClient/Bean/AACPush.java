package com.app.CClient.Bean;


public interface AACPush {
    void pushaac(byte[] data, int size);
    void setdata(byte[] data);
    void getdata(byte[] data);
    void pushh264(byte[] data,int len);
    void pushPcm(byte[] data,int len);
    void pullh264(byte[] data,int len);
}
