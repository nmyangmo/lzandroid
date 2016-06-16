package com.lazy.android.xiaobai.ui.register.data;

import android.content.Context;

import com.lazy.android.config.ConfigStaticType;
import com.lazy.android.basefunc.LZUtils.UtilsDownload;
import com.lazy.android.basefunc.LZUtils.UtilsShared;

/**
 * Created by chenglei on 16/3/25.
 */
public class UserinfoSharedUtils {

	public UserinfoSharedUtils() {
	}

	//	添加更新用户信息
	public void SharedUpdate(Context context, UserInfoBean userInfoBean) {
		UtilsShared.setBoolean(context, ConfigStaticType.SettingField.XB_LOGIN_SUCCESS, true);
		if (!userInfoBean.getCityname().equals("")) {
			UtilsShared.setString(context, ConfigStaticType.SettingField.XB_CITYNAME, userInfoBean.getCityname());
		}
		if (!userInfoBean.getHeadimg().equals("")) {
			String url = UtilsDownload.bindHeadImage(userInfoBean.getHeadimg());
			UtilsShared.setString(context, ConfigStaticType.SettingField.XB_HEADIMG, url);
		}
		if (!userInfoBean.getNickname().equals("")) {
			UtilsShared.setString(context, ConfigStaticType.SettingField.XB_NICKNAME, userInfoBean.getNickname());
		}
		if (!userInfoBean.getToken().equals("")) {
			UtilsShared.setString(context, ConfigStaticType.SettingField.XB_TOKEN, userInfoBean.getToken());
		}
		if (!userInfoBean.getUsername().equals("")) {
			UtilsShared.setString(context, ConfigStaticType.SettingField.XB_USERNAME, userInfoBean.getUsername());
		}
		if (userInfoBean.getSex() != -1) {
			UtilsShared.setInt(context, ConfigStaticType.SettingField.XB_SEX, userInfoBean.getSex());
		}
		if (userInfoBean.getUserid() != 0) {
			UtilsShared.setInt(context, ConfigStaticType.SettingField.XB_UID, userInfoBean.getUserid());
		}
		if (!userInfoBean.getIsshare().equals("")) {
			UtilsShared.setString(context, ConfigStaticType.SettingField.XB_ISSHARE, userInfoBean.getIsshare());
		}
		if (!userInfoBean.getMobile().equals("")) {
			UtilsShared.setString(context, ConfigStaticType.SettingField.XB_MOBILE, userInfoBean.getMobile());
		}
	}

	public void LoginOut(Context context) {
		UtilsShared.setBoolean(context, ConfigStaticType.SettingField.XB_LOGIN_SUCCESS, false);
		UtilsShared.setString(context, ConfigStaticType.SettingField.XB_USERNAME, "");
		UtilsShared.setString(context, ConfigStaticType.SettingField.XB_USERNAME, "");
		UtilsShared.setString(context, ConfigStaticType.SettingField.XB_NICKNAME, "");
		UtilsShared.setString(context, ConfigStaticType.SettingField.XB_TOKEN, "");
		UtilsShared.setString(context, ConfigStaticType.SettingField.XB_USERNAME, "");
		UtilsShared.setInt(context, ConfigStaticType.SettingField.XB_SEX, 0);
		UtilsShared.setInt(context, ConfigStaticType.SettingField.XB_UID, 0);
		UtilsShared.setString(context, ConfigStaticType.SettingField.XB_ISSHARE, "");
		UtilsShared.setString(context, ConfigStaticType.SettingField.XB_MOBILE, "");
	}



}
