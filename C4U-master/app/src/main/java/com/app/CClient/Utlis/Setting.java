package com.app.CClient.Utlis;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Setting {
	private SharedPreferences mSharedPreferences;
	public boolean isAutoRun;
	public boolean isRecordAudio;
	public boolean isRecording;
	public boolean isLocked;
	public int mTimeLong;
	public int mQuality;

	public Setting(Context context) {
		mSharedPreferences = context.getSharedPreferences("Preferences",
				Context.MODE_PRIVATE);
		isAutoRun = mSharedPreferences.getBoolean("isAutoRun", true);
		isRecordAudio = mSharedPreferences.getBoolean("isRecordAudio", true);
		isRecording = mSharedPreferences.getBoolean("isRecording", false);
//		isLocked = mSharedPreferences.getBoolean("isLocked", false);
		mTimeLong = mSharedPreferences.getInt("mTimeLong", 5);
		mQuality = mSharedPreferences.getInt("mQuality", 1080);
	}

	public void save() {
		Editor editor = mSharedPreferences.edit();
		editor.putBoolean("isAutoRun", isAutoRun);
		editor.putBoolean("isRecordAudio", isRecordAudio);
		editor.putBoolean("isRecording", isRecording);
//		editor.putBoolean("isLocked", isLocked);
		editor.putInt("mTimeLong", mTimeLong);
		editor.putInt("mQuality", mQuality);
		editor.commit();
	}

}
