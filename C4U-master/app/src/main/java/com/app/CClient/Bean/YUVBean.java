package com.app.CClient.Bean;

/**YUV raw数据
 *
 * Created by jianddongguo on 2017/7/21.
 */

public class YUVBean {
    private String picPath;
    // YUV数据
    private byte[] yuvData;
    // 时间戳
    private long timeStamp;

    private int width;
    private int height;
    private int degree;
    private boolean isEnableSoftCodec;
    private boolean isFrontCamera;

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public byte[] getYuvData() {
        return yuvData;
    }

    public void setYuvData(byte[] yuvData) {
        this.yuvData = yuvData;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public boolean isEnableSoftCodec() {
        return isEnableSoftCodec;
    }

    public void setEnableSoftCodec(boolean enableSoftCodec) {
        isEnableSoftCodec = enableSoftCodec;
    }

    public boolean isFrontCamera() {
        return isFrontCamera;
    }

    public void setFrontCamera(boolean frontCamera) {
        isFrontCamera = frontCamera;
    }
}
