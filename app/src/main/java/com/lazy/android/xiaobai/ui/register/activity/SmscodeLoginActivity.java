package com.lazy.android.xiaobai.ui.register.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.config.ConfigSystem;
import com.lazy.android.R;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseui.base.LZBaseActivity;
import com.lazy.android.xiaobai.ui.main.activity.MainIndexActivity;
import com.lazy.android.xiaobai.ui.register.protocol.GetsmsProtocol;
import com.lazy.android.xiaobai.ui.register.protocol.SmscodeLoginProtocol;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/3/3.
 */

@ContentView(R.layout.xb_loginregister_smscodelogin_activity)
public class SmscodeLoginActivity extends LZBaseActivity {

	@ViewInject(R.id.smslogin_username) private EditText smslogin_username;
	@ViewInject(R.id.smslogin_smscode) private EditText smslogin_smscode;
	@ViewInject(R.id.smslogin_getcode) private Button smslogin_getcode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
	}


	@Event(R.id.smslogin_getcode)
	private void smslogin_getcode_click(View view){
		new GetsmsProtocol(this,this,smslogin_username.getText().toString(), ConfigStaticType.getSmscodeType.TELLOGIN_CODE);
//		开启倒计时的子线程
		new Thread(){
			@Override
			public void run() {
				for(int i = ConfigSystem.GETSMSCODE_TIME; i >=0 ; i--){
					Message message = new Message();
					message.what = i;
					mHandler.sendMessage(message);
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	@Event(R.id.smslogin_button)
	private void smslogin_button_click(View view){
		new SmscodeLoginProtocol(this,this,smslogin_username.getText().toString(),smslogin_smscode.getText().toString());
	}




	//倒计时60秒
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what <= 0){
				smslogin_getcode.setClickable(true);
				smslogin_getcode.setText("获取验证码");
			}else{
				smslogin_getcode.setText(msg.what + "秒后再发送");
				smslogin_getcode.setClickable(false);
			}
		}
	};


	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("验证登录");
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
//		点击获取验证码的网络请求
		if(protocol.getProtocolType() == ConfigProtocolType.GETSMSCODE){
			GetsmsProtocol getsmsProtocol = (GetsmsProtocol)protocol;
			if(getsmsProtocol.getProtocolStatus() == ConfigStaticType.ProtocolStatus.RESULT_SUCCESS){
				ToastShow("验证码发送成功");
			}else{
				ToastShow(getsmsProtocol.getProtocolErrorMessage());
			}
		}else if(protocol.getProtocolType() == ConfigProtocolType.SMSLOGIN){
			SmscodeLoginProtocol smscodeLoginProtocol = (SmscodeLoginProtocol)protocol;
			if(smscodeLoginProtocol.getProtocolStatus()==ConfigStaticType.ProtocolStatus.RESULT_SUCCESS){
				startActivity(new Intent(this, MainIndexActivity.class));
				this.finish();
			}

		}

	}
}
