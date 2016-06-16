package com.lazy.android.xiaobai.ui.register.data;

/**
 * Created by chenglei on 16/5/6.
 */
public class UserInfoThird {
	private String token;
	private String nickname;
	private String faceimg;
	private String thirdtype;

	public UserInfoThird() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFaceimg() {
		return faceimg;
	}

	public void setFaceimg(String faceimg) {
		this.faceimg = faceimg;
	}

	public String getThirdtype() {
		return thirdtype;
	}

	public void setThirdtype(String thirdtype) {
		this.thirdtype = thirdtype;
	}

	@Override
	public String toString() {
		return "UserInfoThird{" +
			"token='" + token + '\'' +
			", nickname='" + nickname + '\'' +
			", faceimg='" + faceimg + '\'' +
			", thirdtype='" + thirdtype + '\'' +
			'}';
	}
}
