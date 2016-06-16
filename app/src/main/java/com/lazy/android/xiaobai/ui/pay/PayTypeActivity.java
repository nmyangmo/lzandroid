package com.lazy.android.xiaobai.ui.pay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.basefunc.LZUtils.UtilsShared;
import com.lazy.android.baseui.base.LZBaseActivityI;
import com.lazy.android.xiaobai.data.WebviewIntentData;
import com.lazy.android.xiaobai.pay.XiaobaiAlipayActivity;
import com.lazy.android.xiaobai.pay.XiaobaiWxpayActivity;
import com.lazy.android.xiaobai.ui.common.CommonWebViewActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by chenglei on 16/3/11.
 */
@ContentView(R.layout.xb_pay_paytype_activity)
public class PayTypeActivity extends LZBaseActivityI {

	private Boolean payClick = false;

	private String orderid;
	@ViewInject(R.id.paytype_nopay) private LinearLayout paytype_nopay;
	@ViewInject(R.id.paytype_alipay) private LinearLayout paytype_alipay;
	@ViewInject(R.id.paytype_wxpay) private LinearLayout paytype_wxpay;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
		orderid = getIntent().getStringExtra("orderid");
	}


	//	暂不支付
	@Event(R.id.paytype_nopay)
	private void paytype_nopay_Event(View view){
			this.finish();
	}

	//	支付宝支付
	@Event(R.id.paytype_alipay)
	private void paytype_alipay_Event(View view){
		payClick = true;
		Intent intent = new Intent(this, XiaobaiAlipayActivity.class);
		intent.putExtra("orderid", orderid);
		startActivity(intent);
	}

	//	微信支付
	@Event(R.id.paytype_wxpay)
	private void paytype_wxpay_Event(View view){
		payClick = true;
		Intent intent = new Intent(this, XiaobaiWxpayActivity.class);
		intent.putExtra("orderid", orderid);
		startActivity(intent);
	}


	@Override
	public void onResume() {
		super.onResume();
		if(UtilsShared.getBoolean(this, ConfigStaticType.SettingField.XB_ISPAYSUCCESS,false)){
			WebviewIntentData webviewIntentData = new WebviewIntentData("待发货","supplies-orders.html?status=3");
			Intent intent = new Intent(this, CommonWebViewActivity.class);
			intent.putExtra("intentdata",webviewIntentData);
			startActivity(intent);
			UtilsShared.setBoolean(this, ConfigStaticType.SettingField.XB_ISPAYSUCCESS, false);
			this.finish();
		}
	}

	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("支付方式");
	}



}
