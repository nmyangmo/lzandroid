package com.lazy.android.sample.xutils.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lazy.android.basemodel.sqlite.UserBean;
import com.lazy.android.R;
import com.lazy.android.baseui.base.LZBaseFragment;
import org.xutils.common.util.KeyValue;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/1/15.
 */
@ContentView(R.layout.sample_fragment_dbfragment)
public class DbFragment extends LZBaseFragment {

	@ViewInject(R.id.xutils_db_result)
	TextView xutils_db_result;
	private UserBean userBean;


	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		userBean = new UserBean("chenglei", "asdasdasd");

	}

	//	@OnClick(R.id.xutils_db_one_add)
	@Event(R.id.xutils_db_one_add)
	public void xutils_db_one_add_click(View v) {
		userBean.save(mDbManager);
	}

//	@OnClick(R.id.xutils_db_one_delete)
	@Event(R.id.xutils_db_one_delete)
	public void xutils_db_one_delete_click(View v) {
		userBean.delete(mDbManager, 33);
	}

//	@OnClick(R.id.xutils_db_one_select)
	@Event(R.id.xutils_db_one_select)
	public void xutils_db_one_select_click(View v) {
		UserBean result = (UserBean) userBean.select(mDbManager, 2);
	}

//	@OnClick(R.id.xutils_db_one_update)
	@Event(R.id.xutils_db_one_update)
	public void xutils_db_one_update_click(View v) {
		userBean.update(mDbManager, 2, new KeyValue("username","111111111"),new KeyValue("password","23333333333333"));
}


//	@OnClick(R.id.xutils_db_list_add)
	@Event(R.id.xutils_db_list_add)
	public void xutils_db_list_add_click(View v) {

	}

//	@OnClick(R.id.xutils_db_list_delete)
	@Event(R.id.xutils_db_list_delete)
	public void xutils_db_list_delete_click(View v) {

	}

//	@OnClick(R.id.xutils_db_list_select)
	@Event(R.id.xutils_db_list_select)
	public void xutils_db_list_select_click(View v) {

	}

//	@OnClick(R.id.xutils_db_list_update)
	@Event(R.id.xutils_db_list_update)
	public void xutils_db_list_update_click(View v) {

	}


}
