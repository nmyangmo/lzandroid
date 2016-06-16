package com.lazy.android.xiaobai.ui.register.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseui.base.LZBaseActivityI;
import com.lazy.android.xiaobai.ui.register.protocol.GetsmsProtocol;
import com.lazy.android.xiaobai.ui.register.protocol.RegisterProtocol;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/3/3.
 */
@ContentView(R.layout.xb_loginregister_register_activity)
public class RegisterActivity extends LZBaseActivityI {

	@ViewInject(R.id.register_telnum) private EditText register_telnum;
	@ViewInject(R.id.register_smscode) private EditText register_smscode;
	@ViewInject(R.id.register_pwd) private EditText register_pwd;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
	}



//	获得手机验证码
	@Event(value = R.id.register_getsmscode,type = View.OnClickListener.class)
	private void register_getsmscode_click(View view){
		new GetsmsProtocol(this,this,register_telnum.getText().toString(), ConfigStaticType.getSmscodeType.REGISTER_CODE);
	}

//	注册按钮登录
	@Event(value = R.id.register_sure)
	private void register_sure_click(View view){
		new RegisterProtocol(this,this,register_telnum.getText().toString(),register_smscode.getText().toString(),register_pwd.getText().toString());
	}


	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("注册");
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
//		点击注册的网络请求
		}else if(protocol.getProtocolType() == ConfigProtocolType.REGISTER){
			RegisterProtocol registerProtocol = (RegisterProtocol)protocol;
			if(registerProtocol.getProtocolStatus() == ConfigStaticType.ProtocolStatus.RESULT_SUCCESS){
				ToastShow("注册成功");
			}else{
				ToastShow(registerProtocol.getProtocolErrorMessage());
			}
		}
	}




}
