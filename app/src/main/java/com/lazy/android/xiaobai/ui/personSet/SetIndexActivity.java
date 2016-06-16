package com.lazy.android.xiaobai.ui.personSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.basefunc.LZUtils.UtilsClear;
import com.lazy.android.basefunc.LZUtils.UtilsShared;
import com.lazy.android.baseui.base.LZBaseActivityI;
import com.lazy.android.xiaobai.ui.register.activity.EditPasswordActivity;
import com.lazy.android.xiaobai.ui.register.activity.NewTelnumActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by chenglei on 16/3/14.
 */
@ContentView(R.layout.xb_setindex_activity)
public class SetIndexActivity extends LZBaseActivityI {

	@ViewInject(R.id.setindex_telnum_edit) private LinearLayout setindex_telnum_edit;
	@ViewInject(R.id.setindex_pwd_edit) private LinearLayout setindex_pwd_edit;
	@ViewInject(R.id.setindex_nologin_hidden) private LinearLayout setindex_nologin_hidden;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
		if(UtilsShared.getBoolean(this, ConfigStaticType.SettingField.XB_LOGIN_SUCCESS,false)){
			setindex_nologin_hidden.setVisibility(View.VISIBLE);
		}else{
			setindex_nologin_hidden.setVisibility(View.GONE);
		}
	}



	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("设置");
	}


//	绑定新手机号点击
	@Event(value = R.id.setindex_telnum_edit,type = View.OnClickListener.class)
	private void setindex_telnum_edit_click(View view){
		startActivity(new Intent(this, NewTelnumActivity.class));
	}

//	修改密码点击
	@Event(value = R.id.setindex_pwd_edit,type = View.OnClickListener.class)
	private void setindex_pwd_edit_click(View view){
		startActivity(new Intent(this, EditPasswordActivity.class));
	}

//	用户协议点击
	@Event(value = R.id.setindex_protocol,type = View.OnClickListener.class)
	private void setindex_protocol_click(View view){

	}

//	用户反馈
	@Event(value = R.id.setindex_opinion,type = View.OnClickListener.class)
	private void setindex_opinion_click(View view){

	}

//	清除缓存
	@Event(value = R.id.setindex_clean,type = View.OnClickListener.class)
	private void setindex_clean_click(View view){
		UtilsClear.cleanExternalCache(this);
		ToastShow("缓存已清理");
	}

//	检测新版本
	@Event(value = R.id.setindex_newversion,type = View.OnClickListener.class)
	private void setindex_newversion_click(View view){

	}

}
