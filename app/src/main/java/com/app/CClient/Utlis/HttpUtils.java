package com.app.CClient.Utlis;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.loopj.android.http.HttpGet;



import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

//Http请求的工具类
public class HttpUtils
{

    private static final int TIMEOUT_IN_MILLIONS = 1000 * 10;
    private int time = 0;
    public interface CallBack
    {
        void onRequestComplete(String result);
    }


    /**
     * 异步的Get请求
     *
     * @param urlStr
     * @param callBack
     */
    public static void doGetAsyn(final String urlStr, final CallBack callBack)
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    String result = doGet(urlStr);
                    if (callBack != null)
                    {
                        callBack.onRequestComplete(result);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            };
        }.start();
    }

    /**
     * 异步的Post请求
     * @param urlStr
     * @param params
     * @param callBack
     * @throws Exception
     */
    public static void doPostAsyn(final String urlStr, final String params,
                                  final CallBack callBack) throws Exception
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    String result = doPost(urlStr, params);
                    if (callBack != null)
                    {
                        callBack.onRequestComplete(result);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            };
        }.start();

    }

    /**
     * Get请求，获得返回数据
     *
     * @param urlStr
     * @return
     * @throws Exception
     */
    public static String doGet(String urlStr)
    {
        String strResult = null;
        try {
            HttpGet httpGet = new HttpGet(urlStr);
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);

            if (httpResponse.getStatusLine().getStatusCode() == 200)
            {
                //  第3步：使用getEntity方法获得返回结果
                strResult = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strResult;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     * @throws Exception
     */
    public static String doPost(String url, String param)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);

            if (param != null && !param.trim().equals(""))
            {
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static final byte PROGRESS_SETMAX = 1;          //文件有多大，也就是初始化进度条
    public static final byte PROGRESS_SETPRO = 2;          //设置进度条
    public static final byte PROGRESS_CLOSE = 3;          //关闭j进度条
    public static final byte PROGRESS_ERROR = 4;          //下载错误
    public static void DownFileToSDCard(final String strUrl, final String strFullFile, final Handler hProgresshandle){

        new Thread() {
            public void run() {

                    //构建URL地址
                    try {
                        URL url = new URL(strUrl);
                        //打开打开打开
                        HttpURLConnection hcont = (HttpURLConnection) url.openConnection();
                        //建立实际链接
                        hcont.connect();
                        //获取输入流内容
                        InputStream is = hcont.getInputStream();
                        //获取文件长度
                        int dwSize =hcont.getContentLength();

                        if (hProgresshandle != null)
                        {
                            Message msg = new Message();
                            Bundle data = new Bundle();
                            data.putInt("msg", dwSize);
                            msg.setData(data);
                            msg.what = PROGRESS_SETMAX;
                            hProgresshandle.sendMessage(msg);
                        }
                        //写入文件
                        OutputStream os = new FileOutputStream(strFullFile);
                        int length;
                        int lengtsh = 0;
                        byte [] bytes = new byte[1024];
                        while ((length = is.read(bytes))!= -1){
                            os.write(bytes,0,length);
                            //获取当前进度值
                            lengtsh +=length;

                            Message msg = new Message();
                            Bundle data = new Bundle();
                            data.putInt("msg", lengtsh);
                            msg.setData(data);
                            msg.what = PROGRESS_SETPRO;
                            hProgresshandle.sendMessage(msg);
                        }
                        //关闭流
                        is.close();
                        os.close();
                        os.flush();

                        if (hProgresshandle != null)
                        {
                            Message msg = new Message();
                            Bundle data = new Bundle();
                            data.putInt("msg", dwSize);
                            msg.setData(data);
                            msg.what = PROGRESS_CLOSE;
                            hProgresshandle.sendMessage(msg);
                        }
                    } catch (IOException e) {
                        if (hProgresshandle != null)
                        {
                            Message msg = new Message();
                            Bundle data = new Bundle();
                            msg.setData(data);
                            msg.what = PROGRESS_ERROR;
                            hProgresshandle.sendMessage(msg);
                        }
                        e.printStackTrace();
                    }
            }
        }.start();

    }


}
