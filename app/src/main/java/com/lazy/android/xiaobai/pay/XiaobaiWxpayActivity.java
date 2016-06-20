package com.lazy.android.xiaobai.pay;

import android.os.Bundle;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.basefunc.LZUtils.UtilsShared;
import com.lazy.android.baseui.base.LZBaseActivity;

/**
 * Created by chenglei on 16/5/4.
 */
public class XiaobaiWxpayActivity extends LZBaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String orderid = getIntent().getStringExtra("orderid");
		UtilsShared.setString(this, ConfigStaticType.SettingField.XB_ORDERID, orderid);
		new XiaobaiWxpayProtocol(this,this,orderid);
	}


	@Override
	public void onHttpStart(LZHttpProtocolHandlerBase protocol) {
		super.onHttpStart(protocol);
	}

	@Override
	public void onHttpProgress(long total, long current, boolean isUploading) {
		super.onHttpProgress(total, current, isUploading);
	}

	@Override
	public void onHttpFailure(Exception except, String msg) {
		super.onHttpFailure(except, msg);
	}

	@Override
	public void onHttpSuccess(LZHttpProtocolHandlerBase protocol) {
		super.onHttpSuccess(protocol);
		PayReq data = ((XiaobaiWxpayProtocol)protocol).req;
		final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
		msgApi.registerApp(data.appId);
		msgApi.sendReq(data);
		this.finish();
	}
}
