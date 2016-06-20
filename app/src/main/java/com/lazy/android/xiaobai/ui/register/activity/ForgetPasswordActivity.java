package com.lazy.android.xiaobai.ui.register.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseui.base.LZBaseActivity;
import com.lazy.android.xiaobai.ui.register.protocol.ForgetPasswordProtocol;
import com.lazy.android.xiaobai.ui.register.protocol.GetsmsProtocol;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/3/3.
 */

@ContentView(R.layout.xb_loginregister_forget_activity)
public class ForgetPasswordActivity extends LZBaseActivity {

	@ViewInject(R.id.forget_username) private EditText forget_username;
	@ViewInject(R.id.forget_smscode) private EditText forget_smscode;
	@ViewInject(R.id.forget_newpwd) private EditText forget_newpwd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
	}


//	获得验证码的点击
	@Event(R.id.forget_getsmscode)
	private void forget_getsmscode_click(View view){
		new GetsmsProtocol(this,this,forget_username.getText().toString(), ConfigStaticType.getSmscodeType.GETPWD_CODE);
	}

//	忘记密码的确定
	@Event(R.id.forget_ok)
	private void forget_ok_click(View view){
		new ForgetPasswordProtocol(this,this,forget_username.getText().toString(),forget_smscode.getText().toString(),forget_newpwd.getText().toString());
	}

	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("忘记密码");
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
		//点击获取验证码的网络请求
		if(protocol.getProtocolType() == ConfigProtocolType.GETSMSCODE){
			GetsmsProtocol getsmsProtocol = (GetsmsProtocol)protocol;
			if(getsmsProtocol.getProtocolStatus() == ConfigStaticType.ProtocolStatus.RESULT_SUCCESS){
				ToastShow("验证码发送成功");
			}else{
				ToastShow(getsmsProtocol.getProtocolErrorMessage());
			}
//		修改密码的网络请求
		}else if(protocol.getProtocolType() == ConfigProtocolType.REGISTER){
			ForgetPasswordProtocol forgetPasswordProtocol = (ForgetPasswordProtocol) protocol;
			if(forgetPasswordProtocol.getProtocolStatus() == ConfigStaticType.ProtocolStatus.RESULT_SUCCESS){
				ToastShow("找回密码成功");
			}else{
				ToastShow(forgetPasswordProtocol.getProtocolErrorMessage());
			}
		}
	}
}
