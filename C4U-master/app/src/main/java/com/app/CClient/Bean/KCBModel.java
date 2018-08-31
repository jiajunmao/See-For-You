package com.app.CClient.Bean;
/**
 * 课程表对象
 * @author Administrator
 *
 */
public class KCBModel {

	//课程名称
	private String timeName;
	//周一至周五开始时间
	private String ofStartTime;
	//周一至周五结束时间
	private String ofEndTime;
	//周六至周日开始时间
	private String ssStartTime;
	//周六至周日结束时间
	private String ssEndTime;

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	private String starttime;
	public String getTimeName() {
		return timeName;
	}
	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}
	public String getOfStartTime() {
		return ofStartTime;
	}
	public void setOfStartTime(String ofStartTime) {
		this.ofStartTime = ofStartTime;
	}
	public String getOfEndTime() {
		return ofEndTime;
	}
	public void setOfEndTime(String ofEndTime) {
		this.ofEndTime = ofEndTime;
	}
	public String getSsStartTime() {
		return ssStartTime;
	}
	public void setSsStartTime(String ssStartTime) {
		this.ssStartTime = ssStartTime;
	}
	public String getSsEndTime() {
		return ssEndTime;
	}
	public void setSsEndTime(String ssEndTime) {
		this.ssEndTime = ssEndTime;
	}


}
