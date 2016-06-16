package com.lazy.android.xiaobai.ui.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lazy.android.R;
import com.lazy.android.baseui.base.LZBaseActivityI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by chenglei on 16/3/11.
 */
@ContentView(R.layout.xb_pay_paysult_activity)
public class PayResultActivity extends LZBaseActivityI {

	@ViewInject(R.id.paysult_ico) private ImageView paysult_ico;
	@ViewInject(R.id.paysult_text) private TextView paysult_text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	//	点击返回订单
	@Event(R.id.paysult_button_order)
	private void paysult_button_order_Event(View view){

	}


	//	点击返回商城
	@Event(R.id.paysult_button_shop)
	private void paysult_button_shop_Event(View view){

	}





	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("支付结果");
	}


}
