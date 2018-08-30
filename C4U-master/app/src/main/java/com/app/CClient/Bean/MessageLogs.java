package com.app.CClient.Bean;
/***
 * 短信model
 * @author Administrator
 *
 */
public class MessageLogs {

	//发件人
	private String person;
	//发件人地址
	private String address;
	//日期
	private String date;
	//内容
	private String body;
	//短信类型，是接收还是发送
	private String type;
	
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
