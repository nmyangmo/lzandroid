package com.lazy.android.sample.divview.netlist;

import android.os.Bundle;

import com.lazy.android.R;
import com.lazy.android.baseui.base.LZBaseActivityI;

import org.xutils.view.annotation.ContentView;

/**
 * Created by chenglei on 16/6/16.
 */
@ContentView(R.layout.sample_divview_netlist_activity)
public class NetListActivity extends LZBaseActivityI {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
	}



	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setMiddleTitle("网络请求页面列表");
	}

}
