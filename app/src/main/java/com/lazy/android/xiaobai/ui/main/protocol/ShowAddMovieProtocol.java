package com.lazy.android.xiaobai.ui.main.protocol;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;

/**
 * Created by chenglei on 16/3/22.
 */
public class ShowAddMovieProtocol extends LZHttpProtocolHandlerBase {


	public ShowAddMovieProtocol(Context context, LZHttpIProtocolCallback callBack, String content, String movie, String movieImgPaht, int lable, int peitid) {
		super(context, callBack);
		mSubUrl = mImgUrl + "usr/mShare!addShow.do";
//		mSubUrl = mTextUrl;
		mProtocolType = ConfigProtocolType.SHOWADD;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.UPLOAD);
		requestInfo.addQueryString("content",content);
		requestInfo.addQueryString("label",lable+"");
		requestInfo.addQueryString("petid",peitid+"");
		requestInfo.addFormFieldParam("movie",movie);
		requestInfo.addFormFieldParam("movieImg",movieImgPaht);
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
