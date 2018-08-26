package com.app.CClient.Bean;

public class UserMessage {
	
	/**连接标识*/
	private int nConnectStat;
	/**连接key*/
	private int connectKey;
	/**备注姓名*/
	private String name;
	/**手机IMEI码*/
	private String phoneIMEI;
	
	/**系统版本*/
	private String sysVersion;

	public int getConnectStat() {
		return nConnectStat;
	}

	public void setConnectStat(int lJBS) {
		nConnectStat = lJBS;
	}

	public int getConnectKey() {
		return connectKey;
	}

	public void setConnectKey(int connectKey) {
		this.connectKey = connectKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneIMEI() {
		return phoneIMEI;
	}

	public void setPhoneIMEI(String phoneIMEI) {
		this.phoneIMEI = phoneIMEI;
	}

	public String getSysVersion() {
		return sysVersion;
	}

	public void setSysVersion(String sysVersion) {
		this.sysVersion = sysVersion;
	}
	
	  
}