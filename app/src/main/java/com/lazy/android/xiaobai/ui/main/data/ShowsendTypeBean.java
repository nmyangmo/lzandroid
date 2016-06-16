package com.lazy.android.xiaobai.ui.main.data;

import com.lazy.android.LZJavaBean.LZBaseVoBean;

/**
 * Created by chenglei on 16/4/19.
 */
public class ShowsendTypeBean extends LZBaseVoBean {

	private int rel_count;
	private int tag_id;
	private String tag_name;
	private int tag_type;

	public ShowsendTypeBean() {
	}

	public ShowsendTypeBean(int rel_count, int tag_id, String tag_name, int tag_type) {
		this.rel_count = rel_count;
		this.tag_id = tag_id;
		this.tag_name = tag_name;
		this.tag_type = tag_type;
	}

	public int getRel_count() {
		return rel_count;
	}

	public void setRel_count(int rel_count) {
		this.rel_count = rel_count;
	}

	public int getTag_id() {
		return tag_id;
	}

	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	public int getTag_type() {
		return tag_type;
	}

	public void setTag_type(int tag_type) {
		this.tag_type = tag_type;
	}


	@Override
	public String toString() {
		return "ShowsendTypeBean{" +
			"rel_count=" + rel_count +
			", tag_id=" + tag_id +
			", tag_name='" + tag_name + '\'' +
			", tag_type=" + tag_type +
			'}';
	}
}
