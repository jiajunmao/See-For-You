package com.app.CClient.Tool;

import com.yajie.smartlock.R;

/**
 * 用户角色
 * Created by jiapeng on 2017/9/13.
 */

public enum UserRole {
    SUPER_ADMIN((byte)0, R.string.super_admin),
    ADMIN((byte)1,R.string.admin),
    NORMAL_USER((byte)2,R.string.normal_user);

    public int nameResId;
    public byte type;

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

    public static UserRole getRole(int value){
        return UserRole.values()[value];
    }
}
