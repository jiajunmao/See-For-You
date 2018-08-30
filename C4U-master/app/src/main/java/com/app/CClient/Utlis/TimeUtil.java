package com.app.CClient.Utlis;

public class TimeUtil {
	public static String timeFormat(int time) {

		String sformat = "00";
		String mformat = "00";
		String hformat = "00";
		int mm;
		int ss;
		int hh;
		if (time < 60) {
			sformat = time >= 10 ? "" + time : "0" + time;
		} else if (time >= 60 && time < 3600) {
			ss = time % 60;
			sformat = MaxToTen(ss);
			mm = (time - ss) / 60;
			mformat = MaxToTen(mm);
		} else if (time >= 3600) {
			ss = time % 60;
			sformat = MaxToTen(ss);
			mm = (time - ss) / 60;
			mformat = MaxToTen(mm);
			hh = (time - ss - mm) / 3600;
			hformat = MaxToTen(hh);
		}
		return hformat + ":" + mformat + ":" + sformat;
	}
	/**
	 * 
	 * @param num
	 * @return
	 */
	private static String MaxToTen(int num) {
		return num >= 10 ? "" + num : "0" + num;
	}
}
