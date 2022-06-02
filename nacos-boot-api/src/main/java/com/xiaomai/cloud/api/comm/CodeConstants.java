package com.xiaomai.cloud.api.comm;

public class CodeConstants {
	/*请求头参数*/
	public static final String HEADER_INFO = "Authorization-dev";	//开发：Authorization-dev 正式：Authorization
	
	/*请求成功编号*/
	public static final String CODE_SUCCESS = "200";
	/*请求失败编号*/
	public static final String CODE_DEFEAT = "201";
	/*服务异常 code*/
	public static final String CODE_ERROR_SERVER = "500";
	/*服务异常 msg*/
	public static final String CODE_ERROR_MSG = "服务异常!";
	/*数据异常 code*/
	public static final String CODE_FAIL_DATE = "501";
	/*数据异常 msg*/
	public static final String CODE_FAIL_MSG = "数据异常!";
	
}
