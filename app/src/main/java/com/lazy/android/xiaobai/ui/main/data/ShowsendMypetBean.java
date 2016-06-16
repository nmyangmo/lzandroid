package com.lazy.android.xiaobai.ui.main.data;

import com.lazy.android.LZJavaBean.LZBaseVoBean;

/**
 * Created by chenglei on 16/4/25.
 */
public class ShowsendMypetBean extends LZBaseVoBean {

		private int mpet_id;
		private int member_id;
		private String petname;
		private String petimgurl;
		private int petsex;
		private int petcat;
		private long petbirthday;
		private long pethometime;

	public ShowsendMypetBean() {
	}

	public ShowsendMypetBean(int mpet_id, int member_id, String petname, String petimgurl, int petsex, int petcat, long petbirthday, long pethometime) {
		this.mpet_id = mpet_id;
		this.member_id = member_id;
		this.petname = petname;
		this.petimgurl = petimgurl;
		this.petsex = petsex;
		this.petcat = petcat;
		this.petbirthday = petbirthday;
		this.pethometime = pethometime;
	}

	public int getMpet_id() {
		return mpet_id;
	}

	public void setMpet_id(int mpet_id) {
		this.mpet_id = mpet_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public String getPetimgurl() {
		return petimgurl;
	}

	public void setPetimgurl(String petimgurl) {
		this.petimgurl = petimgurl;
	}

	public int getPetsex() {
		return petsex;
	}

	public void setPetsex(int petsex) {
		this.petsex = petsex;
	}

	public int getPetcat() {
		return petcat;
	}

	public void setPetcat(int petcat) {
		this.petcat = petcat;
	}

	public long getPetbirthday() {
		return petbirthday;
	}

	public void setPetbirthday(long petbirthday) {
		this.petbirthday = petbirthday;
	}

	public long getPethometime() {
		return pethometime;
	}

	public void setPethometime(long pethometime) {
		this.pethometime = pethometime;
	}

	@Override
	public String toString() {
		return "ShowsendMypetBean{" +
			"mpet_id=" + mpet_id +
			", member_id=" + member_id +
			", petname='" + petname + '\'' +
			", petimgurl='" + petimgurl + '\'' +
			", petsex=" + petsex +
			", petcat=" + petcat +
			", petbirthday=" + petbirthday +
			", pethometime=" + pethometime +
			'}';
	}
}
