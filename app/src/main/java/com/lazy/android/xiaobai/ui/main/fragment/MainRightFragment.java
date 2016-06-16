package com.lazy.android.xiaobai.ui.main.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazy.android.R;
import com.lazy.android.basefunc.LZHtml.LZWebView;

/**
 * Created by chenglei on 16/3/7.
 */

public class MainRightFragment extends Fragment {

	private View view;
	private LZWebView right_fragment_webview;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.xb_mian_fragment_right,null);
		right_fragment_webview = (LZWebView) view.findViewById(R.id.right_fragment_webview);
		right_fragment_webview.showWebView("dog-screen.html");
		return view;
	}
}
