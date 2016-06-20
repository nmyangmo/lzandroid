package com.lazy.android.xiaobai.ui.personSet;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lazy.android.config.ConfigProtocolType;
import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.baseprotocol.LZHttpProtocolHandlerBase;
import com.lazy.android.basefunc.LZUtils.UtilsDate;
import com.lazy.android.basefunc.LZUtils.UtilsDownload;
import com.lazy.android.baseui.base.LZBaseActivity;
import com.lazy.android.baseui.common.DateTimePickDialog;
import com.lazy.android.baseui.crumbs.CrumbsPickerDiyActivity;
import com.lazy.android.baseui.crumbs.CrumbsTakephotoActivity;
import com.lazy.android.xiaobai.ui.personSet.data.PetInfoBean;
import com.lazy.android.xiaobai.ui.personSet.data.PetTypeBean;
import com.lazy.android.xiaobai.ui.personSet.protocol.AddMypetProtocol;
import com.lazy.android.xiaobai.ui.personSet.protocol.EditMypetProtocol;
import com.lazy.android.xiaobai.ui.personSet.protocol.GetMypetProtocol;
import com.lazy.android.xiaobai.ui.personSet.protocol.GetpetTypeProtocol;
import com.lazy.android.xiaobai.ui.personSet.protocol.PetHeadimgEditProtocol;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenglei on 16/3/12.
 */


@ContentView(R.layout.xb_personset_addmypet_activity)
public class AddMypetActivity extends LZBaseActivity {

	@ViewInject(R.id.addpet_headimg)
	private CircleImageView addpet_headimg;
	@ViewInject(R.id.addpet_petname)
	private EditText addpet_petname;
	@ViewInject(R.id.addpet_sexwomen_img)
	private ImageView addpet_sexwomen_img;
	@ViewInject(R.id.addpet_sexwomen_text)
	private TextView addpet_sexwomen_text;
	@ViewInject(R.id.addpet_sexmen_img)
	private ImageView addpet_sexmen_img;
	@ViewInject(R.id.addpet_sexmen_text)
	private TextView addpet_sexmen_text;
	@ViewInject(R.id.addpet_pettype)
	private EditText addpet_pettype;
	@ViewInject(R.id.addpet_brithday)
	private EditText addpet_brithday;
	@ViewInject(R.id.addpet_hometime)
	private EditText addpet_hometime;
	@ViewInject(R.id.addpet_button)
	private EditText addpet_button;
	@ViewInject(R.id.addpet_sexwomen_click)
	private LinearLayout addpet_sexwomen_click;
	@ViewInject(R.id.addpet_sexmen_click)
	private LinearLayout addpet_sexmen_click;


	private String headimg;
	private ArrayList<PetTypeBean> pettypes;
	private ArrayList<String> pettypename = new ArrayList<String>();

	//	宠物种类position
	private int typeposition;
	private int typeid;
	private long birthdayTimeInMillis;
	private long tohomeTimeInMillis;
	private ConfigStaticType.SEX petSex = ConfigStaticType.SEX.NULL;

	private String initStartDateTime = "2013年9月3日"; // 初始化开始时间

	private int petid;
	private PetInfoBean petinfo;

	private String headimgurl;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initCommonHead();
		initdata();
		petid = getIntent().getIntExtra("id",0);
//		petid = 1;
		if(petid != 0 ){
			new GetMypetProtocol(this,this,petid);
		}
	}

	//	加载数据
	private void initdata() {
		new GetpetTypeProtocol(this, this);
	}


	//	选择MM性别
	@Event(R.id.addpet_sexwomen_click)
	private void addpet_sexwomen_click_Event(View v) {
		petSex = ConfigStaticType.SEX.WOMEN;
		addpet_sexwomen_img.setImageResource(R.drawable.xb_sex_women);
		addpet_sexwomen_text.setTextColor(getResources().getColor(R.color.textredea6e76));
		addpet_sexmen_img.setImageResource(R.drawable.xb_sex_middle);
		addpet_sexmen_text.setTextColor(getResources().getColor(R.color.textcolor999));
	}

	//	选择GG性别
	@Event(R.id.addpet_sexmen_click)
	private void addpet_sexmen_click_Event(View v) {
		petSex = ConfigStaticType.SEX.MEN;
		addpet_sexwomen_img.setImageResource(R.drawable.xb_sex_middle);
		addpet_sexwomen_text.setTextColor(getResources().getColor(R.color.textcolor999));
		addpet_sexmen_img.setImageResource(R.drawable.xb_sex_men);
		addpet_sexmen_text.setTextColor(getResources().getColor(R.color.textblue4eabe1));
	}


	//	点击头像跳转
	@Event(R.id.addpet_headimg)
	private void addpet_headimg_Event(View v) {
		startActivityForResult(new Intent(this, CrumbsTakephotoActivity.class), CrumbsTakephotoActivity.RESULT_ONE_CODE);
	}


	//	品种选择
	@Event(R.id.addpet_pettype)
	private void addpet_pettype_Event(View view) {
		Intent intent = new Intent(this, CrumbsPickerDiyActivity.class);
		intent.putExtra("data", pettypename);
		intent.putExtra("title", "选择品种");
		startActivityForResult(intent, CrumbsPickerDiyActivity.RESULT_CODE);
	}


	//	宠物生日选择器
	@Event(R.id.addpet_brithday)
	private void addpet_brithday_Event(View view) {
		DateTimePickDialog dateTimePicKDialog = new DateTimePickDialog(this, "");
		dateTimePicKDialog.dateTimePicKDialog(addpet_brithday);
		birthdayTimeInMillis = dateTimePicKDialog.TimeInMillis;
	}


	//	宠物到家时间选择器
	@Event(R.id.addpet_hometime)
	private void addpet_hometime_Event(View view) {
		DateTimePickDialog dateTimePicKDialog = new DateTimePickDialog(this, "");
		dateTimePicKDialog.dateTimePicKDialog(addpet_hometime);
		tohomeTimeInMillis = dateTimePicKDialog.TimeInMillis;
	}

	//	点击保存按钮
	@Event(R.id.addpet_button)
	private void addpet_button_Event(View view) {

		if(petid != 0 ){
			new EditMypetProtocol(this, this,petid,
				addpet_petname.getText().toString(),
				petSex.key,
				typeid,
				birthdayTimeInMillis,
				tohomeTimeInMillis);
		}else{
			new AddMypetProtocol(this, this,
				addpet_petname.getText().toString(),
				petSex.key,
				typeid,
				birthdayTimeInMillis,
				tohomeTimeInMillis,
				headimg);
		}

	}


	//	返回结果处理
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == CrumbsTakephotoActivity.RESULT_ONE_CODE) {
			headimg = data.getStringExtra("headimg");
			if(petid == 0){
				addpet_headimg.setImageBitmap(BitmapFactory.decodeFile(data.getStringExtra("headimg")));
			}else {
				new PetHeadimgEditProtocol(this,this,headimg,"1",petinfo.getMpet_id()+"");
			}
		} else if (resultCode == CrumbsPickerDiyActivity.RESULT_CODE) {
			typeposition = data.getIntExtra(CrumbsPickerDiyActivity.INTENT_KEY, 0);
			typeid = pettypes.get(data.getIntExtra(CrumbsPickerDiyActivity.INTENT_KEY, 0)).getTypeid();
			addpet_pettype.setText(pettypename.get(typeposition));
		}
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
			if (protocol.getProtocolType() == ConfigProtocolType.GET_ALLPETS_TYPE) {
				GetpetTypeProtocol getpetTypeProtocol = (GetpetTypeProtocol) protocol;
//				1、获得数据
				pettypes = getpetTypeProtocol.resultArray;
//				2、获得选择框需要的数据
				for (int i = 0; i < pettypes.size(); i++) {
					pettypename.add(pettypes.get(i).getTypename());
				}
			}else if(protocol.getProtocolType() == ConfigProtocolType.ADD_PET){
				AddMypetProtocol addMypetProtocol = (AddMypetProtocol)protocol;
				if(addMypetProtocol.result){
					this.finish();
				}
			}else if(protocol.getProtocolType() == ConfigProtocolType.EDIT_PET){
				EditMypetProtocol editMypetProtocol = (EditMypetProtocol)protocol;
				if(editMypetProtocol.result){
					this.finish();
				}
			}else if(protocol.getProtocolType() == ConfigProtocolType.GET_PET){
				GetMypetProtocol getMypetProtocol = (GetMypetProtocol)protocol;
				petinfo = getMypetProtocol.bean;
				initPetData(petinfo);
			}

		} else {
			ToastShow(protocol.getProtocolErrorMessage());
		}
	}


//	异步更新头像
	Handler handler = new Handler() {
		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
				case 1:
					//ui 方法
					addpet_headimg.setImageBitmap(BitmapFactory.decodeFile(headimgurl));
			}
			super.handleMessage(msg);
		}
	};
	//	初始化带ID的数据
	private void initPetData(PetInfoBean bean) {

		addpet_petname.setText(bean.getPetname());
		headimgurl = UtilsDownload.bindHeadImage(bean.getPetimgurl());

		Message message = handler.obtainMessage(1);
		handler.sendMessageDelayed(message, 500); // 发送message


		if(bean.getPetsex() == 1){
			addpet_sexwomen_click_Event(addpet_sexwomen_click);
		}else{
			addpet_sexmen_click_Event(addpet_sexmen_click);
		}
		addpet_pettype.setText(bean.getPetcatname()+"");
		addpet_brithday.setText(UtilsDate.getYYYYMMDDCN(bean.getPetbirthday()*1000)+"");
		birthdayTimeInMillis = bean.getPetbirthday()*1000;
		addpet_hometime.setText(UtilsDate.getYYYYMMDDCN(bean.getPethometime()*1000)+"");
		tohomeTimeInMillis = bean.getPethometime()*1000;
	}

	/**
		 * 设置头部布局
		 */
		@Override
		public void initCommonHead () {
			super.initCommonHead();
			mCommonHead.setLeftLayoutVisible(true);
			mCommonHead.setMiddleTitle("添加宠物");
		}


	}
