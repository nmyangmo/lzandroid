package com.lazy.android.xiaobai.ui.register.protocol;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;

/**
 * Created by chenglei on 16/3/22.
 */
public class GetsmsProtocol extends LZHttpProtocolHandlerBase {


	public GetsmsProtocol(Context context, LZHttpIProtocolCallback callBack, String mobile, int type) {
		super(context, callBack);
		mSubUrl = mBaseUrl + "/usr/member!sendCode.do";
		mProtocolType = ConfigProtocolType.GETSMSCODE;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.POST);
		requestInfo.addQueryString("mobile", mobile);
		requestInfo.addQueryString("type","" + type);
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
