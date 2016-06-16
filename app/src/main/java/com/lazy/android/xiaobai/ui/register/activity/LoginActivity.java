package com.lazy.android.xiaobai.ui.register.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseui.base.LZBaseActivityI;
import com.lazy.android.xiaobai.ui.register.data.UserInfoThird;
import com.lazy.android.xiaobai.ui.register.protocol.LoginProtocol;
import com.lazy.android.xiaobai.ui.register.protocol.ThirdLoginProtocol;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.Map;

/**
 * Created by Administrator on 2016/3/1.
 */

@ContentView(R.layout.xb_loginregister_login_activity)
public class LoginActivity extends LZBaseActivityI {

	@ViewInject(R.id.login_username)
	private EditText login_username;
	@ViewInject(R.id.login_password)
	private EditText login_password;
	private UMShareAPI mShareAPI;
	private Boolean isget = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
//		初始化umeng配置
//		ConfigUmeng.initUmeng();


		//微信 appid appsecret
		PlatformConfig.setWeixin("wx9e5ba61fef2f3da5", "a51078d16fc653b678d7208fc70efd59");
		//新浪微博 appkey appsecret
		PlatformConfig.setSinaWeibo("4135614408", "084d58a296a41576d96046c30ddead60");
//		// QQ和Qzone appid appkey
		PlatformConfig.setQQZone("1104703359", "Ydmlp5q6mAwMFnlm");
//		//支付宝 appid


		//授权 首先获取UMShareAPI
		mShareAPI = UMShareAPI.get(this);


	}

	//	点击登录按钮
	@Event(R.id.login_loginbutton)
	private void login_loginbutton_click(View view) {
		if(login_username.getText().toString().equals("1")){
			login_username.setText("13405332040");
			login_password.setText("asdasdasd");
		}
		new LoginProtocol(this, this, login_username.getText().toString(), login_password.getText().toString());
	}

	//	点击手机验证码登录
	@Event(R.id.login_qlogin)
	private void login_qlogin_click(View view) {
		startActivity(new Intent(this, SmscodeLoginActivity.class));
		this.finish();
	}


	//	点击忘记密码跳转
	@Event(R.id.login_forget)
	private void login_forget_click(View view) {
		startActivity(new Intent(this, ForgetPasswordActivity.class));
		this.finish();
	}


	//	QQ登录
	@Event(R.id.login_qqlogin)
	private void login_qqlogin_Event(View view) {
		isget = false;
		if (mShareAPI.isInstall(this, SHARE_MEDIA.QQ)) {
			mShareAPI.doOauthVerify(this, SHARE_MEDIA.QQ, umAuthListener);
		} else {
			ToastShow("您还未安装QQ");
		}
	}

	//	微信登录
	@Event(R.id.login_wxlogin)
	private void login_wxlogin_Event(View view) {
		isget = false;
		if (mShareAPI.isInstall(this, SHARE_MEDIA.WEIXIN)) {
			mShareAPI.doOauthVerify(this, SHARE_MEDIA.WEIXIN, umAuthListener);
		} else {
			ToastShow("您还未安装微信");
		}

	}

	//	微博登录
	@Event(R.id.login_wblogin)
	private void login_wblogin_Event(View view) {
		isget = false;
		if (mShareAPI.isInstall(this, SHARE_MEDIA.SINA)) {
			mShareAPI.doOauthVerify(this, SHARE_MEDIA.SINA, umAuthListener);
		} else {
			ToastShow("您还未安装新浪");
		}
	}


	//	回调返回处理
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mShareAPI.onActivityResult(requestCode, resultCode, data);

	}

	//	第三方登录回调
	private UMAuthListener umAuthListener = new UMAuthListener() {
		@Override
		public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
			if (!isget) {
				mShareAPI.getPlatformInfo(LoginActivity.this, platform, umAuthListener);
				isget = true;
			}else{
				UserInfoThird third = new UserInfoThird();
				if (platform == SHARE_MEDIA.WEIXIN) {
//					try {
//						third.setNickname(URLEncoder.encode(data.get("nickname"),"UTF-8"));
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
					third.setNickname(data.get("nickname"));
					third.setFaceimg(data.get("headimgurl"));
					third.setThirdtype("WX");
					third.setToken(data.get("unionid"));
				} else if (platform == SHARE_MEDIA.QQ) {
//					try {
//						third.setNickname(URLEncoder.encode(data.get("screen_name"),"UTF-8"));
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
					third.setNickname(data.get("screen_name"));
					third.setFaceimg(data.get("profile_image_url"));
					third.setThirdtype("QQ");
					third.setToken(data.get("openid"));
				} else if (platform == SHARE_MEDIA.SINA) {
					try {
						JSONObject result = new JSONObject(data.get("result")+"");
//						try {
//							third.setNickname(URLEncoder.encode(result.get("screen_name").toString(),"UTF-8"));
//						} catch (UnsupportedEncodingException e) {
//							e.printStackTrace();
//						}
						third.setNickname(result.get("screen_name").toString());
						third.setFaceimg(result.get("profile_image_url").toString());
						third.setThirdtype("WB");
						third.setToken(result.get("id").toString());
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				ToastShow(third.toString());
				new ThirdLoginProtocol(LoginActivity.this,LoginActivity.this,third);
			}
		}

		@Override
		public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//			Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onCancel(SHARE_MEDIA platform, int action) {
//			Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
		}
	};


	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("登录");
		mCommonHead.setRightLayoutText("注册");
		mCommonHead.setRightLayoutTextClick(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
				LoginActivity.this.finish();
			}
		});
	}


	@Override
	public void onHttpStart(LZHttpProtocolHandlerBase protocol) {
		super.onHttpStart(protocol);
	}

	@Override
	public void onHttpProgress(long total, long current, boolean isUploading) {
		super.onHttpProgress(total, current, isUploading);
	}

	@Override
	public void onHttpFailure(Exception except, String msg) {
		super.onHttpFailure(except, msg);
	}

	@Override
	public void onHttpSuccess(LZHttpProtocolHandlerBase protocol) {
		super.onHttpSuccess(protocol);
		if (protocol.getProtocolType() == ConfigProtocolType.LOGIN) {
			LoginProtocol loginProtocol = (LoginProtocol) protocol;
			if (loginProtocol.getProtocolStatus() == ConfigStaticType.ProtocolStatus.RESULT_SUCCESS) {
				ToastShow("登录成功");
				LoginActivity.this.finish();
			} else {
				ToastShow(loginProtocol.getProtocolErrorMessage());
			}
		}else if(protocol.getProtocolType() == ConfigProtocolType.THIRDLOGIN){
			ThirdLoginProtocol thirdLoginProtocol = (ThirdLoginProtocol) protocol;
			LoginActivity.this.finish();
		}


	}
}
