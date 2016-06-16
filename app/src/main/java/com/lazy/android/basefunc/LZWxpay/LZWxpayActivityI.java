package com.lazy.android.basefunc.LZWxpay;

import android.os.Bundle;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseui.base.LZBaseActivityI;

/**
 * Created by chenglei on 16/4/24.
 */
public class LZWxpayActivityI extends LZBaseActivityI {

	private String key = "aLN1GTL4MlQJkPB3prteCLcH2gEljdix";
//	订单对象
	private LZWxpayOrderDate date;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		1、将订单用key签名，获得sign
		LZWxpayOrderDate date = new LZWxpayOrderDate("order",1000,"tile","content");
		new LZWxpayGetsignProtocol(this,this,date.getkey(),key);

		new LZWxpayOrderProtocol(this,this,date);
	}


	@Override
	public void onHttpStart(LZHttpProtocolHandlerBase protocol) {
		super.onHttpStart(protocol);
	}

	@Override
	public void onHttpProgress(long total, long current, boolean isUploading) {
		super.onHttpProgress(total, current, isUploading);
	}

	@Override
	public void onHttpFailure(Exception except, String msg) {
		super.onHttpFailure(except, msg);
	}

	@Override
	public void onHttpSuccess(LZHttpProtocolHandlerBase protocol) {
		super.onHttpSuccess(protocol);
		if(protocol.getProtocolStatus() == ConfigStaticType.ProtocolStatus.RESULT_SUCCESS){
			if(protocol.getProtocolType() == ConfigProtocolType.WXPAY_GETSIGN){
				LZWxpayGetsignProtocol lzWxpayGetsignProtocol = (LZWxpayGetsignProtocol)protocol;
				String sign = lzWxpayGetsignProtocol.sign;
				date.setSign(sign);
//				拿到组装的数据再次发送请求

			}else if(protocol.getProtocolType() == ConfigProtocolType.WXPAY_ORDERINIT){

			}
		}

	}
}
