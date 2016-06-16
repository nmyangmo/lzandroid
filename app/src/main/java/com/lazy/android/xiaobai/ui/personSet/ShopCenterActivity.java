package com.lazy.android.xiaobai.ui.personSet;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.basefunc.LZUtils.UtilsShared;
import com.lazy.android.baseui.base.LZBaseActivityI;
import com.lazy.android.xiaobai.data.WebviewIntentData;
import com.lazy.android.xiaobai.ui.common.CommonWebViewActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenglei on 16/3/12.
 */


@ContentView(R.layout.xb_shopcenter_activity)
public class ShopCenterActivity extends LZBaseActivityI {

	@ViewInject(R.id.common_head_headimg) CircleImageView common_head_headimg;
	@ViewInject(R.id.common_head_headtext) TextView common_head_headtext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
		if (!UtilsShared.getString(this, ConfigStaticType.SettingField.XB_HEADIMG, "").equals("")) {
			String headurl = UtilsShared.getString(this, ConfigStaticType.SettingField.XB_HEADIMG, "");
			common_head_headimg.setImageBitmap(BitmapFactory.decodeFile(headurl));
		} else {
			common_head_headimg.setImageResource(R.drawable.appico);
		}
		if (!UtilsShared.getString(this, ConfigStaticType.SettingField.XB_NICKNAME, "").equals("")) {
			common_head_headtext.setText(UtilsShared.getString(this, ConfigStaticType.SettingField.XB_NICKNAME, ""));
		}
	}



	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("购物中心");
	}


	@Event(value = R.id.shopcenter_all,type = View.OnClickListener.class)
	private void shopcenter_all_click(View view){
		WebviewIntentData webviewIntentData = new WebviewIntentData("我的订单","supplies-orders.html");
		Intent intent = new Intent(this, CommonWebViewActivity.class);
		intent.putExtra("intentdata", webviewIntentData);
		startActivity(intent);
	}

	@Event(value = R.id.shopcenter_waitpay,type = View.OnClickListener.class)
	private void shopcenter_waitpay_click(View view){
		WebviewIntentData webviewIntentData = new WebviewIntentData("待付款","supplies-orders.html?status=0");
		Intent intent = new Intent(this, CommonWebViewActivity.class);
		intent.putExtra("intentdata",webviewIntentData);
		startActivity(intent);
	}

	@Event(value = R.id.shopcenter_waitsend,type = View.OnClickListener.class)
	private void shopcenter_waitsend_click(View view){
		WebviewIntentData webviewIntentData = new WebviewIntentData("待发货","supplies-orders.html?status=3");
		Intent intent = new Intent(this, CommonWebViewActivity.class);
		intent.putExtra("intentdata",webviewIntentData);
		startActivity(intent);
	}

	@Event(value = R.id.shopcenter_waitreceive,type = View.OnClickListener.class)
	private void shopcenter_waitreceive_click(View view){
		WebviewIntentData webviewIntentData = new WebviewIntentData("待收货","supplies-orders.html?status=5");
		Intent intent = new Intent(this, CommonWebViewActivity.class);
		intent.putExtra("intentdata",webviewIntentData);
		startActivity(intent);
	}

	@Event(value = R.id.shopcenter_waitcomment,type = View.OnClickListener.class)
	private void shopcenter_waitcomment_click(View view){
		WebviewIntentData webviewIntentData = new WebviewIntentData("待评价","supplies-orders.html?status=7");
		Intent intent = new Intent(this, CommonWebViewActivity.class);
		intent.putExtra("intentdata",webviewIntentData);
		startActivity(intent);
	}

	@Event(value = R.id.shopcenter_shopcar,type = View.OnClickListener.class)
	private void shopcenter_shopcar_click(View view){
//		Intent intent = new Intent(this, ShopCarActivity.class);
//		startActivity(intent);
		WebviewIntentData webviewIntentData = new WebviewIntentData("购物车","set-shoppingcart.html");
		Intent intent = new Intent(this, CommonWebViewActivity.class);
		intent.putExtra("intentdata",webviewIntentData);
		startActivity(intent);
	}

	@Event(value = R.id.shopcenter_address,type = View.OnClickListener.class)
	private void shopcenter_address_click(View view){
		WebviewIntentData webviewIntentData = new WebviewIntentData("收货地址","set-addresslist.html");
		Intent intent = new Intent(this, CommonWebViewActivity.class);
		intent.putExtra("intentdata",webviewIntentData);
		startActivity(intent);
	}




}
