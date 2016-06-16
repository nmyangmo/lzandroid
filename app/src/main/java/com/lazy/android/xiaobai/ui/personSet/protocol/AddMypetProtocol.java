package com.lazy.android.xiaobai.ui.personSet.protocol;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;

import org.json.JSONException;

/**
 * Created by chenglei on 16/4/27.
 */
public class AddMypetProtocol extends LZHttpProtocolHandlerBase {

	public Boolean result;

	public AddMypetProtocol(Context context, LZHttpIProtocolCallback callBack, String petname, int petsex, int pettype, long petbrithday, long petinday, String headimg) {
		super(context, callBack);
		mSubUrl = mImgUrl + "/usr/member!addpet.do";
		mProtocolType = ConfigProtocolType.ADD_PET;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.UPLOAD);
		requestInfo.addQueryString("petname", petname);
		requestInfo.addQueryString("petsex", petsex + "");
		requestInfo.addQueryString("pettype", pettype + "");
		requestInfo.addQueryString("petbrithday", petbrithday/1000 + "");
		requestInfo.addQueryString("petinday", petinday/1000 + "");
		requestInfo.addFormFieldParam("headimg", headimg);
		this.setmRequestInfo(requestInfo);
		this.sendRequest();
	}

	@Override
	public void afterParse() {

	}

	@Override
	public boolean parse() {

		try {
			int resultint = mResponeVO.getInt("result");
			if (resultint == 1) {
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
