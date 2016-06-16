package com.lazy.android.xiaobai.ui.personSet.data;

import com.lazy.android.LZJavaBean.LZBaseVoBean;

/**
 * Created by chenglei on 16/4/28.
 */
public class PetTypeBean extends LZBaseVoBean {

	private int typeid;
	private String typename;


	public PetTypeBean() {
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Override
	public String toString() {
		return "PetTypeBean{" +
			"typeid=" + typeid +
			", typename='" + typename + '\'' +
			'}';
	}
}
