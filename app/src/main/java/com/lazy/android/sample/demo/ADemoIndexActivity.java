package com.lazy.android.sample.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lazy.android.R;
import com.lazy.android.baseui.base.LZBaseActivityI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by chenglei on 16/3/26.
 */
@ContentView(R.layout.demo_index_activity)
public class ADemoIndexActivity extends LZBaseActivityI {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
	}


	@Event(R.id.surfaceview)
	private void surfaceview_click(View view){
		startActivity(new Intent(this,DemoSurfaceViewActivity.class));
	}


	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("各种功能类的首页");
	}

}
