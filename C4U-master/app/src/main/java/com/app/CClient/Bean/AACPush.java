package com.app.CClient.Bean;


public interface AACPush {
    void pushaac(byte[] data, int size);
    void setdata(byte[] data);
    void getdata(byte[] data);
}
