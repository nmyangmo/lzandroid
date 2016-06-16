package com.lazy.android.xiaobai.ui.personSet.protocol;

import android.content.Context;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;
import com.lazy.android.basefunc.LZJson.LZJson;
import com.lazy.android.xiaobai.ui.personSet.data.PetTypeBean;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by chenglei on 16/4/27.
 */
public class GetpetTypeProtocol extends LZHttpProtocolHandlerBase {

	public ArrayList<PetTypeBean> resultArray = new ArrayList<PetTypeBean>();


	public GetpetTypeProtocol(Context context, LZHttpIProtocolCallback callBack) {
		super(context, callBack);
		mSubUrl = mBaseUrl + "/usr/mShare!getCats.do";
//		mSubUrl = mTextUrl;
		mProtocolType = ConfigProtocolType.GET_ALLPETS_TYPE;
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
				PetTypeBean object = new PetTypeBean();
				resultArray.add((PetTypeBean) new LZJson().fromJson(result.get(i).toString(), object));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
}
