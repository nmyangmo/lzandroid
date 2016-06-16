package com.lazy.android.xiaobai.ui.personSet.protocol;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;
import com.lazy.android.xiaobai.ui.register.data.UserInfoBean;
import com.lazy.android.xiaobai.ui.register.data.UserinfoSharedUtils;

import org.json.JSONException;

/**
 * Created by chenglei on 16/4/27.
 */
public class PersoninfoSetProtocol extends LZHttpProtocolHandlerBase {
	private UserInfoBean userInfoBean;

	public PersoninfoSetProtocol(Context context, LZHttpIProtocolCallback callBack, String headimg, String nickname, String sex, String city) {
		super(context, callBack);
		mSubUrl = mBaseUrl + "/usr/member!saveInfo.do";
		mProtocolType = ConfigProtocolType.PERSONINFO_SAVE;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.UPLOAD);
		requestInfo.addQueryString("nickname", nickname);
		requestInfo.addQueryString("sex", ConfigStaticType.SEX.toint(sex) + "");
		requestInfo.addQueryString("city",city);
//		try {
//			requestInfo.addQueryString("city", URLEncoder.encode(city,"utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		this.setmRequestInfo(requestInfo);
		this.sendRequest();
	}

	@Override
	public void afterParse() {
		new UserinfoSharedUtils().SharedUpdate(mContext,userInfoBean);
	}

	@Override
	public boolean parse() {
		try {
			userInfoBean = (UserInfoBean) new UserInfoBean().toObject(mResponeVO.getJSONObject("data").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return true;
	}
}
