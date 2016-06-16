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
import com.lazy.android.baseui.base.LZBaseActivityI;
import com.lazy.android.xiaobai.ui.register.protocol.ForgetPasswordProtocol;
import com.lazy.android.xiaobai.ui.register.protocol.GetsmsProtocol;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/3/3.
 */


@ContentView(R.layout.xb_loginregister_editpassword_activity)
public class EditPasswordActivity extends LZBaseActivityI {

	@ViewInject(R.id.editpsw_telnum) EditText editpsw_telnum;
	@ViewInject(R.id.editpsw_smscode) EditText editpsw_smscode;
	@ViewInject(R.id.editpsw_getsmscode) Button editpsw_getsmscode;
	@ViewInject(R.id.editpsw_getpsw) EditText editpsw_getpsw;
	@ViewInject(R.id.editpsw_button) Button editpsw_button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
	}

	//倒计时60秒
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what <= 0){
				editpsw_getsmscode.setClickable(true);
				editpsw_getsmscode.setText("获取验证码");
			}else{
				editpsw_getsmscode.setText(msg.what + "秒后再发送");
				editpsw_getsmscode.setClickable(false);
			}
		}
	};



//	点击获得验证码
	@Event(R.id.editpsw_getsmscode)
	private void editpsw_getsmscode_Event(View view){
		String tel = editpsw_telnum.getText().toString();
		new GetsmsProtocol(this,this,tel,1);
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

//	点击确定按钮
	@Event(R.id.editpsw_button)
	private void editpsw_button_Event(View view){
		String tel = editpsw_telnum.getText().toString();
		String smscode = editpsw_smscode.getText().toString();
		String password = editpsw_getpsw.getText().toString();
		new ForgetPasswordProtocol(this,this,tel,smscode,password);
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
		if (protocol.getProtocolType() == ConfigProtocolType.GETSMSCODE) {
			GetsmsProtocol getsmsProtocol = (GetsmsProtocol) protocol;
			if (getsmsProtocol.getProtocolStatus() == ConfigStaticType.ProtocolStatus.RESULT_SUCCESS) {
				ToastShow("验证码已发送");
			} else {
				ToastShow(getsmsProtocol.getProtocolErrorMessage());
			}
		}else if(protocol.getProtocolType() == ConfigProtocolType.FORGOTPASSWORD){
			ForgetPasswordProtocol forgetPasswordProtocol = (ForgetPasswordProtocol) protocol;
			if (forgetPasswordProtocol.getProtocolStatus() == ConfigStaticType.ProtocolStatus.RESULT_SUCCESS) {
				ToastShow("密码修改成功");
			} else {
				ToastShow(forgetPasswordProtocol.getProtocolErrorMessage());
			}
		}

	}

	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("修改登录密码");
	}

}
