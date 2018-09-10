package com.app.CClient.Bean;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ADDrSrtControl {
    private static ADDrSrtControl faceOpenglControl;
    private AddrStr cateyeOpenglPlay;
    public static ADDrSrtControl getControl() {
        if (faceOpenglControl == null) {
            faceOpenglControl = new ADDrSrtControl();
        }
        return faceOpenglControl;
    }
    public AddrStr getClientController() {
        return cateyeOpenglPlay;
    }

    public void setFaceOpenglPlay(AddrStr cateyeOpenglPlay) {
        this.cateyeOpenglPlay = cateyeOpenglPlay;
    }
}
