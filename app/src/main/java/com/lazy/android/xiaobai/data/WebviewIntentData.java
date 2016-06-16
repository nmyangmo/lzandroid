package com.lazy.android.xiaobai.data;

import java.io.Serializable;

/**
 * Created by chenglei on 16/3/17.
 */
public class WebviewIntentData implements Serializable {

	private String title;
	private String url;
	private String refresh = "false";

	public WebviewIntentData() {
	}

	public WebviewIntentData(String title, String url) {
		this.title = title;
		this.url = url;
	}

	public WebviewIntentData(String title, String url, String refresh) {
		this.title = title;
		this.url = url;
		this.refresh = refresh;
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url =  url;
	}

	public String getRefresh() {
		return refresh;
	}

	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}
}
