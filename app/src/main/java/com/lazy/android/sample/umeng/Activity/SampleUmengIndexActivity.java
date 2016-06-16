package com.lazy.android.sample.umeng.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.lazy.android.R;
import com.lazy.android.baseui.base.LZBaseActivityI;
import com.lazy.android.baseui.crumbs.CrumbsUmengSharedActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.util.Map;

/**
 * Created by chenglei on 16/1/17.
 */

@ContentView(R.layout.sample_umeng_activity)
public class SampleUmengIndexActivity extends LZBaseActivityI {

	private UMShareAPI mShareAPI;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

////		各个平台的配置
		//微信 appid appsecret
		PlatformConfig.setWeixin("wx9e5ba61fef2f3da5", "a51078d16fc653b678d7208fc70efd59");
		//新浪微博 appkey appsecret
		PlatformConfig.setSinaWeibo("4135614408", "084d58a296a41576d96046c30ddead60");
//		// QQ和Qzone appid appkey
		PlatformConfig.setQQZone("1104703359", "Ydmlp5q6mAwMFnlm");
//		//支付宝 appid
//		PlatformConfig.setAlipay("2015111700822536");
//		//易信 appkey
//		PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
//		//Twitter appid appkey
//		PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//		//Pinterest appid
//		PlatformConfig.setPinterest("1439206");
//		//来往 appid appkey
//		PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");


//		授权  首先获取UMShareAPI
		mShareAPI = UMShareAPI.get(this);

	}

//	微信登录
	@Event(R.id.umeng_loginwx)
	private void umeng_loginwx_Event(View view){

		if(mShareAPI.isInstall(this, SHARE_MEDIA.WEIXIN)){
			mShareAPI.doOauthVerify(this, SHARE_MEDIA.WEIXIN, umAuthListener);
		}else{
			ToastShow("您还未安装微信");
		}

	}

//	QQ登录
	@Event(R.id.umeng_loginqq)
	private void umeng_loginqq_Event(View view){


		if(mShareAPI.isInstall(this, SHARE_MEDIA.QQ)){
			mShareAPI.doOauthVerify(this, SHARE_MEDIA.QQ, umAuthListener);
		}else{
			ToastShow("您还未安装QQ");
		}

	}

//	微博登录
	@Event(R.id.umeng_loginweibo)
	private void umeng_loginweibo_Event(View view){
		if(mShareAPI.isInstall(this, SHARE_MEDIA.SINA)){
			mShareAPI.doOauthVerify(this, SHARE_MEDIA.SINA, umAuthListener);
		}else{
			ToastShow("您还未安装新浪");
		}
	}


//	返回回调
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mShareAPI.onActivityResult(requestCode, resultCode, data);
	}

	//	第三方登录回调
	private UMAuthListener umAuthListener = new UMAuthListener() {
		@Override
		public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

			String result = platform.name();
			for (String key : data.keySet()) {
				result += ("&" + key + "=" + data.get(key));
			}

			ToastShow(result);
		}

		@Override
		public void onError(SHARE_MEDIA platform, int action, Throwable t) {
			ToastShow("failaaaa");
			Log.i("chenglei","fail");
//			Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onCancel(SHARE_MEDIA platform, int action) {
			ToastShow("cancelaaaa");
			Log.i("chenglei","cancel");
//			Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
		}
	};



//	点击打开分享面板
	@Event(R.id.umeng_share)
	private void umeng_share_Event(View view){
		startActivity(new Intent(this, CrumbsUmengSharedActivity.class));
	}





}
