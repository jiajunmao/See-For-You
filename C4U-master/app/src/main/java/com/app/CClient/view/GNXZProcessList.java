package com.app.CClient.view;

public class GNXZProcessList {

	//进程名称
	private String processName;
	//进程是否在数据库中，即是否是被选中状态,0为未选中，1位选中
	private String flagState;

	public int getProcesstype() {
		return processtype;
	}

	public void setProcesstype(int processtype) {
		this.processtype = processtype;
	}

	private int processtype;
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getFlagState() {
		return flagState;
	}
	public void setFlagState(String flagState) {
		this.flagState = flagState;
	}


}
