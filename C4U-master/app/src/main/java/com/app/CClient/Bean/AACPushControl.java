package com.app.CClient.Bean;



public class AACPushControl {
    private static AACPushControl faceOpenglControl;
    private AACPush cateyeOpenglPlay;
    public static AACPushControl getControl() {
        if (faceOpenglControl == null) {
            faceOpenglControl = new AACPushControl();
        }
        return faceOpenglControl;
    }
    public AACPush getClientController() {
        return cateyeOpenglPlay;
    }

    public void setFaceOpenglPlay(AACPush cateyeOpenglPlay) {
        this.cateyeOpenglPlay = cateyeOpenglPlay;
    }
}
