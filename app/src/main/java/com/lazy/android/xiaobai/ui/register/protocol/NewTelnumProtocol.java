package com.lazy.android.xiaobai.ui.register.protocol;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;

import org.json.JSONException;

/**
 * Created by chenglei on 16/3/22.
 */
public class NewTelnumProtocol extends LZHttpProtocolHandlerBase {

	public Boolean result;


	public NewTelnumProtocol(Context context, LZHttpIProtocolCallback callBack, String telnum, String smscode) {
		super(context, callBack);
		mSubUrl = mBaseUrl + "usr/member!regMobile.do";
		mProtocolType = ConfigProtocolType.NEWTELNUM;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.POST);
		requestInfo.addQueryString("mobile", telnum);
		requestInfo.addQueryString("smscode", smscode);
		this.setmRequestInfo(requestInfo);
		this.sendRequest();
	}

	@Override
	public void afterParse() {

	}

	@Override
	public boolean parse() {
		try {
			if (mResponeVO.getInt("result") == 1) {
				result = true;
			} else {
				result = false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}


}
