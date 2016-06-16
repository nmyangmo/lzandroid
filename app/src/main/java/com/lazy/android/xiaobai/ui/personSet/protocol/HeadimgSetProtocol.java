package com.lazy.android.xiaobai.ui.personSet.protocol;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;

/**
 * Created by chenglei on 16/4/27.
 */
public class HeadimgSetProtocol extends LZHttpProtocolHandlerBase {


	public HeadimgSetProtocol(Context context, LZHttpIProtocolCallback callBack, String headimg, String type, String petid) {
		super(context, callBack);
		mSubUrl = mImgUrl + "usr/member!uploadAvatar.do";
		mProtocolType = ConfigProtocolType.HEADIMG_SAVE;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.UPLOAD);
		requestInfo.addQueryString("type", type);
		requestInfo.addQueryString("petid",petid);
		requestInfo.addFormFieldParam("headimg", headimg);
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
