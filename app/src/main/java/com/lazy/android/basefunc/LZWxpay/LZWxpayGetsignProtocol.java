package com.lazy.android.basefunc.LZWxpay;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by chenglei on 16/4/30.
 */
public class LZWxpayGetsignProtocol extends LZHttpProtocolHandlerBase {


	public String sign;

	public LZWxpayGetsignProtocol(Context context, LZHttpIProtocolCallback callBack, String str, String key) {
		super(context, callBack);
		mSubUrl = "http://cs.zhuli6.com/wxpay.php";
//		mSubUrl = mTextUrl;
		mProtocolType = ConfigProtocolType.WXPAY_GETSIGN;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.POST);
		requestInfo.addQueryString("str","str");
		requestInfo.addQueryString("key","key");
		this.setmRequestInfo(requestInfo);
		this.sendRequest();

	}

	@Override
	public void afterParse() {

	}

	@Override
	public boolean parse() {
		try {
			JSONObject result = (JSONObject) mResponeVO.getJSONArray("data").get(0);
			sign = result.getString("return");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
}
