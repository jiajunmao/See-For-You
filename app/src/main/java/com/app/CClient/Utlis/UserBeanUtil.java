package com.app.CClient.Utlis;



public class UserBeanUtil {



    private static UserBeanUtil instance;

    private UserBeanUtil() {

    }

    public static UserBeanUtil getInstance() {
        if (instance == null) {
            synchronized (UserBeanUtil.class) {
                if (instance == null) {
                    instance = new UserBeanUtil();
                }
            }
        }
        return instance;
    }
}
