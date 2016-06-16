package com.lazy.android.sample.divview.circleimageview;

import android.os.Bundle;

import com.lazy.android.R;
import com.lazy.android.baseui.base.LZBaseActivityI;

import org.xutils.view.annotation.ContentView;

/**
 * Created by chenglei on 16/3/8.
 */
@ContentView(R.layout.sample_divview_circleimageview)
public class CircleImageViewActivity extends LZBaseActivityI {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("circleimageview");
	}



}
