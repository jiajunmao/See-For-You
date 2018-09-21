package com.app.CClient.Tool;

import android.graphics.RectF;

public class VideoRect {
    public RectF rect;

    public int startH;
    public int startMin;
    public int startSec;
    public int stopH;
    public int stopMin;
    public int stopSec;
    public int startvideo;
    public VideoRect(int startH, int startMin, int startSec, int stopH, int stopMin, int stopSec) {
        this.startH = startH;
        this.startMin = startMin;
        this.startSec = startSec;
        this.stopH = stopH;
        this.stopMin = stopMin;

    }
}
