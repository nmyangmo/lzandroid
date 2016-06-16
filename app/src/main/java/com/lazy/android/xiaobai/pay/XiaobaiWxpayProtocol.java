package com.lazy.android.xiaobai.pay;

import android.content.Context;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chenglei on 16/3/22.
 */
public class XiaobaiWxpayProtocol extends LZHttpProtocolHandlerBase {

	public PayReq req;

	public XiaobaiWxpayProtocol(Context context, LZHttpIProtocolCallback callBack, String orderid) {
		super(context, callBack);
		mSubUrl = "http://www.ixbai.com/tenpay/tpay!tpayinfo.do?ordersn=" + orderid;
		mProtocolType = ConfigProtocolType.PAY_WXPAY;
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
			JSONObject data = mResponeVO.getJSONObject("data");
			req = new PayReq();
			req.appId = data.getString("appid");
			req.partnerId = data.getString("partnerid");
			req.prepayId = data.getString("prepayid");
			req.packageValue = data.getString("package");
			req.nonceStr = data.getString("noncestr");
			req.timeStamp = data.getString("timestamp");
			req.sign = data.getString("sign");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}


}
