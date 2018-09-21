package com.app.CClient.Bean;

/**
 * Created by Administrator on 2016/12/1.
 */

public class WifiBean
{
    private String name;
    private String password;
    private int type;

    public String getOpenwifi() {
        return openwifi;
    }

    public void setOpenwifi(String openwifi) {
        this.openwifi = openwifi;
    }

    private String openwifi;
    private String closewifi;
    public int getWifitype() {
        return wifitype;
    }

    public void setWifitype(int wifitype) {
        this.wifitype = wifitype;
    }

    private int wifitype;
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String path;


    public String getName()
    {
        return name;
    }

    public int getType()
    {
        return type;
    }

    public String getPassword()
    {
        return password;
    }

    public WifiBean(String name, String password, int type) {
        super();
        this.name = name;
        this.password = password;
        this.type = type;

    }

    @Override
    public String toString() {
        return "wifiBean [wifiname=" + name + ", password=" + password + "type" + type +"]";
    }

}

