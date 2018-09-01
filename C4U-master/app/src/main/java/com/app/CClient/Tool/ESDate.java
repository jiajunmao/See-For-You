package com.app.CClient.Tool;


public class ESDate {
    public int year;
    public int month;
    public int day;

    public ESDate(byte[] bytes) {
        this.month = bytes[2];
        this.day = bytes[3];

    }
}
