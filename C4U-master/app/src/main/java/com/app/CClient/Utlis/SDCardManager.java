package com.app.CClient.Utlis;

import android.os.StatFs;
import android.text.format.Time;

import java.io.File;

public class SDCardManager {
	public static final int PHOTO_PATH = 0;
	public static final int VIDEO_PATH = 1;
	public static final int LOCK_PATH = 2;
	private String mCard;
	private String[] mPath = new String[3];
	private static final String[] SDCARDS = {
		"/storage/sdcard1",
	};

	public boolean checkMount() {
		for (int i = 0; i < SDCARDS.length; i++) {
			File file = new File(SDCARDS[i] + "/DVR");
			file.mkdir();
			if (file.exists()) {
				mCard = SDCARDS[i];
				mPath[PHOTO_PATH] = file + "/photo";
				mPath[VIDEO_PATH] = file + "/video";
				mPath[LOCK_PATH] = file + "/lock";
				return true;
			}
		}
		return false;
	}

	public boolean checkAvailableSize(int index) {
		long size = getAvailableSize();
		long need = 0;
		need = (index == PHOTO_PATH) ? 10 : 100;
		while (size < need) {
			if (!deleteFile(mPath[index])) return false;
			size = getAvailableSize();
		}
		return true;
	}

	public long getAvailableSize() {
		StatFs fs = new StatFs(mCard);
		long size = fs.getAvailableBytes() >> 20;
		return size;
	}

	public String getPath(int index) {
		Time time = new Time();
		time.setToNow();
		String folder = time.format("/%Y-%m-%d");
		String name = time.format("/%Y%m%d%H%M%S");
		String path = null;
		switch (index) {
		case PHOTO_PATH:
			path = mPath[index] + folder + name + ".jpg";
			break;
		case VIDEO_PATH:
			path = mPath[index] + folder + name + ".mp4";
			break;
		case LOCK_PATH:
			path = mPath[index] + folder;
			break;
		}
		return path;
	}

	public boolean deleteFile(String path) {
		File file = new File(path);
		File[] list = file.listFiles();
		if (list == null || list.length == 0) {
			file.delete();
			return false;
		}
		file = list[0];
		for (int i = 1; i < list.length; i++) {
			if (file.compareTo(list[i]) > 0) {
				file = list[i];
			}
		}
		if (file.isDirectory()) {
			deleteFile(file.getPath());
		} else {
			file.delete();
		}
		return true;
	}

}
