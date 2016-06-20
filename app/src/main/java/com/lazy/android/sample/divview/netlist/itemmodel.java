package com.lazy.android.sample.divview.netlist;

import com.google.gson.Gson;
import com.lazy.android.basedata.model.LZBaseData;
import com.lazy.android.basefunc.LZLogger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by chenglei on 16/6/17.
 */
public class itemmodel extends LZBaseData {

	private long id;
	private String nickname;
	private String headimg;

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}



	public static itemmodel parse(JSONObject jsonObject) {
		itemmodel itemmodel = new Gson().fromJson(jsonObject.toString(), itemmodel.class);
		return itemmodel;
	}

}
