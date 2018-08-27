package com.app.CClient.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.FileInputStream;
import java.util.List;

public class ImageSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    //ͼƬ��
    private List<String> mList            =    null;
    //����״̬
    public     boolean             mLoop             =     false;
    //��ȡ����
    private SurfaceHolder mSurfaceHolder     =     null;
    //ͼƬ����
    private int                 mCount             =     0; 
    //ʱ����
    private long                 speed             =     1000;
    
    private static Matrix matrix             =     new Matrix();
    /**
     * @param context
     * <see>����</see>
     * @param list
     * <see>ͼƬ��ַ�б� </see>
     * @param rate
     * <see>ͼƬ�л�ʱ�䡡��λ:����</see>
     * 
     */
    public ImageSurfaceView(Context context, List<String> list, long speed) {
        super(context);
        if(list!=null && list.size()>0){
            this.mList = list;
            this.speed = speed;
        }
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mLoop = true;//��ʼ��ͼ
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
    //ͼ�񴴽�ʱ
    public void surfaceCreated(SurfaceHolder holder) {
        if(mList!=null && mList.size()>0){
            if(mList.size() == 1){
                Log.d("ImageSurfaceView"," Only one picture");
                drawImg();
            }else{
                Log.d("ImageSurfaceView"," run Thread.");
                new Thread(this).start();
            }            
        }
        
    }
    //��ͼ���ʱ
    public void surfaceDestroyed(SurfaceHolder holder) {
        mLoop = false;
    }
    //��ͼ����
    private void drawImg(){
        Canvas canvas = mSurfaceHolder.lockCanvas();
        if(canvas == null || mSurfaceHolder == null){
            return;
        }
        if(mCount >= mList.size()){
            mCount = 0;
        }
        Bitmap bitmap  = null;
        try{
            String path = mList.get(mCount++);
            bitmap  = BitmapFactory.decodeStream(new FileInputStream(path));
            if(bitmap!=null){            
                //������͸�
                int height = getHeight();
                int width  = getWidth();
                //��ɺ��ʵ�ͼ��
                bitmap = getReduceBitmap(bitmap,width,height);
                
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setStyle(Style.FILL);
                //����
                paint.setColor(Color.BLACK);
                canvas.drawRect(new Rect(0, 0, getWidth(),getHeight()), paint);
                //Log.d("ImageSurfaceView_IMG",path);
                //��ͼ
                canvas.drawBitmap(bitmap, matrix, paint);    
            }
            //������ʾ
            mSurfaceHolder.unlockCanvasAndPost(canvas);                
        }catch(Exception ex){
            Log.e("ImageSurfaceView",ex.getMessage());
            return;
        }finally{
            //��Դ����
            if(bitmap!=null){
                bitmap.recycle();
            }
        }        
    }    
    //ˢ��ͼƬ
    public void run() {
        while(mLoop){            
            synchronized (mSurfaceHolder) {
                drawImg();
            }
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Log.e("ImageSurfaceView_Thread",e.getMessage());
            }
        }
        mList = null;//���
    }
    //����ͼƬ
    private Bitmap getReduceBitmap(Bitmap bitmap , int w, int h){
        int     width     =     bitmap.getWidth();
        int     hight     =     bitmap.getHeight();
        Matrix matrix     =     new Matrix();
        float     wScake     =     ((float)w/width); 
        float     hScake     =     ((float)h/hight);        
        matrix.postScale(wScake, hScake);        
        return Bitmap.createBitmap(bitmap, 0,0,width,hight,matrix,true);
    }

}