package com.lazy.android.sample.divview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lazy.android.R;
import com.lazy.android.baseui.base.LZBaseActivity;
import com.lazy.android.baseui.crumbs.CrumbsPickerDiyActivity;
import com.lazy.android.sample.divview.activityButtonup.ActivityButtonUpActivity;
import com.lazy.android.sample.divview.circleimageview.CircleImageViewActivity;
import com.lazy.android.sample.divview.netlist.NetListActivity;
import com.lazy.android.sample.divview.slidingmenu.SlidingmenuIndexActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/4.
 */
@ContentView(R.layout.sample_divview_index_activity)
public class DivviewIndexActivity extends LZBaseActivity {
	private ArrayList<String> selectdata = new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();

	}

	@Event(value = R.id.SlidingMenu,type = View.OnClickListener.class)
	private void slidingmenuEvent(View view){
		startActivity(new Intent(this, SlidingmenuIndexActivity.class));
	}

	@Event(value = R.id.circleimageview,type = View.OnClickListener.class)
	private void circleimageviewEvent(View view){
		startActivity(new Intent(this, CircleImageViewActivity.class));
	}

	@Event(value = R.id.activity_button_up,type = View.OnClickListener.class)
	private void activity_button_up_Event(View view){
		startActivity(new Intent(this, ActivityButtonUpActivity.class));
	}



//	简单选择拾取器
	@Event(R.id.sexpick)
	private void sexpick_Event(View view){
		selectdata.clear();
		selectdata.add("数据1");
		selectdata.add("数据2");
		selectdata.add("数据3");
		selectdata.add("数据4");
		Intent intent = new Intent(this, CrumbsPickerDiyActivity.class);
		intent.putExtra("data",selectdata);
		intent.putExtra("title","标题");
		startActivityForResult(intent, CrumbsPickerDiyActivity.RESULT_CODE);
	}



	//	返回结果处理
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
//		性别拾取器
		if(resultCode == CrumbsPickerDiyActivity.RESULT_CODE){
			int position = data.getIntExtra(CrumbsPickerDiyActivity.INTENT_KEY,0);
			ToastShow(selectdata.get(position));
		}

		if(resultCode == CrumbsPickerDiyActivity.RESULT_CODE){
			int position = data.getIntExtra(CrumbsPickerDiyActivity.INTENT_KEY,0);
		}
	}


	@Event(R.id.netlist)
	private void netlist_click(View view){
		startActivity(new Intent(this,NetListActivity.class));
	}


	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("自定义界面");
	}



}
