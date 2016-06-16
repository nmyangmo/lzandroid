package com.lazy.android.xiaobai.ui.main.protocol;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;

import java.util.ArrayList;

/**
 * Created by chenglei on 16/3/22.
 */
public class ShowAddPhotoProtocol extends LZHttpProtocolHandlerBase {


	public ShowAddPhotoProtocol(Context context, LZHttpIProtocolCallback callBack, String content, ArrayList photos, int lable, int peitid) {
		super(context, callBack);
		mSubUrl = mImgUrl + "usr/mShare!addShow.do";
//		mSubUrl = mTextUrl;
		mProtocolType = ConfigProtocolType.SHOWADD;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.UPLOAD);
		requestInfo.addQueryString("content",content);
		requestInfo.addQueryString("label",lable+"");
		requestInfo.addQueryString("petid",peitid+"");

		for(int i = 0 ;i < photos.size() ; i++){
			requestInfo.addFormFieldParam("photo"+i,photos.get(i).toString());
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
