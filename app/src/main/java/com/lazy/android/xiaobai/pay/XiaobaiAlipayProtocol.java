package com.lazy.android.xiaobai.pay;

import android.content.Context;
import android.widget.Toast;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;

/**
 * Created by chenglei on 16/3/22.
 */
public class XiaobaiAlipayProtocol extends LZHttpProtocolHandlerBase {

	public String payInfo;


	public XiaobaiAlipayProtocol(Context context, LZHttpIProtocolCallback callBack, String orderid) {
		super(context, callBack);
		mSubUrl = "http://www.ixbai.com/usr/apay!apayinfo.do?outtradeno=" + orderid;
		mProtocolType = ConfigProtocolType.PAY_ALIPAY;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.GET);
		this.setmRequestInfo(requestInfo);
		this.sendRequest();

	}

	@Override
	public void afterParse() {
	}

	@Override
	public boolean parse() {
		payInfo = mJson;
		Toast.makeText(mContext, mJson, Toast.LENGTH_SHORT).show();
		return false;
	}


}
