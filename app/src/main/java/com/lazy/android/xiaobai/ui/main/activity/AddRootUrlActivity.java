package com.lazy.android.xiaobai.ui.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.basefunc.LZUtils.UtilsShared;
import com.lazy.android.baseui.base.LZBaseActivityI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by chenglei on 16/4/11.
 */

@ContentView(R.layout.xb_addrooturl_activity)
public class AddRootUrlActivity extends LZBaseActivityI {

	@ViewInject(R.id.edit) EditText edit;
	@ViewInject(R.id.button) Button button;
	@ViewInject(R.id.editimg) EditText editimg;
	@ViewInject(R.id.buttonimg) Button buttonimg;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
		edit.setText("");
		edit.setText(UtilsShared.getString(this, ConfigStaticType.SettingField.XB_ROOT, "http://i.ixbai.com/"));
		editimg.setText("");
		editimg.setText(UtilsShared.getString(this, ConfigStaticType.SettingField.XB_IMGROOT,"http://115.159.183.250/"));
	}

	@Event(R.id.button)
	private void button_Event(View view){
		if(edit.getText().toString().equals("")){
			UtilsShared.setString(this, ConfigStaticType.SettingField.XB_ROOT,"http://i.ixbai.com/");
		}else{
			UtilsShared.setString(this, ConfigStaticType.SettingField.XB_ROOT, edit.getText().toString());
			edit.setText(UtilsShared.getString(this, ConfigStaticType.SettingField.XB_ROOT, "http://i.ixbai.com/"));
		}
		this.finish();
	}


	@Event(R.id.buttonimg)
	private void buttonimg_Event(View view){
		if(editimg.getText().toString().equals("")){
			UtilsShared.setString(this, ConfigStaticType.SettingField.XB_IMGROOT,"http://115.159.183.250/");
		}else if(editimg.getText().toString().equals("1")){
			UtilsShared.setString(this, ConfigStaticType.SettingField.XB_IMGROOT,"http://115.159.183.250/");
		}else{
			UtilsShared.setString(this, ConfigStaticType.SettingField.XB_IMGROOT, editimg.getText().toString());
		}
		this.finish();
	}


	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("注册根地址");
	}


}
