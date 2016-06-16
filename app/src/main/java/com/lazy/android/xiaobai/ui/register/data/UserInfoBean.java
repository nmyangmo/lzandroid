package com.lazy.android.xiaobai.ui.register.data;

import android.content.Context;

import com.lazy.android.LZJavaBean.LZBaseVoBean;

/**
 * Created by chenglei on 16/3/24.
 */
public class UserInfoBean extends LZBaseVoBean {

	private String cityname;
	private String headimg;
	private String nickname;
	private int sex;
	private String token;
	private int userid;
	private String username;
	private String isshare;
	private String mobile;

	public UserInfoBean() {
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIsshare() {
		return isshare;
	}

	public void setIsshare(String isshare) {
		this.isshare = isshare;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "UserInfoBean{" +
			"cityname='" + cityname + '\'' +
			", headimg='" + headimg + '\'' +
			", nickname='" + nickname + '\'' +
			", sex=" + sex +
			", token='" + token + '\'' +
			", userid=" + userid +
			", username='" + username + '\'' +
			", isshare='" + isshare + '\'' +
			", mobile='" + mobile + '\'' +
			'}';
	}
}
