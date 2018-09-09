package com.app.CClient.Utlis;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.io.File;


public class MediaUtil
{
	private String media;
	public static void deleteFile(String path){
		File mp4File = new File(path);
		if(mp4File.exists()){
			mp4File.delete();
		}
		mp4File=null;
	}
	
	/**
	 * 发出广播通知
	 * @param path 
	 * @param context 
	 */
	public static  void mediaScanner(String path,Context context) {
		if (path == null || path.length() <= 0) {
			return;
		}
		Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		scanIntent.setData(Uri.fromFile(new File(path)));
		context.sendBroadcast(scanIntent);
	}

	private static String addZero(String day) {
		if (day.length() == 1) {
			return "0" + day;
		}
		return day;
	}

}
