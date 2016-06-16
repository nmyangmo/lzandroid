package com.lazy.android.xiaobai.ui.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lazy.android.R;
import com.lazy.android.basefunc.LZRecord.RecordMainActivity;
import com.lazy.android.basefunc.LZTakePhoto.LZTakePhotoConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by chenglei on 16/3/9.
 */

public class ShowAddtypeActivity extends Activity implements View.OnClickListener {
	//	必须定义的变量
	private FunctionConfig.Builder functionConfigBuilder;
	private List<PhotoInfo> mPhotoList = new ArrayList<>();
	private LinearLayout movieclick,addphotoclick,addphotosclick;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xb_main_showaddtype_activity);
		initview();
		//		初始化 相册控件   必须
		LZTakePhotoConfig.init(this);
	}

//	初始化页面
	private void initview() {
		movieclick = (LinearLayout) findViewById(R.id.movieclick);
		movieclick.setOnClickListener(this);
		addphotoclick = (LinearLayout) findViewById(R.id.addphotoclick);
		addphotoclick.setOnClickListener(this);
		addphotosclick = (LinearLayout) findViewById(R.id.addphotosclick);
		addphotosclick.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.movieclick:
				movieclick();
				this.finish();
				break;
			case R.id.addphotoclick:
				addphotoclick();
				this.finish();
				break;
			case R.id.addphotosclick:
				addphotosclick();
				this.finish();
				break;
		}

	}

//	发表相册的按钮
	private void addphotosclick() {

		//		多选打开相册配置
		functionConfigBuilder = new FunctionConfig.Builder();

		functionConfigBuilder.setMutiSelectMaxSize(4);//配置多选数量
		functionConfigBuilder.setEnableEdit(true);//开启编辑功能
		functionConfigBuilder.setEnableRotate(true);//开启旋转功能
		functionConfigBuilder.setEnableCrop(true);//开启裁剪功能
		functionConfigBuilder.setCropSquare(true);
		functionConfigBuilder.setEnableCamera(true);
		functionConfigBuilder.setEnablePreview(true);//是否开启预览功能
		functionConfigBuilder.setSelected(mPhotoList);//添加过滤集合 必加
		GalleryFinal.openGalleryMuti(LZTakePhotoConfig.REQUEST_CODE_GALLERY, functionConfigBuilder.build(), mOnHanlderResultCallback);



	}

//	发表拍照的按钮
	private void addphotoclick() {
		//		使用拍照配置
		functionConfigBuilder = new FunctionConfig.Builder();
		functionConfigBuilder.setEnableEdit(true);//开启编辑功能
		functionConfigBuilder.setEnableCrop(true);//开启裁剪功能
		functionConfigBuilder.setEnableRotate(true);//开启旋转功能
		functionConfigBuilder.setEnableCamera(true);//开启相机功能
		functionConfigBuilder.setCropSquare(true);//裁剪正方形
//		functionConfigBuilder.takePhotoFolter(File file);//配置拍照保存目录，不做配置的话默认是/sdcard/DCIM/GalleryFinal/
		functionConfigBuilder.setForceCrop(true);//启动强制裁剪功能,一进入编辑页面就开启图片裁剪，不需要用户手动点击裁剪，此功能只针对单选操作
		functionConfigBuilder.setForceCropEdit(false);//在开启强制裁剪功能时是否可以对图片进行编辑（也就是是否显示旋转图标和拍照图标）
		functionConfigBuilder.setEnablePreview(true);//是否开启预览功能
		functionConfigBuilder.setSelected(mPhotoList);//添加过滤集合  必加
		GalleryFinal.openCamera(LZTakePhotoConfig.REQUEST_CODE_CAMERA,functionConfigBuilder.build(), mOnHanlderResultCallback);
	}

//	发表视频的按钮
	private void movieclick() {
		startActivity(new Intent(this, RecordMainActivity.class));
	}



	//	返回回调
	private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
		@Override
		public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
			mPhotoList.addAll(resultList);
			if (resultList != null) {
//				Log.i("chenglei","返回的标识码为 ： " + reqeustCode);
				Intent intent = new Intent(ShowAddtypeActivity.this, ShowSendActivity.class);
				intent.putExtra("photos", (Serializable) resultList);
				startActivity(intent);
				ShowAddtypeActivity.this.finish();
			}
		}

		@Override
		public void onHanlderFailure(int requestCode, String errorMsg) {
			Toast.makeText(ShowAddtypeActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
		}
	};

}

