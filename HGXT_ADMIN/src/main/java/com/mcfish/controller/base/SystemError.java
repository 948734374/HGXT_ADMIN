package com.mcfish.controller.base;

/**
 * 定义返回错误码
 * @author ZhouXiaobing
 * @date 2018年3月24日 上午9:04:21 
 * @version 1.0
 */
public enum SystemError {

	/*0：成功

	100 - 199 ： 通用类型错误 + 登陆注册模块的错误信息； 对应 userController

	200 - 299  : 项目模块操作类错误，对于 projectController

	300 - 399 ： 钱包相关的错误，对于walletController

	400 - 499 ： 商家/分销/代理商 特有类型错误，如商家不存在/； 对应 mallController

	500 - 599  :  设备操作模块，相关错误;

	600 - 699 ： 各种系统类、系统配置类的错误码。对于systemController, 系统异常等。
	*/
	/***请求参数不合法***/
	PARAMETER_ERROR(100, "请求参数不合法"),
	/***请求参数不合法***/
	PARAMETER_ALL(99, "请求参数不全"),
	/***用户名不存在***/
	LOGIN_FAILED_NAME(101,"用户不存在"),
	/***密码不正确***/
	LOGIN_FAILED_PASSWORD(102,"密码不正确"),
	
	/***已被锁定***/
	LOCKED(103,"已被锁定"),
	/***暂无权限***/
	PERMISSION_NO(104, "暂无权限"),
	/***账号被冻结***/
	LOGIN_FREEZE(106,"账号被冻结"),
	/***手机号已被注册***/
	REGISTER_PHONE(108,"手机号已被注册"),
	/***授权登录失败***/
	AUTH_LOGIN_FAIL(109,"授权登录失败"),
	
	/***TOKEN过期***/
	TOKEN_OVER(110, "token已过期"),
	/***TOKEN不存在***/
	TOKEN_NULL(112, "token不存在"),
	/***短信验证码发送失败***/
	CODE_PHONE(114,"短信验证码发送失败"),
	/***原密码不正确***/
	OLD_FAILED_PASSWORD(115,"原密码不正确"),
	
	/***验证码错误***/
	CAPTCHA_ERROR(121, "验证码不正确"),	
	/***验证码过期***/
	CAPTCHA_NULL(122, "验证码过期"),
	
	/****文件上传失败*******/
	UPLOADFILE_FAIL(160,"文件上传失败"),
	
	/***暂无操作权限***/
	NOT_AUTHORITY(209, "暂无权限"),

	/***暂无权限***/
	PROJECT_STEP(230, "暂无权限，所属阶段有误"),
	
	/***未知错误***/
	FAIL_NO_KNOW(600, "未知逻辑错误"),
	/****空指针异常******/
	NULL_EXCEPTION(601, "请求数据有误"),
	/****系统其他异常******/
	OTHER_EXCEPTION(602, "系统异常"),
	;
	
	/**状态码**/
	private int code;
	/**信息**/
    private String message;

    SystemError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
	
	
}
