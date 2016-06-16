package com.lazy.android.config;

/**
 * 系统配置
 * 
 * @author
 * 
 */
public class ConfigSystem {
	/**包名*/
	public final static String PACKAGE_NAME = "com.lazy.android";
	/**每页显示记录条数*/
	public final static int PAGE_SIZE = 10;
	/**终端标记*/
	public static final String PLATFORM = "android";
	/**是否开启DEBUG*/
	public static final boolean DEBUG = true;
	/**是否开启DEBUG*/
	public static final boolean STAT_ENABLE = DEBUG;
	/**上传错误信息开启*/
	public static final boolean UPLOAD_CRASH_INFO = false;
	/**项目名称*/
	public static final String PRODUCT_NAME = "lzandroid";
	/**默认城市的ID*/
    public static final int DEFAULT_CITY = 3;
    /**密码最小长度*/
	public static final int PASSWORD_MINLEN = 6;
	/**短信验证码长度*/
	public static final int VERIFYCODE_LEN = 4;
	/**短信验证码倒计时长*/
	public static final int WAIT_TIME = 60;
	/**价格格式化串*/
	public static final String PRICE_FORMATER = "￥:%.0f";
	/**图片质量*/
	public static final int IMAGE_QUALITY = 70;
	public static final String  PACK_ID = "packID";	
	public static final String  ORDER_ID = "orderID";	
	public static final String MARKET_PRICE = "marketPrice";
	public static final String PRODUCT_NAMES = "productName";
	public static final String PRODUCT_DESCRIPTION = "productDescription";
	public static final String PRODUCT_PRICE = "productPrice";
	/**渠道关键字*/
	public final static String CHANNEL_KEY = "友盟";
	/**渠道号*/
	public final static String CHANNEL_NAME = "UMENG";
	/**图片服务器根地址*/
	public static String IMAGE_SERVER_ROOT = "";
	/**升级包文件名*/
	public static final String UPDATE_APK = "update.apk";
	/**最大任务数*/
	public static final int MAX_TASK_SIZE = 5;
	/** 获取短信验证码技能CD的时间 */
	public static  final int GETSMSCODE_TIME = 10;



//	上传服务器地址
	public static final String UPLOAD_ROOT = "http://115.159.183.250/";

//	接口服务器根地址
	public final static String SERVER_ROOT = "http://i.ixbai.com/";

//	微信支付appid
	public final static String WX_APPID = "wx9e5ba61fef2f3da5";

//	微信支付商户号
	public final static String WX_MCHID = "1260194401";




	/**
	 * H5分享页面私钥 0123456789abcdef
	 */
	public final static String H5_PRIVATE_KEY = "A0d8E1tm2Y&rQ*i5";
    /**
     * 百度导航的AK
     */
    public final static String BAIDUMAP_AK = "MiQK77G25yQ6qvn9GEBI6okO";
    /**
     * 微信登录的key
     */
    public static String WECHAT_APPID="wx711e29e7c52bb3d9";
    public static String WECHAT_APPSECRET="392be73cd37e93e1e38bf5e869a2cc0a";
    /**
     * QQ空间分享的key
     */
    public static String QZONE_APPID="1104506510";
    public static String QZONE_APPKEY="PuomETsrWT38irxC";
}
