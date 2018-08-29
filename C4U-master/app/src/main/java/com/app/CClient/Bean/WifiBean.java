package com.app.CClient.Bean;

/**
 * Created by Administrator on 2016/12/1.
 */

public class WifiBean
{
    private String name;
    private String password;
    private int type;


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

