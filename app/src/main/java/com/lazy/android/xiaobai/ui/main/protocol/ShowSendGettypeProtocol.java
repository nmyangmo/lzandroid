package com.lazy.android.xiaobai.ui.main.protocol;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;
import com.lazy.android.basefunc.LZJson.LZJson;
import com.lazy.android.xiaobai.ui.main.data.ShowsendTypeBean;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by chenglei on 16/3/22.
 */
public class ShowSendGettypeProtocol extends LZHttpProtocolHandlerBase {

	public ArrayList<ShowsendTypeBean> resultArray = new ArrayList<ShowsendTypeBean>();


	public ShowSendGettypeProtocol(Context context, LZHttpIProtocolCallback callBack) {
		super(context, callBack);
		mSubUrl = mBaseUrl + "/usr/mShare!getLables.do";
		mProtocolType = ConfigProtocolType.SHOWADD_TYPE;
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
			JSONArray result = mResponeVO.getJSONArray("data");
			for(int i = 0 ; i < result.length() ; i++){
				ShowsendTypeBean object = new ShowsendTypeBean();
				resultArray.add((ShowsendTypeBean) new LZJson().fromJson(result.get(i).toString(), object));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}



}
