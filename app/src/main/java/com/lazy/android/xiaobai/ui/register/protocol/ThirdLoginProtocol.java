package com.lazy.android.xiaobai.ui.register.protocol;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;
import com.lazy.android.xiaobai.ui.register.data.UserInfoBean;
import com.lazy.android.xiaobai.ui.register.data.UserInfoThird;
import com.lazy.android.xiaobai.ui.register.data.UserinfoSharedUtils;

import org.json.JSONException;

/**
 * Created by chenglei on 16/3/22.
 */
public class ThirdLoginProtocol extends LZHttpProtocolHandlerBase {
	private UserInfoBean userInfoBean;

	public ThirdLoginProtocol(Context context, LZHttpIProtocolCallback callBack, UserInfoThird third) {
		super(context, callBack);
		mSubUrl = mBaseUrl + "usr/memthr!tlogin.do";
		mProtocolType = ConfigProtocolType.THIRDLOGIN;
		LZHttpRequestInfo requestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.POST);
		requestInfo.addQueryString("token", third.getToken());
		requestInfo.addQueryString("nickname",third.getNickname());
		requestInfo.addQueryString("faceimg", third.getFaceimg());
		requestInfo.addQueryString("thirdtype", third.getThirdtype());
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


	public UserInfoBean getUserInfoBean() {
		return userInfoBean;
	}
}
