package com.app.CClient.Tool;


public class ESDate {
    public int year;
    public int month;
    public int day;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String type;
    public ESDate(byte[] bytes) {
        this.month = bytes[2];
        this.day = bytes[3];

    }
}
