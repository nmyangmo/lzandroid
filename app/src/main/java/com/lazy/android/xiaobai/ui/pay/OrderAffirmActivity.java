package com.lazy.android.xiaobai.ui.pay;

import android.os.Bundle;

import com.lazy.android.R;
import com.lazy.android.baseui.base.LZBaseActivityI;

import org.xutils.view.annotation.ContentView;

/**
 * Created by chenglei on 16/3/14.
 */
@ContentView(R.layout.xb_orderaffirm_activity)
public class OrderAffirmActivity extends LZBaseActivityI {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
	}




	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("确认订单");
	}


}
