package com.app.CClient.Tool;


import com.app.CClient.fragmenttabhost.R;

/**
 * 用户角色
 * Created by jiapeng on 2017/9/13.
 */

 class UserRole {

    public int nameResId;
    public byte type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String username;
    UserRole(byte type, int nameResId){
        this.nameResId = nameResId;
        this.type = type;
    }

    public int getNameResId() {
        return nameResId;
    }

    public byte getType() {
        return type;
    }

}
