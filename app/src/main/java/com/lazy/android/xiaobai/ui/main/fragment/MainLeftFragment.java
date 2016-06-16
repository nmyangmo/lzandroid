package com.lazy.android.xiaobai.ui.main.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.basefunc.LZUtils.UtilsShared;
import com.lazy.android.xiaobai.data.WebviewIntentData;
import com.lazy.android.xiaobai.ui.common.CommonWebViewActivity;
import com.lazy.android.xiaobai.ui.main.activity.MainIndexActivity;
import com.lazy.android.xiaobai.ui.personSet.PersoninfoSetActivity;
import com.lazy.android.xiaobai.ui.personSet.SetIndexActivity;
import com.lazy.android.xiaobai.ui.personSet.ShopCenterActivity;
import com.lazy.android.xiaobai.ui.register.activity.LoginActivity;
import com.lazy.android.xiaobai.ui.register.data.UserinfoSharedUtils;


/**
 * Created by chenglei on 16/3/7.
 */

public class MainLeftFragment extends Fragment implements View.OnClickListener {

	private View view;
	private LinearLayout leftmenu_mypet,leftmenu_mytrain,leftmenu_shopcenter,leftmenu_shopcar,leftmenu_setting,leftmenu_loginout;
	private ImageView personhead_img;
	private TextView personhead_text;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.xb_mian_fragment_left,null);
		initView();
		initdata();
		return view;
	}


	@Override
	public void onResume() {
		super.onResume();
		initdata();
	}

	//	初始化数据
	private void initdata() {



		if(UtilsShared.getBoolean(getActivity(), ConfigStaticType.SettingField.XB_LOGIN_SUCCESS, false)){
			personhead_img.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getActivity().startActivity(new Intent(getActivity(), PersoninfoSetActivity.class));
				}
			});
			String headurl = UtilsShared.getString(getActivity(), ConfigStaticType.SettingField.XB_HEADIMG, "");
			if(!headurl.equals("")){
				personhead_img.setImageBitmap(BitmapFactory.decodeFile(headurl));
			}else{
				personhead_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.xb_headimg_null));
			}

		}else{
			personhead_img.setImageResource(R.drawable.nologin);
			personhead_text.setVisibility(View.GONE);
			personhead_img.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
				}
			});
		};
		if(!UtilsShared.getBoolean(getActivity(), ConfigStaticType.SettingField.XB_LOGIN_SUCCESS,false)){
			leftmenu_loginout.setVisibility(View.GONE);
		}else{
			leftmenu_loginout.setVisibility(View.VISIBLE);
		}
	}





	//	初始化视图
	private void initView() {

		leftmenu_mypet = (LinearLayout) view.findViewById(R.id.leftmenu_mypet);
		leftmenu_mypet.setOnClickListener(this);
		leftmenu_mytrain = (LinearLayout) view.findViewById(R.id.leftmenu_mytrain);
		leftmenu_mytrain.setOnClickListener(this);
		leftmenu_shopcenter = (LinearLayout) view.findViewById(R.id.leftmenu_shopcenter);
		leftmenu_shopcenter.setOnClickListener(this);
		leftmenu_shopcar = (LinearLayout) view.findViewById(R.id.leftmenu_shopcar);
		leftmenu_shopcar.setOnClickListener(this);
		leftmenu_setting = (LinearLayout) view.findViewById(R.id.leftmenu_setting);
		leftmenu_setting.setOnClickListener(this);
		leftmenu_loginout = (LinearLayout) view.findViewById(R.id.leftmenu_loginout);

		leftmenu_loginout.setOnClickListener(this);
		personhead_img = (ImageView) view.findViewById(R.id.personhead_img);

		personhead_img.setOnClickListener(this);
		personhead_text = (TextView) view.findViewById(R.id.personhead_text);
		personhead_text.setOnClickListener(this);



	}




	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.leftmenu_mypet:
				leftmenu_mypet_click();
				break;
			case R.id.leftmenu_mytrain:
				leftmenu_mytrain_click();
				break;
			case R.id.leftmenu_shopcar:
				leftmenu_shopcar_click();
				break;
			case R.id.leftmenu_shopcenter:
				leftmenu_shopcenter_click();
				break;
			case R.id.leftmenu_setting:
				leftmenu_setting_click();
				break;
			case R.id.leftmenu_loginout:
				leftmenu_loginout_click();
				break;
			case R.id.personhead_img:
				personhead_img_click();
				break;
			case R.id.personhead_text:
				personhead_img_click();
				break;

		}
	}

//	头像点击事件
	private void personhead_img_click() {


	}



	//	购物中心
	private void leftmenu_shopcenter_click() {
		startActivity(new Intent(getActivity(), ShopCenterActivity.class));
	}

//	退出登录
	private void leftmenu_loginout_click() {
		new UserinfoSharedUtils().LoginOut(getActivity());
		initView();
		initdata();
		((MainIndexActivity)getActivity()).refreshhead();
	}

	private void leftmenu_setting_click() {
		startActivity(new Intent(getActivity(), SetIndexActivity.class));
	}

	private void leftmenu_shopcar_click() {
		WebviewIntentData webviewIntentData = new WebviewIntentData("购物车","set-shoppingcart.html");
		Intent intent = new Intent(getActivity(), CommonWebViewActivity.class);
		intent.putExtra("intentdata",webviewIntentData);
		startActivity(intent);
	}

	private void leftmenu_mytrain_click() {
		WebviewIntentData webviewIntentData = new WebviewIntentData();
		webviewIntentData.setName("我的课程");
		webviewIntentData.setUrl("train-mytrain.html");
		Intent intent = new Intent(getActivity(), CommonWebViewActivity.class);
		intent.putExtra("intentdata",webviewIntentData);
		startActivity(intent);
	}

	private void leftmenu_mypet_click() {
		WebviewIntentData webviewIntentData = new WebviewIntentData();
		webviewIntentData.setName("我的宠物");
		webviewIntentData.setUrl("set-mypet.html");
		Intent intent = new Intent(getActivity(), CommonWebViewActivity.class);
		intent.putExtra("intentdata",webviewIntentData);
		startActivity(intent);
	}
}
