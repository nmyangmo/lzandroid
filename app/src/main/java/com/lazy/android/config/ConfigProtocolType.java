package com.lazy.android.config;

public final class ConfigProtocolType {

//	获得http协议内容的请求
	public static final int TEST = 1000;

//	sample网络请求开始
	/**
	 * SAMPLE GET请求
	 */
	public static final int SAMPLE_GET = 0;
	/**
	 * SAMPLE UPLOAD请求
	 */
	public static final int SAMPLE_UPLOAD = SAMPLE_GET + 1;
	/**
	 *  SAMPLE POST请求
	 */
	public static final int SAMPLE_POST = SAMPLE_UPLOAD + 1;
	/**
	 *  SAMPLE LIST请求
	 */
	public static final int SAMPLE_LIST = SAMPLE_POST + 1;
	/**
	 *  SAMPLE DELETE请求
	 */
	public static final int SAMPLE_DELETE = SAMPLE_LIST + 1;
	/**
	 *  签名微信的订单
	 */
	public static final int WXPAY_GETSIGN = SAMPLE_DELETE + 1;
	/**
	 *  微信订单的预下
	 */
	public static final int WXPAY_ORDERINIT = WXPAY_GETSIGN + 1;







//	sample网络请求结束


//	xiaobai网络请求开始
	/**
	 *  发送验证码请求
	 */
	public static final int GETSMSCODE = 0;
	/**
	 *  注册会员网络请求
	 */
	public static final int REGISTER = GETSMSCODE + 1;
	/**
	 *  会员登录网络请求
	 */
	public static final int LOGIN = REGISTER + 1;
	/**
	 *  提交新的手机号
	 */
	public static final int NEWTELNUM = LOGIN + 1;
	/**
	 *  会员第三方登录网络请求
	 */
	public static final int THIRDLOGIN = NEWTELNUM + 1;
	/**
	 *  验证码登录会员
	 */
	public static final int SMSLOGIN = THIRDLOGIN + 1;
	/**
	 *  验证码登录会员
	 */
	public static final int FORGOTPASSWORD = SMSLOGIN + 1;

	/**
	 *  发布show的网络请求
	 */
	public static final int SHOWADD = FORGOTPASSWORD + 1;

	/**
	 *  获得show的发布类型
	 */
	public static final int SHOWADD_TYPE = SHOWADD + 1;

	/**
	 *  获得show的宠物列表
	 */
	public static final int SHOWADD_MYPETS = SHOWADD_TYPE + 1;


	/**
	 *  个人资料的保存
	 */
	public static final int PERSONINFO_SAVE = SHOWADD_MYPETS + 1;
	/**
	 *  个人资料的保存
	 */
	public static final int HEADIMG_SAVE = PERSONINFO_SAVE + 1;
	/**
	 *   获得所有宠物的类别
	 */
	public static final int GET_ALLPETS_TYPE = HEADIMG_SAVE + 1;
	/**
	 *   添加宠物
	 */
	public static final int ADD_PET = GET_ALLPETS_TYPE + 1;
	/**
	 *   编辑宠物
	 */
	public static final int EDIT_PET = ADD_PET + 1;

	/**
	 *   获得宠物信息
	 */
	public static final int GET_PET = EDIT_PET + 1;


	/**
	 * 支付宝支付获取调起字符串
	 */
	public static final int PAY_ALIPAY = GET_ALLPETS_TYPE + 1;

	/**
	 * 微信支付获取调起字符串
	 */
	public static final int PAY_WXPAY = PAY_ALIPAY + 1;


}
