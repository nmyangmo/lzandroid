package com.lazy.android.xiaobai.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.lazy.android.R;
import com.lazy.android.baseui.base.LZBaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.Random;

/**
 * Created by chenglei on 16/5/23.
 */
@ContentView(R.layout.xb_init_activity)
public class InitActivity extends LZBaseActivity {

	@ViewInject(R.id.init_leadimg)
	ImageView init_leadimg;

//	三秒后跳转页面
	private Handler mhandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			startActivity(new Intent(InitActivity.this,MainIndexActivity.class));
			InitActivity.this.finish();
		}
	};


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Random random = new Random();
		int num = random.nextInt(5);
		init_leadimg.setImageResource(R.drawable.xb_init1);
		switch (num){
			case 0:
				init_leadimg.setImageResource(R.drawable.xb_init1);
				break;
			case 1:
				init_leadimg.setImageResource(R.drawable.xb_init2);
				break;
			case 2:
				init_leadimg.setImageResource(R.drawable.xb_init3);
				break;
			case 3:
				init_leadimg.setImageResource(R.drawable.xb_init4);
				break;
			case 4:
				init_leadimg.setImageResource(R.drawable.xb_init5);
				break;
		}
		new Thread(){
			@Override
			public void run() {
				try {
					sleep(2000);
					Message message = new Message();
					message.what = 1;
					mhandler.sendMessage(message);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}
}
