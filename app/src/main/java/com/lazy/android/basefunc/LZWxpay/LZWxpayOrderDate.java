package com.lazy.android.basefunc.LZWxpay;

import com.lazy.android.config.ConfigSystem;
import com.lazy.android.basefunc.LZUtils.UtilsUniqueId;

/**
 * Created by chenglei on 16/4/24.
 */
public class LZWxpayOrderDate {

	private String appid;	//应用ID
	private String mch_id;	//商户号
	private String device_info;	//设备号
	private String nonce_str;	//随机字符串
	private String sign;		//签名
	private String body;		//商品描述
	private String detail;		//商品详情
	private String attach;		//附加数据
	private String out_trade_no;	//商户订单号
	private String fee_type;		//货币类型
	private int total_fee;		//总金额
	private String spbill_create_ip;	//终端IP
	private String time_start;			//交易起始时间
	private String time_expire;			//交易结束时间
	private String goods_tag;			//商品标记
	private String notify_url;			//通知地址
	private String trade_type;			//交易类型
	private String limit_pay;			//指定支付方式


	public LZWxpayOrderDate( String out_trade_no,int total_fee,String body, String detail ) {
		this.body = body;
		this.detail = detail;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;

		this.appid = ConfigSystem.WX_APPID;
		this.mch_id = ConfigSystem.WX_MCHID;
		this.nonce_str = UtilsUniqueId.getInstance().genericId()+"";






	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}


//	需要加密的订单字符串
	public String getkey(){


		String signtem = "";
		if(!(this.appid == null)){
			signtem += ("appid="+ appid);
		}
		if(!(this.attach == null)){
			signtem += ("&attach="+ attach);
		}
		if(!(this.body == null)){
			signtem += ("&body="+ body);
		}
		if(!(this.detail == null)){
			signtem += ("&detail="+ detail);
		}
		if(!(this.device_info == null)){
			signtem += ("&device_info="+ device_info);
		}
		if(!(this.fee_type == null)){
			signtem += ("&fee_type="+ fee_type);
		}
		if(!(this.goods_tag == null)){
			signtem += ("&goods_tag="+ goods_tag);
		}
		if(!(this.limit_pay == null)){
			signtem += ("&limit_pay="+ limit_pay);
		}
		if(!(this.mch_id == null)){
			signtem += ("&mch_id="+ mch_id);
		}
		if(!(this.nonce_str == null)){
			signtem += ("&nonce_str="+ nonce_str);
		}
		if(!(this.notify_url == null)){
			signtem += ("&notify_url="+ notify_url);
		}
		if(!(this.out_trade_no == null)){
			signtem += ("&out_trade_no="+ out_trade_no);
		}
		if(!(this.spbill_create_ip == null)){
			signtem += ("&spbill_create_ip="+ spbill_create_ip);
		}
		if(!(this.time_start == null)){
			signtem += ("&time_start="+ time_start);
		}
		if(!(this.time_expire == null)){
			signtem += ("&time_expire="+ time_expire);
		}
		if(this.total_fee!=0){
			signtem += ("&total_fee="+ total_fee);
		}
		if(!(this.trade_type == null)){
			signtem += ("&trade_type="+ trade_type);
		}

















		return signtem;
	}


	@Override
	public String toString() {
		return "LZWxpayOrderDate{" +
			"appid='" + appid + '\'' +
			", mch_id='" + mch_id + '\'' +
			", device_info='" + device_info + '\'' +
			", nonce_str='" + nonce_str + '\'' +
			", sign='" + sign + '\'' +
			", body='" + body + '\'' +
			", detail='" + detail + '\'' +
			", attach='" + attach + '\'' +
			", out_trade_no='" + out_trade_no + '\'' +
			", fee_type='" + fee_type + '\'' +
			", total_fee=" + total_fee +
			", spbill_create_ip='" + spbill_create_ip + '\'' +
			", time_start='" + time_start + '\'' +
			", time_expire='" + time_expire + '\'' +
			", goods_tag='" + goods_tag + '\'' +
			", notify_url='" + notify_url + '\'' +
			", trade_type='" + trade_type + '\'' +
			", limit_pay='" + limit_pay + '\'' +
			'}';
	}
}
