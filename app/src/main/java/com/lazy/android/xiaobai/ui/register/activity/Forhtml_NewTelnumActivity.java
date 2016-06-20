package com.lazy.android.xiaobai.ui.register.activity;

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
import com.lazy.android.xiaobai.ui.register.protocol.GetsmsProtocol;
import com.lazy.android.xiaobai.ui.register.protocol.NewTelnumProtocol;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/3/3.
 */

@ContentView(R.layout.xb_loginregister_forhtml_newtelnum_activity)
public class Forhtml_NewTelnumActivity extends LZBaseActivity {

	@ViewInject(R.id.newnum_tel) EditText newnum_tel;
	@ViewInject(R.id.newnum_code) EditText newnum_code;
	@ViewInject(R.id.newnum_getcode) Button newnum_getcode;
	@ViewInject(R.id.newnum_button) Button newnum_button;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	//倒计时60秒
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what <= 0){
				newnum_getcode.setClickable(true);
				newnum_getcode.setText("获取验证码");
			}else{
				newnum_getcode.setText(msg.what + "秒后再发送");
				newnum_getcode.setClickable(false);
			}
		}
	};


//	获取验证码
	@Event(R.id.newnum_getcode)
	private void newnum_getcode_Event(View view){
		String tel = newnum_tel.getText().toString();
		new GetsmsProtocol(this,this,tel,0);
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


//	提交新手机号
	@Event(R.id.newnum_button)
	private void newnum_button_Event(View view){
		String tel = newnum_tel.getText().toString();
		String smscode = newnum_code.getText().toString();
		new NewTelnumProtocol(this,this,tel,smscode);
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
		if(protocol.getProtocolStatus() == ConfigStaticType.ProtocolStatus.RESULT_SUCCESS){
			if (protocol.getProtocolType() == ConfigProtocolType.NEWTELNUM) {
				NewTelnumProtocol loginProtocol = (NewTelnumProtocol) protocol;
				ToastShow("设置成功");
				Forhtml_NewTelnumActivity.this.finish();
			}else if(protocol.getProtocolType() == ConfigProtocolType.GETSMSCODE){
				ToastShow("短信发送成功");
			}
		}else{
			ToastShow(protocol.getProtocolErrorMessage());
		}


	}


}
