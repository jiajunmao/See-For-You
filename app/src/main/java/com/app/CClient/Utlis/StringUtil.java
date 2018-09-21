package com.app.CClient.Utlis;

import android.util.Log;

public class StringUtil {
	
	private static final String TAG = "SubstringUtil";
	public static String getSSID(String ssid){
		if(ssid.length()>=2){
			Log.e(TAG, ssid.substring(ssid.length()-1,ssid.length()));
			Log.d(TAG, ssid.substring(0, 1));
			if(ssid.substring(0,1).equals("\"")&&ssid.substring(ssid.length()-1,ssid.length()).equals("\"")){
				Log.d(TAG, "substring:"+ssid);
				ssid= ssid.substring(1, ssid.length()-1);
			}	
			Log.i(TAG, ssid);
		}
		return ssid;
	}
	/**
	 *  add ssid \"
	 * @param ssid
	 * @return
	 */
	public static String setSSID(String ssid){
		if(ssid.length()>=2){
			Log.e(TAG, ssid.substring(ssid.length()-1,ssid.length()));
			Log.d(TAG, ssid.substring(0, 1));
			if(!ssid.substring(0,1).equals("\"")&&!ssid.substring(ssid.length()-1,ssid.length()).equals("\"")){
				Log.d(TAG, "substring:"+ssid);
				ssid=new String("\""+ssid+"\"");
			}	
			Log.i(TAG, ssid);
		}else{
			ssid=new String("\""+ssid+"\"");
		}
		return ssid;
	}
	
}
