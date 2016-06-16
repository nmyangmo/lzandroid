package com.lazy.android.xiaobai.ui.main.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.R;
import com.lazy.android.basefunc.LZHtml.LZWebView;
import com.lazy.android.basefunc.LZUtils.UtilsShared;
import com.lazy.android.baseui.common.CommonHead;
import com.lazy.android.xiaobai.data.WebviewIntentData;
import com.lazy.android.xiaobai.ui.common.CommonWebViewActivity;
import com.lazy.android.xiaobai.ui.main.fragment.MainLeftFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lazy.android.xiaobai.ui.register.activity.LoginActivity;
import com.lazy.android.xiaobai.ui.search.SearchActivity;

import java.util.ArrayList;

/**
 * Created by chenglei on 16/3/7.
 */
public class MainIndexActivity extends SlidingFragmentActivity implements View.OnClickListener {
	private LinearLayout index_linear_head, index_linear_pet, index_linear_cultivate, index_linear_shop;
	private ImageView index_linear_head_image, index_linear_pet_image, index_linear_cultivate_image, index_linear_shop_image;
	private TextView index_linear_head_text, index_linear_pet_text, index_linear_cultivate_text, index_linear_shop_text;
	private LZWebView index_webview;

	private ArrayList<ImageView> imagelist = new ArrayList<ImageView>();
	private ArrayList<TextView> textlist = new ArrayList<TextView>();
	private ArrayList<Integer> imageon = new ArrayList<Integer>();
	private ArrayList<Integer> imageoff = new ArrayList<Integer>();
	private String url = "";

	private Fragment fragmentleft;

	protected CommonHead mCommonHead = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xb_mian_activity_main);
		initview();
		initrightview();
		index_linear_head_click();
	}

	//	初始化头部
	private void initview() {

		index_linear_head = (LinearLayout) findViewById(R.id.index_linear_head);
		index_linear_pet = (LinearLayout) findViewById(R.id.index_linear_pet);
		index_linear_cultivate = (LinearLayout) findViewById(R.id.index_linear_cultivate);
		index_linear_shop = (LinearLayout) findViewById(R.id.index_linear_shop);
		index_webview = (LZWebView) findViewById(R.id.index_webview);
		index_linear_head.setOnClickListener(this);
		index_linear_pet.setOnClickListener(this);
		index_linear_cultivate.setOnClickListener(this);
		index_linear_shop.setOnClickListener(this);

		index_linear_head_image = (ImageView) findViewById(R.id.index_linear_head_image);
		index_linear_pet_image = (ImageView) findViewById(R.id.index_linear_pet_image);
		index_linear_cultivate_image = (ImageView) findViewById(R.id.index_linear_cultivate_image);
		index_linear_shop_image = (ImageView) findViewById(R.id.index_linear_shop_image);
		index_linear_head_text = (TextView) findViewById(R.id.index_linear_head_text);
		index_linear_pet_text = (TextView) findViewById(R.id.index_linear_pet_text);
		index_linear_cultivate_text = (TextView) findViewById(R.id.index_linear_cultivate_text);
		index_linear_shop_text = (TextView) findViewById(R.id.index_linear_shop_text);


		imagelist.add(index_linear_head_image);
		imagelist.add(index_linear_pet_image);
		imagelist.add(index_linear_cultivate_image);
		imagelist.add(index_linear_shop_image);


		textlist.add(index_linear_head_text);
		textlist.add(index_linear_pet_text);
		textlist.add(index_linear_cultivate_text);
		textlist.add(index_linear_shop_text);

		imageon.add(R.drawable.xb_mainb_index_on);
		imageon.add(R.drawable.xb_mainb_dogs_on);
		imageon.add(R.drawable.xb_mainb_cultivate_on);
		imageon.add(R.drawable.xb_mainb_shop_on);


		imageoff.add(R.drawable.xb_mainb_index_off);
		imageoff.add(R.drawable.xb_mainb_dogs_off);
		imageoff.add(R.drawable.xb_mainb_cultivate_off);
		imageoff.add(R.drawable.xb_mainb_shop_off);

	}


	private void selectmenu(int position) {
		for (int i = 0; i < 4; i++) {
			if (i == position) {
				imagelist.get(i).setImageResource(imageon.get(i));
				textlist.get(i).setTextColor(getResources().getColor(R.color.main));
			} else {
				imagelist.get(i).setImageResource(imageoff.get(i));
				textlist.get(i).setTextColor(getResources().getColor(R.color.textcolor666));
			}
		}
	}


	//	初始化首页头部视图
	private void initindexhead() {

		if (mCommonHead == null) {
			mCommonHead = (CommonHead) findViewById(R.id.common_head);
		}

		mCommonHead.initheadtop(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getSlidingMenu().showMenu();
			}
		}, 0, "", null, R.drawable.xb_head_imgright_add, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (UtilsShared.getBoolean(MainIndexActivity.this, ConfigStaticType.SettingField.XB_LOGIN_SUCCESS, false)) {
					String shared = UtilsShared.getString(MainIndexActivity.this, ConfigStaticType.SettingField.XB_ISSHARE, "0");
					shared  = "1";
					if (shared.equals("1")) {
						startActivity(new Intent(MainIndexActivity.this, ShowAddtypeActivity.class));
					} else {
						Toast.makeText(MainIndexActivity.this, "您没有发布权限", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(MainIndexActivity.this, "您没有登录", Toast.LENGTH_SHORT).show();
					startActivity(new Intent(MainIndexActivity.this, LoginActivity.class));
				}


			}
		});
		mCommonHead.setMiddleTitle("足迹");
	}

	//	初始化狗狗头部视图
	private void initpethead() {
		if (mCommonHead == null) {
			mCommonHead = (CommonHead) findViewById(R.id.common_head);
		}

		mCommonHead.initheadtop(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getSlidingMenu().showMenu();
			}
		}, 1, "寻找狗狗", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainIndexActivity.this, SearchActivity.class);
				intent.putExtra("type", SearchActivity.pet);
				MainIndexActivity.this.startActivity(intent);
			}
		}, R.drawable.xb_head_shopcar, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WebviewIntentData webviewIntentData = new WebviewIntentData("购物车", "set-shoppingcart.html");
				Intent intent = new Intent(MainIndexActivity.this, CommonWebViewActivity.class);
				intent.putExtra("intentdata", webviewIntentData);
				startActivity(intent);
			}
		});
	}

	//	初始化狗狗培训页
	private void initcultivate() {

		if (mCommonHead == null) {
			mCommonHead = (CommonHead) findViewById(R.id.common_head);
		}

		mCommonHead.initheadtop(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getSlidingMenu().showMenu();
			}
		}, 0, "", null, R.drawable.xb_head_imgright_add, new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				WebviewIntentData webviewIntentData = new WebviewIntentData("我的培训", "train-mytrain.html");
				Intent intent = new Intent(MainIndexActivity.this, CommonWebViewActivity.class);
				intent.putExtra("intentdata", webviewIntentData);
				startActivity(intent);
			}
		});
		mCommonHead.setRightLayoutText("课程表");
		mCommonHead.setMiddleTitle("培训");
	}


	//	初始化狗狗商城页
	private void initshophead() {

		if (mCommonHead == null) {
			mCommonHead = (CommonHead) findViewById(R.id.common_head);
		}

		mCommonHead.initheadtop(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getSlidingMenu().showMenu();
			}
		}, 1, "搜索在线商品", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainIndexActivity.this, SearchActivity.class);
				intent.putExtra("type", SearchActivity.shop);
				MainIndexActivity.this.startActivity(intent);
			}
		}, R.drawable.xb_head_shopcar, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WebviewIntentData webviewIntentData = new WebviewIntentData("购物车", "set-shoppingcart.html");
				Intent intent = new Intent(MainIndexActivity.this, CommonWebViewActivity.class);
				intent.putExtra("intentdata", webviewIntentData);
				startActivity(intent);

			}
		});
	}


	//	初始化左侧划出菜单
//	设置左右侧的菜单
	private void initrightview() {
		fragmentleft = new MainLeftFragment();
		setBehindContentView(R.layout.xb_mian_activity_left);
		getSupportFragmentManager().beginTransaction().replace(R.id.main_left_fragment, fragmentleft).commit();

		SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT);
		// 设置触摸屏幕的模式
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
//		menu.setShadowWidthRes(R.dimen.shadow_width);
//		menu.setShadowDrawable(R.drawable.shadow);
		// 设置滑动菜单视图的宽度
		menu.setBehindOffsetRes(
			R.dimen.px_120);
//		menu.setBehindWidth()
		// 设置渐入渐出效果的值
//		menu.setFadeDegree(0.01f);
//		 menu.setBehindScrollScale(1.0f);
//		menu.setSecondaryShadowDrawable(R.drawable.shadow);
		//设置右边（二级）侧滑菜单
		menu.setSecondaryMenu(R.layout.xb_mian_activity_right);


//		Fragment fragmentright = new MainRightFragment();
//		getSupportFragmentManager().beginTransaction().replace(R.id.main_right_fragment, fragmentright).commit();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.index_linear_head:
				index_linear_head_click();
				break;
			case R.id.index_linear_pet:
				index_linear_pet_click();
				break;
			case R.id.index_linear_cultivate:
				index_linear_cultivate_click();
				break;
			case R.id.index_linear_shop:
				index_linear_shop_click();
				break;
		}
	}


	private void index_linear_shop_click() {
		initshophead();
		selectmenu(3);
		url = "supplies-supppage.html";
		index_webview.showWebView(url);

	}

	private void index_linear_cultivate_click() {
		initcultivate();
		selectmenu(2);
		url = "train-homepage.html?cat=492&menulist=3";
		index_webview.showWebView(url);

	}

	private void index_linear_pet_click() {
		initpethead();
		selectmenu(1);
		url = "dog-homepage.html";
		index_webview.showWebView(url);

	}

	private void index_linear_head_click() {
		initindexhead();
		selectmenu(0);
		url = "show-homepage.html";
//		url = "http://app.zhuli6.com/cs.html";
		index_webview.showWebView(url);
	}

	//	刷新头像
	public void refreshhead() {
		mCommonHead.refreshHeadimg();
	}


	@Override
	protected void onResume() {
		super.onResume();
		mCommonHead.refreshHeadimg();
		index_webview.showWebView(url);
	}
}
