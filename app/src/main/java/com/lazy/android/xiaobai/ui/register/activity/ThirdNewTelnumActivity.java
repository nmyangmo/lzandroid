package com.lazy.android.xiaobai.ui.register.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.baseui.base.LZBaseActivity;
import com.lazy.android.xiaobai.ui.register.protocol.GetsmsProtocol;
import com.lazy.android.xiaobai.ui.register.protocol.NewTelnumProtocol;
import com.lazy.android.xiaobai.ui.register.protocol.ThirdNewTelnumProtocol;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/3/3.
 */

@ContentView(R.layout.xb_loginregister_thirdnewtelnum_activity)
public class ThirdNewTelnumActivity extends LZBaseActivity {

	@ViewInject(R.id.thirdnewnum_tel) EditText thirdnewnum_tel;
	@ViewInject(R.id.thirdnewnum_code) EditText thirdnewnum_code;
	@ViewInject(R.id.thirdnewnum_getcode) Button thirdnewnum_getcode;
	@ViewInject(R.id.thirdnewnum_psw) EditText thirdnewnum_psw;
	@ViewInject(R.id.thirdnewnum_button) Button thirdnewnum_button;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
	}



//	获取验证码
	@Event(R.id.newnum_getcode)
	private void newnum_getcode_Event(View view){
		String tel = thirdnewnum_tel.getText().toString();
		new GetsmsProtocol(this,this,tel,0);
	}


//	提交新手机号
	@Event(R.id.newnum_button)
	private void newnum_button_Event(View view){
		String tel = thirdnewnum_tel.getText().toString();
		String smscode = thirdnewnum_code.getText().toString();
		String password = thirdnewnum_psw.getText().toString();
		new ThirdNewTelnumProtocol(this,this,tel,smscode,password);
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
		if (protocol.getProtocolType() == ConfigProtocolType.NEWTELNUM) {
			NewTelnumProtocol loginProtocol = (NewTelnumProtocol) protocol;
			if (loginProtocol.getProtocolStatus() == ConfigStaticType.ProtocolStatus.RESULT_SUCCESS) {
				ToastShow("设置成功");
				ThirdNewTelnumActivity.this.finish();
			} else {
				ToastShow(loginProtocol.getProtocolErrorMessage());
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
		mCommonHead.setMiddleTitle("绑定新手机号");
	}

}
