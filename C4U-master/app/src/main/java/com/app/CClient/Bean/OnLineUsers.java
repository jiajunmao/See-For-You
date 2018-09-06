package com.app.CClient.Bean;
/***
 * 
 * @author Administrator
 * 在线用户需要展示的信息model，这里做范例展示，多的可以直接添加
 */
public class OnLineUsers {

   
	//展示IP
	private String userIMEI;
	/**连接标识 被控端用户名*/
	private String strConnectStat;
	/**备注手机名称*/
	private String strPhoneName;
	/**版本号*/
	private String strVersion;
	/**安卓版本号*/
	private String strAndroidVersion;
	/**网络状态*/
	private String strNetState;
	/**手机备注或者号码*/
	private String strState;

	public String getPhonetype() {
		return phonetype;
	}

	public void setPhonetype(String phonetype) {
		this.phonetype = phonetype;
	}

	private String phonetype;
	public  boolean bIsOnLine;

	public String getAndroidVersion() {
		return strAndroidVersion;
	}

	public void setAndroidVersion(String strAndroidVersion) {
		this.strAndroidVersion = strAndroidVersion;
	}
	public String getVersion() {
		return strVersion;
	}

	public void setVersion(String strVersion) {
		this.strVersion = strVersion;
	}
	public String getPhoneName() {
		return strPhoneName;
	}

	public void setPhoneName(String note) {
		this.strPhoneName = note;
	}

	public String getNetState() {
		return strNetState;
	}

	public void setNetState(String strNetState) {
		this.strNetState = strNetState;
	}
	
	public String getState() {
		return strState;
	}

	public void setState(String strState) {
		this.strState = strState;
	}

	public String getConnectStat() {
		return strConnectStat;
	}

	public void setConnectStat(String strUserID) {
		strConnectStat = strUserID;
	}


	
	
	public String getUserIMEI() {
		return userIMEI;
	}
	public void setUserIMEI(String userIP) {
		this.userIMEI = userIP;
	}

}
