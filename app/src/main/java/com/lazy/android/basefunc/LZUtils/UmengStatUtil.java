//package com.baicaijia.baicaijia.base.utils;
//
//import android.content.Context;
//
//
//import com.baicaijia.baicaijia.LZConfig.LZConfig_System;
//import com.umeng.analytics.MobclickAgent;
//
//public class UmengStatUtil {
//
//	public static void onResume(Context context) {
//		if (LZConfig_System.STAT_ENABLE) {
//			MobclickAgent.onPageStart(context.getClass().getName());
//			MobclickAgent.onResume(context);
//		}
//	}
//
//	public static void onPause(Context context) {
//		if (LZConfig_System.STAT_ENABLE) {
//			MobclickAgent.onPageEnd(context.getClass().getName());
//			MobclickAgent.onPause(context);
//		}
//	}
//
//	public static void onEvent(Context context, String eventId) {
//		if (LZConfig_System.STAT_ENABLE) {
//			MobclickAgent.onEvent(context, eventId);
//		}
//	}
//
//	public static void exit(Context context) {
//		if (LZConfig_System.STAT_ENABLE) {
//			MobclickAgent.onKillProcess(context);
//		}
//	}
//
//	public static void setDebugMode(boolean debugMode) {
//		if (LZConfig_System.STAT_ENABLE) {
//			MobclickAgent.setDebugMode(debugMode);
//		}
//	}
//
//}
