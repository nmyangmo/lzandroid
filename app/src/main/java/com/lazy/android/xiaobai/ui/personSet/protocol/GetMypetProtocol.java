package com.lazy.android.xiaobai.ui.personSet.protocol;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;
import com.lazy.android.basefunc.LZJson.LZJson;
import com.lazy.android.xiaobai.ui.personSet.data.PetInfoBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chenglei on 16/4/27.
 */
public class GetMypetProtocol extends LZHttpProtocolHandlerBase {

	public PetInfoBean bean = new PetInfoBean();

	public GetMypetProtocol(Context context, LZHttpIProtocolCallback callBack, int id) {
		super(context, callBack);
		mSubUrl = mBaseUrl + "/usr/member!getpet.do?petid=" + id;
		mProtocolType = ConfigProtocolType.GET_PET;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.GET);
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
				JSONObject data = mResponeVO.getJSONObject("data");
				bean = (PetInfoBean)(new LZJson().fromJson(data.toString(),bean));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
}
