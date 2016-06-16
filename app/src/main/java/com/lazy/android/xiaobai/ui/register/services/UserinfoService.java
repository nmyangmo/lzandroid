package com.lazy.android.xiaobai.ui.register.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.lazy.android.xiaobai.ui.register.activity.LoginActivity;

/**
 * Created by chenglei on 16/3/25.
 * 用于大数据的写入，多个任务可以放到一个Intentservice中进行，一个执行完执行下一个
 * 不阻塞主线程，执行完任务自动销毁
 *
 */
public class UserinfoService extends IntentService {
	/**
	 * Creates an IntentService.  Invoked by your subclass's constructor.
	 *
	 * @param name Used to name the worker thread, important only for debugging.
	 */

	public UserinfoService() {
		super("com.lazy.android.xiaobai.ui.register.services.UserinfoService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		//根据intent的数值执行具体的业务逻辑

	}


}
