package com.lazy.android.sample.divview.netlist;

import com.lazy.android.basedata.model.LZBaseData;

/**
 * Created by chenglei on 16/6/16.
 */
public class datamodel extends LZBaseData {
	private long id;
	private String nickname;
	private String imageurl;

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Override
	public long getId() {
		return 0;
	}
}
