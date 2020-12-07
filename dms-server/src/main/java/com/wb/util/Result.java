package com.wb.util;

import org.json.JSONObject;

public class Result<T> {
	
	/** 业务自定义状态码 */
	private int code;
	
	/** 请求状态描述，调试用 */
	private String msg;
	
	/** 请求数据，对象或数组均可（建议Map，可直接传输json格式数据） */
	private T data;
	
	/** 服务端异常 */
	public static Result<Object> SUCCESS = new Result<Object>(200, "SUCCESS");
	public static Result<Object> SERVER_ERROR = new Result<Object>(100, "系统异常：%s");
	public static Result<Object> BIND_ERROR = new Result<Object>(101, "(绑定异常)参数校验异常：%s"); /* 用占位符 传入一个参数 */
	public static Result<Object> SESSION_ERROR = new Result<Object>(102, "没有SESSION！"); /* 用占位符 传入一个参数 */
	public static Result<Object> REQUEST_ERROR = new Result<Object>(103, "非法请求！"); /* 用占位符 传入一个参数 */
	public static Result<Object> REQUEST_OVER_LIMIT = new Result<Object>(104, "请求次数过多！"); /* 用占位符 传入一个参数 */

	public Result() {
	}

	/**
	 * 成功时候的调用
	 * 
	 * @param data data
	 * @param <T>  t
	 * @return Result
	 */
	public static <T> Result<T> success(T data) {
		return new Result<T>(data);
	}

	/**
	 * 失败时候的调用
	 * 
	 * @param codeMsg codeMsg
	 * @param <T>     t
	 * @return Result
	 */
	public static <T> Result<T> error(int code, String msg) {
		return new Result<T>(code, msg);
	}
	
	/**
	 * 	自定义返回数据（推荐使用）
	 * @param <T>
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public static <T> Result<T> returnJson(int code, String msg, T data) {
		return new Result<T>(code, msg, data);
	}
	
	/**
	 * 自定义返回数据，构建并返回json格式的字符串（推荐使用）
	 * @param <T>
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public static <T> String buildJson(int code, String msg, T data) {
		Result<T> result = new Result<T>(code, msg, data);
		JSONObject jsonObject = new JSONObject(result);
		return jsonObject.toString();
	}

	/**
	 * 成功的构造函数
	 * 
	 * @param data data
	 */
	public Result(T data) {
		this.code = 200;// 默认200是成功
		this.msg = "SUCCESS";
		this.data = data;
	}

	/**
	 * 失败的构造函数
	 * 
	 * @param codeMsg codeMsg
	 */
	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * 自定义的构造函数
	 * 
	 * @param codeMsg codeMsg
	 */
	public Result(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data= data;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result: {" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
	}
}
