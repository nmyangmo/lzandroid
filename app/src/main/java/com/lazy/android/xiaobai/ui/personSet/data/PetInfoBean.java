package com.lazy.android.xiaobai.ui.personSet.data;

import com.lazy.android.LZJavaBean.LZBaseVoBean;

/**
 * Created by chenglei on 16/5/8.
 */
public class PetInfoBean extends LZBaseVoBean {

	private int member_id;
	private int mpet_id;
	private long petbirthday;
	private int petcat;
	private String petcatname;
	private long pethometime;
	private String petimgurl;
	private String petname;
	private int petsex;

	public PetInfoBean() {
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getMpet_id() {
		return mpet_id;
	}

	public void setMpet_id(int mpet_id) {
		this.mpet_id = mpet_id;
	}

	public long getPetbirthday() {
		return petbirthday;
	}

	public void setPetbirthday(long petbirthday) {
		this.petbirthday = petbirthday;
	}

	public int getPetcat() {
		return petcat;
	}

	public void setPetcat(int petcat) {
		this.petcat = petcat;
	}

	public String getPetcatname() {
		return petcatname;
	}

	public void setPetcatname(String petcatname) {
		this.petcatname = petcatname;
	}

	public long getPethometime() {
		return pethometime;
	}

	public void setPethometime(long pethometime) {
		this.pethometime = pethometime;
	}

	public String getPetimgurl() {
		return petimgurl;
	}

	public void setPetimgurl(String petimgurl) {
		this.petimgurl = petimgurl;
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public int getPetsex() {
		return petsex;
	}

	public void setPetsex(int petsex) {
		this.petsex = petsex;
	}

	@Override
	public String toString() {
		return "PetInfoBean{" +
			"member_id=" + member_id +
			", mpet_id=" + mpet_id +
			", petbirthday=" + petbirthday +
			", petcat=" + petcat +
			", petcatname='" + petcatname + '\'' +
			", pethometime=" + pethometime +
			", petimgurl='" + petimgurl + '\'' +
			", petname=" + petname +
			", petsex=" + petsex +
			'}';
	}
}
