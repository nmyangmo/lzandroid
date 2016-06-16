package com.lazy.android.xiaobai.ui.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.basefunc.LZUtils.UtilsShared;
import com.lazy.android.baseui.crumbs.CrumbsUmengSharedActivity;
import com.lazy.android.xiaobai.data.WebviewIntentData;
import com.lazy.android.xiaobai.pay.XiaobaiAlipayActivity;
import com.lazy.android.xiaobai.pay.XiaobaiWxpayProtocol;
import com.lazy.android.xiaobai.ui.pay.PayTypeActivity;
import com.lazy.android.xiaobai.ui.personSet.AddMypetActivity;
import com.lazy.android.xiaobai.ui.personSet.PersoninfoSetActivity;
import com.lazy.android.xiaobai.ui.register.activity.Forhtml_NewTelnumActivity;
import com.lazy.android.xiaobai.ui.register.activity.LoginActivity;
import com.lazy.android.xiaobai.ui.register.activity.NewTelnumActivity;

/**
 * Created by chenglei on 16/3/17.
 */
public class CommonWebViewObject {

	private Context context;
	private WebView mwebView;


	public CommonWebViewObject() {
	}

	public CommonWebViewObject(Context context,WebView mwebView) {
		this.context = context;
		this.mwebView = mwebView;
	}

	@JavascriptInterface
	public void toJump(String title,String url,String refresh) {
		WebviewIntentData webviewIntentData = new WebviewIntentData(title,url,refresh);
		Intent intent = new Intent(context,CommonWebViewActivity.class);
		intent.putExtra("intentdata",webviewIntentData);
		((Activity)context).startActivity(intent);
	}

	@JavascriptInterface
	public void toJump(String title,String url) {
		this.toJump(title, url, "false");
	}



	@JavascriptInterface
	public void toLogin(){
		((Activity)context).startActivity(new Intent(context, LoginActivity.class));
	}

	@JavascriptInterface
	public void toAddPet(){
		((Activity)context).startActivity(new Intent(context, AddMypetActivity.class));
	}

	@JavascriptInterface
	public void finishThis(){
		((Activity)context).finish();
	}

	@JavascriptInterface
	public void refresh(){
//		Toast.makeText(context, "对象refresh", Toast.LENGTH_SHORT).show();
//		((LZWebView)mwebView).refresh();
		((CommonWebViewActivity)context).refresh();
	}

	@JavascriptInterface
	public void getUserinfo(){
		((Activity)context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				int id = UtilsShared.getInt(context, ConfigStaticType.SettingField.XB_UID, 0);
				String token = UtilsShared.getString(context, ConfigStaticType.SettingField.XB_TOKEN, "");
				String url = "javascript:setUserinfo('" + id + "','" + token + "')";
				mwebView.loadUrl(url);
				mwebView.setWebViewClient(new WebViewClient() {
					@Override
					public boolean shouldOverrideUrlLoading(WebView view, String url) {
						return true;
					}
				});
			}
		});
	}

	@JavascriptInterface
	public void toAlipay(String orderid){
		Intent intent = new Intent(context, XiaobaiAlipayActivity.class);
		intent.putExtra("orderid",orderid);
		((Activity)context).startActivity(intent);
	}


	@JavascriptInterface
	public void toWxpay(String orderid){
		Intent intent = new Intent(context, XiaobaiWxpayProtocol.class);
		intent.putExtra("orderid",orderid);
		((Activity)context).startActivity(intent);
	}


	@JavascriptInterface
	public void toPay(String orderid){
		Intent intent = new Intent(context, PayTypeActivity.class);
		intent.putExtra("orderid",orderid);
		((Activity)context).startActivity(intent);
	}

//	绑定手机号
	@JavascriptInterface
	public void toNewNum(){
		Intent intent = new Intent(context, NewTelnumActivity.class);
		((Activity)context).startActivity(intent);
	}

//	分享页面
	@JavascriptInterface
	public void toShared(String title,String content,String img,String url){
		Intent intent = new Intent(context, CrumbsUmengSharedActivity.class);
		intent.putExtra("title",title);
		intent.putExtra("content",content);
		intent.putExtra("img",img);
		intent.putExtra("url",url);
		((Activity)context).startActivity(intent);
	}

	//	宠物信息
	@JavascriptInterface
	public void toMypetinfo(String id){
		Intent intent = new Intent(context,AddMypetActivity.class);
		intent.putExtra("id",Integer.valueOf(id));
		((Activity)context).startActivity(intent);
	}


	//	第三方绑定手机号
//	@JavascriptInterface
//	public void toThirdNewNum(){
//		Intent intent = new Intent(context,ThirdNewTelnumActivity.class);
//		((Activity)context).startActivity(intent);
//	}


//	第三方绑定手机号
	@JavascriptInterface
	public void bindTel(){
		Intent intent = new Intent(context, Forhtml_NewTelnumActivity.class);
		((Activity)context).startActivity(intent);
	}



//	跳转个人信息
	@JavascriptInterface
	public void toPersonInfo(){
		Intent intent = new Intent(context, PersoninfoSetActivity.class);
		((Activity)context).startActivity(intent);
	}


//	跳出提示框
	@JavascriptInterface
	public void showToast(String toast){
		Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
	}








}
