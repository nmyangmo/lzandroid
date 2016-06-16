package com.lazy.android.basefunc.LZUtils;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.lazy.android.config.ConfigFilePath;
import com.lazy.android.basefunc.LZDownLoad.DownloadService;

import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by chenglei on 16/4/13.
 */
public class UtilsDownload  {

//	绑定圆形的图片
	public static String bindHeadImage(String url){
//		1、拆分出url后缀名
		String[] urlarr = url.split("\\/");
		String name = urlarr[urlarr.length-1];
//		1、根据随机数生成文件名
//		String name = UUID.randomUUID()+"";
//		2、组成新的路径
		String local_url = ConfigFilePath.FILE_IMAGE + "/" + name;
//		3、检查新路径有没有文件存在，存在删除，不存在下载
//		File file = new File(local_url);
//		if(file.exists()){
////			file.delete();
//		}
//		4、下载完成返回新路径
		try {
				DownloadService.getDownloadManager().startDownload(
					url, name,
					local_url, true, false, null);
		} catch (DbException e) {
			e.printStackTrace();
		}

		return local_url;
	}


//	基于xutils的下载
	public static void xutilDownload(String url , String path, Callback.ProgressCallback<File> callback){
		RequestParams requestParams = new RequestParams(url);
		requestParams.setSaveFilePath(path);
		x.http().get(requestParams,callback);
	}

}
