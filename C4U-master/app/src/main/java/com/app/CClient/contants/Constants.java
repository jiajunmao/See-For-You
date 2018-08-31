package com.app.CClient.contants;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class Constants
{
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final String MIME_TYPE = "video/avc";
    public static final MediaFormat Media_Format = MediaFormat.createVideoFormat(
            MIME_TYPE, WIDTH, HEIGHT);
    public static final int TIME_INTERNAL = 30;
    public static final int RTSP_PLAY = 0;
    public static final int BG_ALPHA = 125;
    public static final int SHARE_ONE_FACEBOOK = 0;
   // public static final int SHARE_ONE_INSTAGRAM = 1;
    public static final int SHARE_ONE_TWITTER =1;
    public static final int SHARE_ONE_YOUTUBE = 2;
    public static final int SHARE_ONE_GOOGLEPLUS = 3;
 public static final int SHARE_TWO_WEChAT = 4;
    public static final int SHARE_TWO_WECHAT = 0;
    public static final String SHARE_ONE = "shareOne";
    public static final String SHARE_TWO = "shareTwo";
    public static final String SETUP_NOVICE_GUIDE_ISFIRST = "setup_novice_guide_isFirst";
    //public static final String SETUP_NOVICE_GUIDE_POSITION = "setup_novice_guide_position";
    public static final String FILE_PARENT = "C-me";
    public static final String CMD = "CMD";
    public static final String REPORT = "REPORT";
    public static final String PARAM = "PARAM";
    public static final String RESULT = "RESULT";
    public static final String CONFIGFILE = "cachevalue";
    public static final String SETUP_TYPE = "setup_type";

    public static final String PHRASE = "phrase";
    public static final String WHITE_BALANCE = "M_AWB";
    public static final String EXPOSURE = "M_AE";
    public static final String SDCARD = "M_CARD";
    public static final String SDCARD_FREE_SPACE = "freeSpace";
    public static final String SDCARD_USED_SPACE = "usedSpace";
    public static final String SDCARD_TOTAL_SPACE = "totalSpace";
    public static final String SDCARD_STATUS = "online";
    public static final String FLASH = "M_LED_MODE";
    public static final String BATTERY = "M_BATTERY";
    public static final String CHARGE = "charge";
    public static final String POWER = "power";
    public static final String BRIGHTNESS = "M_BHT";
    public static final String CONTRAST = "M_CTS";
    public static final String WIFI_PARAM = "Wifi_Param";
    public static final String WIFI_SSID = "ssid";
    public static final String WIFI_PASSWORD = "pass_phrase";
    public static final String POSITION_X = "X";
    public static final String POSITION_Y = "Y";
    public static final String VERSION = "FirmWare";
    public static final String TCP_FAIL_RECEIVE = "tcp_fail_receive";
    public static final String TCP_FAIL_SEND = "tcp_fail_send";
    public static final String TCP_FAIL_CONNECT = "tcp_fail_connect";
    public static final String FTP_CONNECT_SUCCESSS = "FTP_CONNECT_SUCCESSS";
    public static final String FTP_CONNECT_FAIL = "FTP_CONNECT_FAIL";
    public static final String FTP_DISCONNECT_SUCCESS = "FTP_DISCONNECT_SUCCESS";
    public static final String FTP_FILE_NOTEXISTS = "FTP_FILE_NOTEXISTS";
    public static final String SDCARD_UPDATE = "sdcard_update";
    public static final String POWER_UPDATE = "power_update";
    public static final String FTP_UPLOAD_SUCCESS = "FTP_UPLOAD_SUCCESS";
    public static final String FTP_UPLOAD_FAIL = "FTP_UPLOAD_FAIL";
    public static final String FTP_UPLOAD_LOADING = "FTP_UPLOAD_LOADING";
    public static final String NUM = "num";
    public static final String DELAY = "delay";

    public static final String FTP_DOWN_LOADING = "FTP_DOWN_LOADING";
    public static final String FTP_DOWN_SUCCESS = "FTP_DOWN_SUCCESS";
    public static final String FTP_DOWN_FAIL = "FTP_DOWN_FAIL";

    public static final String FTP_DELETEFILE_SUCCESS = "FTP_DELETEFILE_SUCCESS";
    public static final String FTP_DELETEFILE_FAIL = "FTP_DELETEFILE_FAIL";

    public static final String YEAR = "YEAR";
    public static final String MONTH = "MONTH";
    public static final String DAY = "DAY";
    public static final String HOUR = "HOUR";
    public static final String MINUTE = "MINUTE";
    public static final String SECOND = "SECOND";
   public static final String SPS_FRAME="674d001fe5402802d880";
   public static final String PPS_FRAME="68ee3112";

   public static final String FTP_UPLOAD_SUCCESS2 = "FTP_UPLOAD_SUCCESS";
   public static final String FTP_UPLOAD_FAIL2 = "FTP_UPLOAD_FAIL";
   public static final String FTP_UPLOAD_LOADING2 = "FTP_UPLOAD_LOADING";
   public static final String NUM2 = "num";
   public static final String DELAY2 = "delay";

   public static final String CONTRAS = "M_CTS";
   public static final String WIFI_PARA = "Wifi_Param";
   public static final String WIFI_SSI = "ssid";
   public static final String WIFI_PASSWOR = "pass_phrase";


}
