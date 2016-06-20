package com.lazy.android.xiaobai.ui.personSet;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.basefunc.LZUtils.UtilsShared;
import com.lazy.android.baseui.base.LZBaseActivity;
import com.lazy.android.baseui.crumbs.CrumbsCityPick.CrumbsCityPickActivity;
import com.lazy.android.baseui.crumbs.CrumbsPickerDiyActivity;
import com.lazy.android.baseui.crumbs.CrumbsTakephotoActivity;
import com.lazy.android.xiaobai.ui.personSet.protocol.HeadimgSetProtocol;
import com.lazy.android.xiaobai.ui.personSet.protocol.PersoninfoSetProtocol;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by chenglei on 16/3/12.
 */
@ContentView(R.layout.xb_personset_info_activity)
public class PersoninfoSetActivity extends LZBaseActivity implements View.OnClickListener {

	@ViewInject(R.id.persentinfo_back)
	private LinearLayout persentinfo_back;
	@ViewInject(R.id.persentinfo_headimg)
	private CircleImageView persentinfo_headimg;
	@ViewInject(R.id.persentinfo_nickname)
	private TextView persentinfo_nickname;
	@ViewInject(R.id.persentinfo_editbutton)
	private TextView persentinfo_editbutton;
	@ViewInject(R.id.persentinfo_telnum)
	private EditText persentinfo_telnum;
	@ViewInject(R.id.persentinfo_nicknamemain)
	private EditText persentinfo_nicknamemain;
	@ViewInject(R.id.persentinfo_sex)
	private EditText persentinfo_sex;
	@ViewInject(R.id.persentinfo_city)
	private EditText persentinfo_city;
	@ViewInject(R.id.persentinfo_savebutton)
	private Button persentinfo_savebutton;

	private String headimg;
	private ArrayList<String> sexarr;

	private boolean isEdit;


	private ImageOptions imageOptions;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isLogin();
		isEdit = false;
		initView();
		initdata();
	}


	//	初始化视图
	@Override
	public void initView() {
		super.initView();
		if (isEdit) {
			persentinfo_headimg.setClickable(true);
			persentinfo_editbutton.setVisibility(View.INVISIBLE);
			persentinfo_telnum.setFocusable(true);
			persentinfo_nicknamemain.setEnabled(true);
			persentinfo_sex.setClickable(true);
			persentinfo_city.setClickable(true);
			persentinfo_savebutton.setVisibility(View.VISIBLE);
			persentinfo_headimg.setOnClickListener(this);
			persentinfo_sex.setOnClickListener(this);
			persentinfo_city.setOnClickListener(this);
			persentinfo_nicknamemain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
						persentinfo_nicknamemain.setText("");
					}
				}
			});
		} else {
			persentinfo_headimg.setClickable(false);
			persentinfo_editbutton.setVisibility(View.VISIBLE);
			persentinfo_telnum.setFocusable(false);
			persentinfo_nicknamemain.setEnabled(false);
			persentinfo_sex.setClickable(false);
			persentinfo_city.setClickable(false);
			persentinfo_savebutton.setVisibility(View.GONE);
		}
	}

	//	初始化数据源
	private void initdata() {
		sexarr = new ArrayList<String>();
		sexarr.add(ConfigStaticType.SEX.MEN.name);
		sexarr.add(ConfigStaticType.SEX.WOMEN.name);
		sexarr.add(ConfigStaticType.SEX.NULL.name);


		if (UtilsShared.getBoolean(this, ConfigStaticType.SettingField.XB_LOGIN_SUCCESS, false)) {

			if (!UtilsShared.getString(this, ConfigStaticType.SettingField.XB_HEADIMG, "").equals("")) {
				String headurl = UtilsShared.getString(this, ConfigStaticType.SettingField.XB_HEADIMG, "");
				persentinfo_headimg.setImageBitmap(BitmapFactory.decodeFile(headurl));
			} else {
				persentinfo_headimg.setImageResource(R.drawable.logo);
			}

			if (!UtilsShared.getString(this, ConfigStaticType.SettingField.XB_USERNAME, "").equals("")) {
				persentinfo_telnum.setText(UtilsShared.getString(this, ConfigStaticType.SettingField.XB_USERNAME, ""));
			}
			if (!UtilsShared.getString(this, ConfigStaticType.SettingField.XB_NICKNAME, "").equals("")) {
				persentinfo_nicknamemain.setText(UtilsShared.getString(this, ConfigStaticType.SettingField.XB_NICKNAME, ""));
				persentinfo_nickname.setText(UtilsShared.getString(this, ConfigStaticType.SettingField.XB_NICKNAME, ""));
			}

			if (UtilsShared.getInt(this, ConfigStaticType.SettingField.XB_SEX, -1) == 0) {
				persentinfo_sex.setText("女");
			} else if (UtilsShared.getInt(this, ConfigStaticType.SettingField.XB_SEX, -1) == 1) {
				persentinfo_sex.setText("男");
			} else {
				persentinfo_sex.setText("未选");
			}

			if (!UtilsShared.getString(this, ConfigStaticType.SettingField.XB_CITYNAME, "").equals("")) {
				persentinfo_city.setText(UtilsShared.getString(this, ConfigStaticType.SettingField.XB_CITYNAME, "未选"));
			}

		}

	}


//	@Override
//	public void onResume() {
//		super.onResume();
//		initView();
//		if(isEdit){
//			ToastShow("true");
//		}else{
//			ToastShow("false");
//		}
//	}
//
//
//	@Override
//	public void onStop() {
//		super.onStop();
//		ToastShow("stop");
//	}

	//	返回结果处理
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == CrumbsTakephotoActivity.RESULT_ONE_CODE) {
			headimg = data.getStringExtra("headimg");
			persentinfo_headimg.setImageBitmap(BitmapFactory.decodeFile(data.getStringExtra("headimg")));
			new HeadimgSetProtocol(this, this, headimg, "0", "0");
		} else if (resultCode == CrumbsPickerDiyActivity.RESULT_CODE) {
			int position = data.getIntExtra(CrumbsPickerDiyActivity.INTENT_KEY, 0);
			persentinfo_sex.setText(sexarr.get(position));
		} else if (resultCode == CrumbsCityPickActivity.RESULT_CODE) {
			String result = data.getStringExtra(CrumbsCityPickActivity.RESULT_KYE);
			persentinfo_city.setText(result);

		}
	}

	@Event(R.id.persentinfo_back)
	private void persentinfo_back_event(View view) {
		this.finish();
	}


	//	保存个人信息
	@Event(R.id.persentinfo_savebutton)
	private void persentinfo_savebutton_event(View view) {
		String nickname = persentinfo_nicknamemain.getText().toString();
		String sex = persentinfo_sex.getText().toString();
		String city = persentinfo_city.getText().toString();
		new PersoninfoSetProtocol(this, this, headimg, nickname, sex, city);
	}


	//	点击编辑事件
	@Event(R.id.persentinfo_editbutton)
	private void persentinfo_editbutton_Event(View view) {
		isEdit = true;
		initView();
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
		if (protocol.getProtocolStatus() == ConfigStaticType.ProtocolStatus.RESULT_SUCCESS) {
			if (protocol.getProtocolType() == ConfigProtocolType.PERSONINFO_SAVE) {
				ToastShow("个人信息修改成功");
				this.finish();
			} else {
				ToastShow(protocol.getProtocolErrorMessage());
			}

		}
	}


	//	点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.persentinfo_headimg:
				persentinfo_headimg_click();
				break;
			case R.id.persentinfo_sex:
				persentinfo_sex_click();
				break;
			case R.id.persentinfo_city:
				persentinfo_city_click();
				break;
		}
	}

	//	城市选择
	private void persentinfo_city_click() {
		startActivityForResult(new Intent(this, CrumbsCityPickActivity.class), CrumbsCityPickActivity.RESULT_CODE);
	}

	//	性别选择
	private void persentinfo_sex_click() {
		Intent intent = new Intent(PersoninfoSetActivity.this, CrumbsPickerDiyActivity.class);
		intent.putExtra("data", sexarr);
		intent.putExtra("title", "选择性别");
		startActivityForResult(intent, CrumbsPickerDiyActivity.RESULT_CODE);
	}

	//	头像点击事件
	private void persentinfo_headimg_click() {
		startActivityForResult(new Intent(PersoninfoSetActivity.this, CrumbsTakephotoActivity.class), CrumbsTakephotoActivity.RESULT_ONE_CODE);
	}


}
