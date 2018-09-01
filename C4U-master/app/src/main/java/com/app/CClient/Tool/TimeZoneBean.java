package com.app.CClient.Tool;

public class TimeZoneBean {
    public String name;
    public int value;
    public String data;

    public TimeZoneBean(String name) {
        this.name = name;
    }

    public TimeZoneBean() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setData(String name) {
        int start = name.indexOf("(");
        int end = name.indexOf(")");
        String str = name.substring(start, end + 1);
        this.data = str;
    }
}
