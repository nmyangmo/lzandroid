package com.lazy.android.xiaobai.ui.search;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lazy.android.R;
import com.lazy.android.basefunc.LZHtml.LZWebView;
import com.lazy.android.baseui.base.LZBaseActivityI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by chenglei on 16/3/10.
 */
@ContentView(R.layout.xb_pet_search)
public class SearchActivity extends LZBaseActivityI {

	public final static int pet = 1;
	public final static int shop = 2;

	@ViewInject(R.id.search_back) ImageView search_back;
	@ViewInject(R.id.search_text) EditText search_text;
	@ViewInject(R.id.search_button) Button search_button;
	@ViewInject(R.id.search_webview)
	LZWebView search_webview;

	private int type ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		type = getIntent().getIntExtra("type", 0);
		if(type == pet){
			search_text.setText("搜索您想找的狗狗");
		} else if (type == shop) {
			search_text.setText("搜索在线商品");
		}


		search_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					search_text.setText("");
					return;
				}
			}
		});

//		search_text.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				search_text.setText("");
//			}
//		});

		if(type == pet){
			search_webview.showWebView("sod.html");
		}else if(type == shop){
			search_webview.showWebView("sop.html");
		}
	}



	@Event(R.id.search_button)
	private void search_button_Event(View v){

		String keyword = search_text.getText().toString();
		if(type == pet){
			search_webview.showWebView("/list.html?cat=502&keyword=" + keyword);
		}else if(type == shop){
			search_webview.showWebView("/listt.html?cat=503&keyword=" + keyword);
		}

	}

	@Event(R.id.search_back)
	private void search_back_Event(View v){

		this.finish();

	}



}
