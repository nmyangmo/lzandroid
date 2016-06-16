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
public class EditMypetProtocol extends LZHttpProtocolHandlerBase {

	public Boolean result;

	public EditMypetProtocol(Context context, LZHttpIProtocolCallback callBack, int petid, String petname, int petsex, int pettype, long petbrithday, long petinday) {
		super(context, callBack);
		mSubUrl = mImgUrl + "/usr/member!editpet.do";
		mProtocolType = ConfigProtocolType.EDIT_PET;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.POST);
		requestInfo.addQueryString("petid",petid+"");
		requestInfo.addQueryString("petname", petname);
		requestInfo.addQueryString("petsex", petsex + "");
		requestInfo.addQueryString("pettype", pettype + "");
		requestInfo.addQueryString("petbrithday", petbrithday/1000 + "");
		requestInfo.addQueryString("petinday", petinday/1000 + "");
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
