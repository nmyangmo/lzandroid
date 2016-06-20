package com.lazy.android.basedata.model;

import com.lazy.android.LZJavaBean.LZBaseVoBean;
import com.lazy.android.basefunc.LZJson.LZJson;

/**
 * @ClassName: BaseData
 * @Description: 模型基类
 * @author
 * 
 */

public abstract class LZBaseData {
	protected long mCursorId = -1L;

	public long getCursorId() {
		if (mCursorId == -1) {
			return getId();
		}
		return mCursorId;
	}

	public abstract long getId();





}
