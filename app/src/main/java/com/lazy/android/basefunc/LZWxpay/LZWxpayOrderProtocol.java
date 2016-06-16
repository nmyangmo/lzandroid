package com.lazy.android.basefunc.LZWxpay;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;

/**
 * Created by chenglei on 16/5/1.
 */
public class LZWxpayOrderProtocol extends LZHttpProtocolHandlerBase {

	public LZWxpayOrderProtocol(Context context, LZHttpIProtocolCallback callBack, LZWxpayOrderDate date) {
		super(context, callBack);
		mSubUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//		mSubUrl = mTextUrl;

		mProtocolType = ConfigProtocolType.WXPAY_ORDERINIT;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.POST);

		if(date.getAppid() != null){
			requestInfo.addQueryString("appid",date.getAppid());
		}

		if(date.getMch_id() != null){
			requestInfo.addQueryString("mch_id",date.getMch_id());
		}

		if(date.getDevice_info() != null){
			requestInfo.addQueryString("device_info",date.getDevice_info());
		}

		if(date.getNonce_str() != null){
			requestInfo.addQueryString("nonce_str",date.getNonce_str());
		}

		if(date.getSign() != null){
			requestInfo.addQueryString("sign",date.getSign());
		}

		if(date.getBody() != null){
			requestInfo.addQueryString("body",date.getBody());
		}

		if(date.getDetail() != null){
			requestInfo.addQueryString("detail",date.getDetail());
		}

		if(date.getAttach() != null){
			requestInfo.addQueryString("attach",date.getAttach());
		}

		if(date.getOut_trade_no() != null){
			requestInfo.addQueryString("out_trade_no",date.getOut_trade_no());
		}

		if(date.getFee_type() != null){
			requestInfo.addQueryString("fee_type",date.getFee_type());
		}

		if(date.getTotal_fee() != 0){
			requestInfo.addQueryString("total_fee",date.getTotal_fee()+"");
		}

		if(date.getSpbill_create_ip() != null){
			requestInfo.addQueryString("spbill_create_ip",date.getSpbill_create_ip());
		}

		if(date.getTime_start() != null){
			requestInfo.addQueryString("time_start",date.getTime_start());
		}

		if(date.getTime_expire() != null){
			requestInfo.addQueryString("time_expire",date.getTime_expire());
		}

		if(date.getGoods_tag() != null){
			requestInfo.addQueryString("goods_tag",date.getGoods_tag());
		}

		if(date.getNotify_url() != null){
			requestInfo.addQueryString("notify_url",date.getNotify_url());
		}

		if(date.getTrade_type() != null){
			requestInfo.addQueryString("trade_type",date.getTrade_type());
		}

		if(date.getLimit_pay() != null){
			requestInfo.addQueryString("limit_pay",date.getLimit_pay());
		}

		this.setmRequestInfo(requestInfo);
		this.sendRequest();

	}

	@Override
	public void afterParse() {

	}

	@Override
	public boolean parse() {
		return false;
	}
}
