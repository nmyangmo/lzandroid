package com.lazy.android.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lazy.android.R;
import com.lazy.android.basefunc.LZAlipay.AlipayActivity;
import com.lazy.android.basefunc.LZAlipay.AlipayDemoActivity;
import com.lazy.android.basefunc.LZRecord.RecordMainActivity;
import com.lazy.android.sample.webview.LZHtmlActivity;
import com.lazy.android.basefunc.LZWxpay.LZWxpayActivity;
import com.lazy.android.sample.demo.ADemoIndexActivity;
import com.lazy.android.baseui.base.LZBaseActivity;
import com.lazy.android.sample.divview.DivviewIndexActivity;
import com.lazy.android.sample.takePhoto.SampleTakePhotoActivity;
import com.lazy.android.sample.umeng.Activity.SampleUmengIndexActivity;
import com.lazy.android.sample.xutils.activity.SampleXutilsIndexActivity;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by chenglei on 16/1/16.
 */

@ContentView(R.layout.sample_index_activity)
public class SampleIndexActivity extends LZBaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
	}


//	demo界面
	@Event(R.id.demo)
	private void demo_click(View view){
		startActivity(new Intent(this, ADemoIndexActivity.class));
	}

//	xutils界面
	@Event(value = R.id.xutils,type = View.OnClickListener.class)
	private void xutilsEvent(View view){
		startActivity(new Intent(this, SampleXutilsIndexActivity.class));
	}

//	友盟第三方
	@Event(R.id.umeng)
	private void umengEvent(View view){
		startActivity(new Intent(this, SampleUmengIndexActivity.class));
	}



//	与html交互
	@Event(value = R.id.html,type = View.OnClickListener.class)
	private void htmlEvent(View view){
		startActivity(new Intent(this, LZHtmlActivity.class));
	}


//	视频录制
	@Event(value = R.id.recoder,type = View.OnClickListener.class)
	private void recoderEvent(View view){

		startActivity(new Intent(this, RecordMainActivity.class));
//		startActivity(new Intent(this, ActivityFFmpegRecorder.class));
//		startActivity(new Intent(this, ARecorderActivity.class));
	}


//	自定义视图界面
	@Event(value = R.id.divview,type = View.OnClickListener.class)
	private void divviewEvent(View view){
		startActivity(new Intent(this, DivviewIndexActivity.class));
	}

//	调用摄像头拍照
	@Event(R.id.takephoto)
	private void takephotoEvent(View view){
		startActivity(new Intent(this, SampleTakePhotoActivity.class));
	}


//	调用微信支付
	@Event(R.id.weixinpay)
	private void weixinpayEvent(View view){
		startActivity(new Intent(this, LZWxpayActivity.class));
	}


	//	支付宝支付<演示用>
	@Event(R.id.alipaydemo)
	private void alipaydemoEvent(View view){
		startActivity(new Intent(this, AlipayDemoActivity.class));
	}


	//	支付宝支付<用于正式项目，依赖服务器端支持>
	@Event(R.id.alipay)
	private void alipayEvent(View view){
		startActivity(new Intent(this, AlipayActivity.class));
	}


	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(false);
		mCommonHead.setMiddleTitle("lzAndroid");
	}
}
