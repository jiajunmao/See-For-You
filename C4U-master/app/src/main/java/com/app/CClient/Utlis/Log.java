package com.app.CClient.Utlis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	public static boolean DEBUG = true;
	public static boolean FILE = false;

	public static void file(String lev, String tag, String msg) {
		File file = new File("/storage/sdcard1/dvr/log.txt");
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(new Date());
			FileWriter out = new FileWriter(file, true);
			out.write(String.format("%s %s %-20s %s\n", lev, time, tag, msg));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void d(String tag, String msg) {
		if (DEBUG) android.util.Log.d(tag, msg);
		if (FILE) file("D", tag, msg);
	}

	public static void e(String tag, String msg) {
		if (DEBUG) android.util.Log.d(tag, msg);
		if (FILE) file("E", tag, msg);
	}

	public static void i(String tag, String msg) {
		if (DEBUG) android.util.Log.d(tag, msg);
		if (FILE) file("I", tag, msg);
	}

	public static void v(String tag, String msg) {
		if (DEBUG) android.util.Log.d(tag, msg);
		if (FILE) file("V", tag, msg);
	}

	public static void w(String tag, String msg) {
		if (DEBUG) android.util.Log.d(tag, msg);
		if (FILE) file("W", tag, msg);
	}

}
