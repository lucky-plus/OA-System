package com.oa.utils;

public class ExtjsAjaxResult {
	private boolean success = true;
	private String msg = "";

	public ExtjsAjaxResult() {}
	public ExtjsAjaxResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public String getMsg() {
		return msg;
	}
}
