package com.app.CClient.Tool;

import java.util.List;

/**
 * Created by lyd10892 on 2016/8/23.
 */

public class HotelUtils {
    private int utilsset;

    public String getHote() {
        return hote;
    }

    public void setHote(String hote) {
        this.hote = hote;
    }

    private String hote;
    public static <D> boolean isEmpty(List<D> list) {
        return list == null || list.isEmpty();
    }
}
