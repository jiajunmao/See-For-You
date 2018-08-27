package com.app.CClient.Bean;
/***
 * 用户通信记录model
 * @author Administrator
 *
 */
public class PhoneLogs {

	//号码
	private String phoneNum;
	//呼叫类型
	private String type;
	//呼叫时间
	private String time;
	//联系人
	private String person;
	//通话时间
	private String duration;
	
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
	
	
}
