package com.lazy.android.xiaobai.ui.common;

import android.os.Bundle;

import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.basefunc.LZHtml.LZWebView;
import com.lazy.android.basefunc.LZUtils.UtilsShared;
import com.lazy.android.baseui.base.LZBaseActivityI;
import com.lazy.android.xiaobai.data.WebviewIntentData;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by chenglei on 16/3/17.
 */
@ContentView(R.layout.lz_common_webview_activity)
public class CommonWebViewActivity extends LZBaseActivityI {

	@ViewInject(R.id.common_webview)
	LZWebView webView;
	private WebviewIntentData data;
	private String WebviewUrl = "";
	private String refresh = "false";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		data = (WebviewIntentData) getIntent().getExtras().getSerializable("intentdata");
		WebviewUrl = data.getUrl();
		refresh = data.getRefresh();
		webView.showWebView(WebviewUrl);
		initCommonHead();
	}


	@Override
	public void onResume() {
		super.onResume();
			webView.showWebView(WebviewUrl);
	}


//	页面刷新的方法
	public void refresh(){
		if(UtilsShared.getBoolean(this, ConfigStaticType.SettingField.LOGIN_SUCCESS,false)){
			webView.showWebView(WebviewUrl);
		}
	}

	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle(data.getName());
	}


}
