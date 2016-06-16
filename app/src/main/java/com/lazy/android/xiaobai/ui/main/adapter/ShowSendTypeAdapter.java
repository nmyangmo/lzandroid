package com.lazy.android.xiaobai.ui.main.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lazy.android.R;
import com.lazy.android.baseui.adapter.LZAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chenglei on 16/4/26.
 */
public class ShowSendTypeAdapter extends LZAdapter {
	private ShowSendTypeAdapterViewHolder showSendTypeAdapterViewHolder;
	public int clickPostion = -1;
	public int typeid = 0 ;


	public ShowSendTypeAdapter(Context context, int layoutId, List array) {
		super(context, layoutId, array);
	}

	@Override
	public Object createViewHolder(View view, Object data) {
		if(view.getTag() == null){
			showSendTypeAdapterViewHolder = new ShowSendTypeAdapterViewHolder();
			showSendTypeAdapterViewHolder.showsend_adapter_typetext = (TextView) view.findViewById(R.id.showsend_adapter_typetext);
		}
		return showSendTypeAdapterViewHolder;
	}


	@Override
	public void bindView(Object data, int position, View view) {
		showSendTypeAdapterViewHolder = (ShowSendTypeAdapterViewHolder) getViewHolder(view,data);

		showSendTypeAdapterViewHolder.showsend_adapter_typetext.setBackground(mContext.getResources().getDrawable(R.drawable.xb_show_add_type_bg));
		showSendTypeAdapterViewHolder.showsend_adapter_typetext.setTextColor(R.color.textcolor333);
		if(position == clickPostion){
			showSendTypeAdapterViewHolder.showsend_adapter_typetext.setBackground(mContext.getResources().getDrawable(R.drawable.xb_show_add_type_bg_on));
			showSendTypeAdapterViewHolder.showsend_adapter_typetext.setTextColor(R.color.white);
		}
		showSendTypeAdapterViewHolder.showsend_adapter_typetext.setText(((HashMap)mArray.get(position)).get("type").toString());
	}

	public class ShowSendTypeAdapterViewHolder{
		private TextView showsend_adapter_typetext;
	}

	/**
	 * 点击标记positon
	 */
	public void setPositon(int positon){
		clickPostion = positon;
		this.notifyDataSetChanged();
	}

}
