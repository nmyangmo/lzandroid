package com.lazy.android.xiaobai.ui.common;

import android.os.Bundle;

import com.lazy.android.R;
import com.lazy.android.baseui.base.LZBaseActivity;

import org.xutils.view.annotation.ContentView;

/**
 * Created by chenglei on 16/3/11.
 */
@ContentView(R.layout.xb_common_comment_activity)
public class CommentActivity extends LZBaseActivity {


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
		mCommonHead.setMiddleTitle("发表评论");
	}


}
