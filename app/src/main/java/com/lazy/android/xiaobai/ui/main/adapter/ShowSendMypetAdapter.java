package com.lazy.android.xiaobai.ui.main.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.lazy.android.R;
import com.lazy.android.baseui.adapter.LZAdapter;

import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenglei on 16/4/25.
 */
public class ShowSendMypetAdapter extends LZAdapter {
	private ShowSendMypetAdapterViewHolder showSendMypetAdapterViewHolder;
	public int clickPosition = -1;
	public int mypetid = 0;

	public ShowSendMypetAdapter(Context context, int layoutId, List array) {
		super(context, layoutId, array);
	}

	@Override
	public Object createViewHolder(View view, Object data) {
		if(view.getTag() == null ){
			showSendMypetAdapterViewHolder = new ShowSendMypetAdapterViewHolder();
			showSendMypetAdapterViewHolder.showsend_adapter_mypets_headimg = (CircleImageView) view.findViewById(R.id.showsend_adapter_mypets_headimg);

		}
		return showSendMypetAdapterViewHolder;
	}

	@Override
	public void bindView(Object data, int position, View view) {
		showSendMypetAdapterViewHolder = (ShowSendMypetAdapterViewHolder) getViewHolder(view,data);
		showSendMypetAdapterViewHolder.showsend_adapter_mypets_headimg.setBorderColor(mContext.getResources().getColor(R.color.white));
		if(position == clickPosition){
			showSendMypetAdapterViewHolder.showsend_adapter_mypets_headimg.setBorderColor(mContext.getResources().getColor(R.color.blue_09aaa5));
		}
		showSendMypetAdapterViewHolder.showsend_adapter_mypets_headimg.setImageBitmap(BitmapFactory.decodeFile(((HashMap)data).get("pet").toString()));

	}


	/**
	 * 点击标记positon
	 */
	public void setPositon(int position){
		clickPosition = position;
		this.notifyDataSetChanged();
	}


	public class ShowSendMypetAdapterViewHolder{
		private CircleImageView showsend_adapter_mypets_headimg;
	}


}
