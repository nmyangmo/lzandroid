package com.lazy.android.xiaobai.ui.main.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.lazy.android.R;
import com.lazy.android.baseui.adapter.LZAdapter;
import com.lazy.android.baseui.common.LZImageviewDiyRound;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenglei on 16/4/26.
 */
public class ShowSendImageAdapter extends LZAdapter {
	private ShowSendImageAdapterViewHolder viewHolder;

	public ShowSendImageAdapter(Context context, int layoutId, List array) {
		super(context, layoutId, array);
	}

	@Override
	public Object createViewHolder(View view, Object data) {
		if(view.getTag() == null){
			viewHolder = new ShowSendImageAdapterViewHolder();
			viewHolder.showsend_adapter_image = (LZImageviewDiyRound) view.findViewById(R.id.showsend_adapter_image);
		}
		return viewHolder;
	}

	@Override
	public void bindView(Object data, int position, View view) {
		viewHolder = (ShowSendImageAdapterViewHolder) getViewHolder(view,data);
		HashMap dataitem = (HashMap)data;
		viewHolder.showsend_adapter_image.setImageBitmap(BitmapFactory.decodeFile(dataitem.get("photo").toString()));

	}

	public class ShowSendImageAdapterViewHolder {
		private LZImageviewDiyRound showsend_adapter_image;
	}
}

