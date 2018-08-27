package com.app.CClient.Utlis;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferencesUtil
{

	private final static String WIFI_PASSWORD="wifi_password";
	private final static String WIFI_SSID="wifi_ssid";
	private final static String WIFI_TYPE="wifi_type";
	private final static String SHARE_PATH="share_path";
	private final static String FULL_SCREEN="full_screen";
	private final static String AILERON="aileron";
	private final static String LIFTING="lifting";
	private final static String DECLINATION="declination";
	public static final String CONFIGFILE = "cachevalue";
	/**
	 *  0:从未校准；1：已室内校准；2：已室外校准；3：硬件故障
	 */
	private final static String CALIBRATION_STATUS="calibration_status";

	public static void setBoolean(Context context, String key, boolean value){
		SharedPreferences sp = context.getSharedPreferences(CONFIGFILE, Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
		
	}

	public static boolean getBoolean(Context context, String key){
		SharedPreferences sp = context.getSharedPreferences(CONFIGFILE, Context.MODE_PRIVATE);
		return sp.getBoolean(key, false);
	}
	public static void setemail(Context context, String ssid){
		SharedPreferences sp = context.getSharedPreferences(CONFIGFILE, Context.MODE_PRIVATE);
		sp.edit().putString(WIFI_SSID,ssid).commit();
	}
	public static String getemail(Context context){
		SharedPreferences sp = context.getSharedPreferences(CONFIGFILE, Context.MODE_PRIVATE);
		return sp.getString(WIFI_SSID,"");
	}

	public static void setusername(Context context, String wifipassword){
		SharedPreferences sp = context.getSharedPreferences(CONFIGFILE, Context.MODE_PRIVATE);
		sp.edit().putString(WIFI_PASSWORD,wifipassword).commit();
	}

	public static String getusername(Context context){
		SharedPreferences sp = context.getSharedPreferences(CONFIGFILE, Context.MODE_PRIVATE);
		return sp.getString(WIFI_PASSWORD,"");
	}
	//lifting



}
