package com.lazy.android.xiaobai.ui.main.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.basefunc.LZRecord.PlayVideoActiviy;
import com.lazy.android.basefunc.LZUtils.UtilsDownload;
import com.lazy.android.baseui.base.LZBaseActivity;
import com.lazy.android.xiaobai.ui.main.adapter.ShowSendImageAdapter;
import com.lazy.android.xiaobai.ui.main.adapter.ShowSendMypetAdapter;
import com.lazy.android.xiaobai.ui.main.adapter.ShowSendTypeAdapter;
import com.lazy.android.xiaobai.ui.main.data.ShowsendMypetBean;
import com.lazy.android.xiaobai.ui.main.data.ShowsendTypeBean;
import com.lazy.android.xiaobai.ui.main.protocol.ShowAddMovieProtocol;
import com.lazy.android.xiaobai.ui.main.protocol.ShowAddPhotoProtocol;
import com.lazy.android.xiaobai.ui.main.protocol.ShowSendGetMypetProtocol;
import com.lazy.android.xiaobai.ui.main.protocol.ShowSendGettypeProtocol;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by chenglei on 16/3/9.
 */
@ContentView(R.layout.xb_show_send_activity)
public class ShowSendActivity extends LZBaseActivity {

	@ViewInject(R.id.showsend_photo)
	private LinearLayout showsend_photo;
	@ViewInject(R.id.showsend_photo_img)
	private GridView showsend_photo_img;
	@ViewInject(R.id.showsend_photo_text)
	private TextView showsend_photo_text;

	@ViewInject(R.id.showsend_movie)
	private LinearLayout showsend_movie;
	@ViewInject(R.id.showsend_movie_movie)
	private ImageView showsend_movie_movie;
	@ViewInject(R.id.showsend_movie_text)
	private TextView showsend_movie_text;


	@ViewInject(R.id.showsend_selecttype)
	private GridView showsend_selecttype;
	@ViewInject(R.id.showsend_selectpet)
	private GridView showsend_selectpet;
	@ViewInject(R.id.showsend_bottom)
	private LinearLayout showsend_bottom;
	private String movepath;
	private String moveImgPath;


	//	图片列表
	private ArrayList<HashMap<String, Object>> showSendImage = new ArrayList<HashMap<String, Object>>();
	private ShowSendImageAdapter showSendImageAdapter;
	//	发表类型列表
	private ArrayList<HashMap<String, Object>> showSendType = new ArrayList<HashMap<String, Object>>();
	private ShowSendTypeAdapter showSendTypeAdapter;
	//	宠物列表
	private ArrayList<HashMap<String, Object>> showSendmypets = new ArrayList<HashMap<String, Object>>();
	private ShowSendMypetAdapter showSendmypetsAdapter;

	private boolean isPhoto = true;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
		movepath = getIntent().getStringExtra("path");
		moveImgPath = getIntent().getStringExtra("imagePath");

		if (movepath != null) {
			isPhoto = false;
			showsend_movie_movie.setImageBitmap(BitmapFactory.decodeFile(moveImgPath));
			showsend_movie_movie.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(ShowSendActivity.this, PlayVideoActiviy.class);
					intent.putExtra(PlayVideoActiviy.KEY_FILE_PATH, movepath);
					intent.putExtra(PlayVideoActiviy.KEY_IMG_PATH, moveImgPath);
					ShowSendActivity.this.startActivity(intent);
				}
			});
		} else {
			isPhoto = true;
		}

		initView();
		initdata();

	}


	//	初始化视图
	@Override
	public void initView() {
		super.initView();
//		图片列表的适配器
//		showSendImageAdapter = new SimpleAdapter(this, //没什么解释
//			showSendImage,//数据来源
//			R.layout.xb_show_send_adapter_image,//night_item的XML实现
//			//动态数组与ImageItem对应的子项
//			new String[]{"imageurl"},
//			//ImageItem的XML文件里面的一个ImageView,两个TextView ID
//			new int[]{R.id.showsend_adapter_image});
		if (isPhoto) {
			showsend_photo.setVisibility(View.VISIBLE);
			showsend_movie.setVisibility(View.GONE);
			showSendImageAdapter = new ShowSendImageAdapter(this, R.layout.xb_show_send_adapter_image, showSendImage);
			showsend_photo_img.setAdapter(showSendImageAdapter);
		} else {
			showsend_photo.setVisibility(View.GONE);
			showsend_movie.setVisibility(View.VISIBLE);
		}

//		发表类型列表的适配器
//		showSendTypeAdapter = new SimpleAdapter(this, //没什么解释
//			showSendType,//数据来源
//			R.layout.xb_show_send_adapter_type,//night_item的XML实现
//			//动态数组与ImageItem对应的子项
//			new String[]{"type"},
//			//ImageItem的XML文件里面的一个ImageView,两个TextView ID
//			new int[]{R.id.showsend_adapter_typetext});
		showSendTypeAdapter = new ShowSendTypeAdapter(this, R.layout.xb_show_send_adapter_type, showSendType);
		showsend_selecttype.setAdapter(showSendTypeAdapter);
		showsend_selecttype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				showSendTypeAdapter.typeid = Integer.valueOf(showSendType.get(position).get("id").toString());
				showSendTypeAdapter.setPositon(position);
			}
		});

//		宠物列表的适配器
//		showSendmypetsAdapter = new SimpleAdapter(this, //没什么解释
//			showSendmypets,//数据来源
//			R.layout.xb_show_send_adapter_mypet,//night_item的XML实现
//			//动态数组与ImageItem对应的子项
//			new String[]{"pet"},
//			//ImageItem的XML文件里面的一个ImageView,两个TextView ID
//			new int[]{R.id.showsend_adapter_mypets_headimg});

		showSendmypetsAdapter = new ShowSendMypetAdapter(this, R.layout.xb_show_send_adapter_mypet, showSendmypets);
		showsend_selectpet.setAdapter(showSendmypetsAdapter);
		showsend_selectpet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				showSendmypetsAdapter.mypetid = Integer.valueOf(showSendmypets.get(position).get("id").toString());
				showSendmypetsAdapter.setPositon(position);
			}
		});
	}

	//	初始化数据源
	private void initdata() {

		if (isPhoto) {
//		组装图片的数据源
			List<PhotoInfo> list = (List<PhotoInfo>) getIntent().getSerializableExtra("photos");
			for (int i = 0; i < list.size(); i++) {
				HashMap hashMap = new HashMap();
				hashMap.put("photo", list.get(i).getPhotoPath());
				showSendImage.add(hashMap);
			}
			showSendImageAdapter.notifyDataSetChanged();
		}


//		获得发布类型的网络请求
		new ShowSendGettypeProtocol(this, this);
//		获得宠物的列表
		new ShowSendGetMypetProtocol(this, this);

	}


//	网络请求的处理


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
			//		添加动态的类型
			if (protocol.getProtocolType() == ConfigProtocolType.SHOWADD_TYPE) {
				ShowSendGettypeProtocol showSendGettypeProtocol = (ShowSendGettypeProtocol) protocol;
				ArrayList<ShowsendTypeBean> resultArray = showSendGettypeProtocol.resultArray;
				for (int i = 0; i < resultArray.size(); i++) {
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("type", resultArray.get(i).getTag_name());
					hashMap.put("id", resultArray.get(i).getTag_id());
					showSendType.add(hashMap);
				}
				showSendTypeAdapter.notifyDataSetChanged();
			}

			//		添加动态的类型
			if (protocol.getProtocolType() == ConfigProtocolType.SHOWADD_MYPETS) {
				ShowSendGetMypetProtocol showSendGetMypetProtocol = (ShowSendGetMypetProtocol) protocol;
				ArrayList<ShowsendMypetBean> resultArray = showSendGetMypetProtocol.resultArray;
				for (int i = 0; i < resultArray.size(); i++) {
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					String localimg = UtilsDownload.bindHeadImage(resultArray.get(i).getPetimgurl());
					hashMap.put("pet", localimg);
					hashMap.put("id", resultArray.get(i).getMpet_id());
					showSendmypets.add(hashMap);
				}
				showSendmypetsAdapter.notifyDataSetChanged();
			}

//			添加动态后的网络请求
			if (protocol.getProtocolType() == ConfigProtocolType.SHOWADD) {
				if(protocol.getProtocolStatus() == ConfigStaticType.ProtocolStatus.RESULT_SUCCESS){
					this.finish();
				}
			}


		}
	}

	/**
	 * 加载本地图片
	 *
	 * @param url
	 * @return
	 */
	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 设置头部布局
	 */
	@Override
	public void initCommonHead() {
		super.initCommonHead();
		mCommonHead.setLeftLayoutVisible(true);
		mCommonHead.setMiddleTitle("小白养宠");
		mCommonHead.setRightLayoutText("发表");
		mCommonHead.setRightLayoutTextClick(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				int label = showSendTypeAdapter.typeid;
				int petid = showSendmypetsAdapter.mypetid;

				if (isPhoto) {
					String content = showsend_photo_text.getText().toString();
					ArrayList<File> photos = new ArrayList<File>();
					for (int i = 0; i < showSendImage.size(); i++) {
						File file = new File(showSendImage.get(i).get("photo").toString());
						photos.add(file);
					}
					new ShowAddPhotoProtocol(ShowSendActivity.this, ShowSendActivity.this, content, photos, label, petid);
				} else {
					String content = showsend_movie_text.getText().toString();
					new ShowAddMovieProtocol(ShowSendActivity.this, ShowSendActivity.this, content, movepath, moveImgPath, label, petid);
				}

			}
		});
	}


	public void showDialog() {
		//		String filestring = Environment.getExternalStorageDirectory().getPath()+"/nologin.png";
//		Bitmap bitmap = getLoacalBitmap(filestring);
//		headimage.setImageBitmap(bitmap);
//
//		LZDialogDiy.Builder builder = new LZDialogDiy.Builder(this);
//		builder.setMessage("发布成功");
//		builder.setTitle("提示");
//		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//				//设置你的操作事项
//			}
//		});
//
//		builder.setNegativeButton("取消",
//			new android.content.DialogInterface.OnClickListener() {
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//				}
//			});
//
//		builder.create(R.layout.xb_dialog_sendsuccess).show();
	}


	public void initkeyborad() {

//		final ScrollView myLayout = (ScrollView) findViewById(R.id.showsend_root);
//		myLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//			/**
//			 * the result is pixels
//			 */
//			@Override
//			public void onGlobalLayout() {
//
//				Rect r = new Rect();
//				myLayout.getWindowVisibleDisplayFrame(r);
//
//				int screenHeight = myLayout.getRootView().getHeight();
//				int heightDifference = screenHeight - (r.bottom - r.top);
//				Log.i("chenglei", "heightDifference=" + heightDifference);
//				showsend_bottom.getLayoutParams().height = heightDifference;
//			}
//		});
	}


}
