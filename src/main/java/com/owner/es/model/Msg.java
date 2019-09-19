package com.owner.es.model;

public class Msg {

	// 状态码 200-成功 100-失败
	private int code;

	// 提示信息
	private String msg;

	// 用户要返回给浏览器的数据
	private Object data = null;

	public static Msg success() {
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("success");
		return result;
	}

	public static Msg fail() {
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("error");
		return result;
	}

	public Msg add(Object value) {
		this.data = value;
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
