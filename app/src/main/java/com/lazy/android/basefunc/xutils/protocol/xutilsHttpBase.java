//package com.baicaijia.baicaijia.base.xutils.protocol;
//
//import android.content.Context;
//import android.text.TextUtils;
//
//import com.baicaijia.baicaijia.app.BaicaijiaApplication;
//import com.baicaijia.baicaijia.base.config.CSConsts.CSHttpHeader;
//
//import com.baicaijia.baicaijia.base.error.ErrorManager;
//import com.baicaijia.baicaijia.base.mobile.SystemInfo;
//import com.baicaijia.baicaijia.base.network.INetwork;
//import com.baicaijia.baicaijia.base.network.ProxySettings;
//import com.baicaijia.baicaijia.base.storage.FileHelper;
//import com.baicaijia.baicaijia.base.utils.StringHelper;
//import com.baicaijia.baicaijia.protocol.IHttpRequestCallback;
//import com.baicaijia.baicaijia.protocol.RequestInfo;
//import com.lidroid.xutils.HttpUtils;
//import com.lidroid.xutils.exception.HttpException;
//import com.lidroid.xutils.http.HttpHandler;
//import com.lidroid.xutils.http.RequestParams;
//import com.lidroid.xutils.http.RequestParams.HeaderItem;
//import com.lidroid.xutils.http.ResponseInfo;
//import com.lidroid.xutils.http.callback.RequestCallBack;
//import com.lidroid.xutils.http.client.HttpRequest;
//import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
//import com.lidroid.xutils.http.client.multipart.MultipartEntity;
//import com.lidroid.xutils.http.client.multipart.content.FileBody;
//import com.lidroid.xutils.http.client.multipart.content.StringBody;
//
//import org.apache.http.Header;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpEntityEnclosingRequest;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.HttpVersion;
//import org.apache.http.NoHttpResponseException;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.HttpRequestRetryHandler;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.apache.http.conn.ClientConnectionManager;
//import org.apache.http.conn.params.ConnRoutePNames;
//import org.apache.http.conn.scheme.PlainSocketFactory;
//import org.apache.http.conn.scheme.Scheme;
//import org.apache.http.conn.scheme.SchemeRegistry;
//import org.apache.http.conn.ssl.SSLSocketFactory;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.CoreConnectionPNames;
//import org.apache.http.params.CoreProtocolPNames;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.params.HttpProtocolParams;
//import org.apache.http.protocol.ExecutionContext;
//import org.apache.http.protocol.HttpContext;
//import org.apache.http.util.EntityUtils;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.Charset;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.net.ssl.SSLHandshakeException;
//
///**
// * @ClassName: HttpNetwork
// * @Description: http协议实现类
// * @author
// *
// */
//public class xutilsHttpBase implements INetwork {
//	private final static String TAG = xutilsHttpBase.class.getSimpleName();
//	/**
//	 * 缓冲区大小
//	 */
//	private final static int BUFFERSIZE = 1024;
//	/**
//	 * 网络超时时间。毫秒
//	 */
//	private final static int TIMEOUT_CONNECTION = 90 * 1000;
//	protected static final int TRY_COUNT = 3;
//	private static xutilsHttpBase instance = null;
//	private Context mContext;
//
//	private  RequestParams mHeaderParams;
//
//	static {
////		mHeaderParams = new RequestParams();
////		mHeaderParams.addHeader("User-Agent", CSHttpHeader.UA_VALUE);
////		mHeaderParams.addHeader("Accept-Language", "zh-cn,zh;q=0.5");
////		mHeaderParams.addHeader("Accept-Charset", "GBK,GB2312,utf-8;q=0.7,*;q=0.7");
////		mHeaderParams.addHeader("Accept", "*/*");
////		mHeaderParams.addHeader(CSHttpHeader.MOBILE_OS, LZConfig_System.PLATFORM);
////		mHeaderParams.addHeader(CSHttpHeader.MOBILE_OS_VERSION,SystemInfo.getSDKVersion());
////		mHeaderParams.addHeader(CSHttpHeader.APP_VERSION, BaicaijiaApplication.APP_VERSION);
//    }
//
//	public xutilsHttpBase(Context context) {
//		mContext = context;
//	}
//
//	public static xutilsHttpBase getInstance(Context context) {
//		if (instance == null) {
//			instance = new xutilsHttpBase(context);
//		}
//		return instance;
//	}
//
//	/*
//	 * (非 Javadoc) <p>Title: sendRequest</p> <p>Description: </p>
//	 *
//	 * @param info
//	 */
//	@Override
//	public void sendRequest(RequestInfo info) {
//		if (!NetUtils.isNetworkAvailable(mContext)) {
//			info.setHttpResultCode(ErrorManager.NOT_NETWORK);
//			if (info.getRequestCallback() != null) {
//				info.getRequestCallback().onHttpFailure(new Exception(), ErrorManager.getErrorDescByCode(ErrorManager.NOT_NETWORK));
//			}
//			return;
//		}
//		RequestInfo.RequestType requestType = info.getRequestType();
//        mHeaderParams = new RequestParams();
//        mHeaderParams.addHeader("User-Agent", CSHttpHeader.UA_VALUE);
//        mHeaderParams.addHeader("Accept-Language", "zh-cn,zh;q=0.5");
//        mHeaderParams.addHeader("Accept-Charset", "GBK,GB2312,utf-8;q=0.7,*;q=0.7");
//        mHeaderParams.addHeader("Accept", "*/*");
//        mHeaderParams.addHeader(CSHttpHeader.MOBILE_OS, LZConfig_System.PLATFORM);
//        mHeaderParams.addHeader(CSHttpHeader.MOBILE_OS_VERSION,SystemInfo.getSDKVersion());
//        mHeaderParams.addHeader(CSHttpHeader.APP_VERSION, BaicaijiaApplication.APP_VERSION);
//        mHeaderParams.addHeader(CSHttpHeader.ACCESS_TOKEN,LZConfig_System.getAccessToken());
//        mHeaderParams.addHeader(CSHttpHeader.USER_ID,LZConfig_System.getUserId());
//        mHeaderParams.addHeader(CSHttpHeader.REGIONID,LZConfig_System.getRegionId());
//
//		info.setHttpResultCode(ErrorManager.NO_ERROR);
//		switch (requestType) {
//		case UPLOAD:
//			uploadFile(info);
//			break;
//		case DOWNLOAD:
//			doDownloadFile(info);
//			break;
//		case GET:
//			sendGetRequest(info);
//			break;
//		case POST:
//			sendPostRequest(info);
//			break;
//		case DELETE:
//			sendDeleteRequest(info);
//			break;
//		case PUT:
//			sendPutRequest(info);
//			break;
//		default:
//			sendGetRequest(info);
//			break;
//		}
//	}
//
//	/**
//	 * @Title: uploadFile
//	 * @Description: 带文件附件的请求
//	 * @param info
//	 * @return void
//	 */
//	private void uploadFile(final RequestInfo info) {
//		// 上传文件
//		if (info.getFormFieldParams() == null
//				&& info.getUploadFilePath() == null) {
//			info.setHttpResultCode(ErrorManager.DATAFORMAT_ERROR);
//			if (info.getRequestCallback() != null) {
//				info.getRequestCallback().onHttpFailure(new Exception(), ErrorManager.getErrorDescByCode(ErrorManager.DATAFORMAT_ERROR));
//			}
//			return;
//		}
//		RequestParams fileParams = new RequestParams();
//		List<HeaderItem> headers = mHeaderParams.getHeaders();
//		for (HeaderItem item : headers) {
//			fileParams.addHeader(item.header);
//		}
//		MultipartEntity bodyEntity = new MultipartEntity();
//		// 添加文本部分
//		if (info.getFormFieldParams() != null
//				&& !info.getFormFieldParams().isEmpty()) {
//			Set<Map.Entry<String, String>> entrySet = info.getFormFieldParams().entrySet();
//			for (Map.Entry<String, String> entry : entrySet) {
//				try {
//					StringBody jsonBody = new StringBody(entry.getKey(), entry.getValue(), Charset.forName(info.getCharset()));
//					bodyEntity.addPart(entry.getKey(), jsonBody);
//				} catch (UnsupportedEncodingException e) {
//					Logger.getInstance(TAG).debug(e.getMessage(), e);
//					info.setHttpResultCode(ErrorManager.DATAFORMAT_ERROR);
//					if (info.getRequestCallback() != null) {
//						info.getRequestCallback().onHttpFailure(new Exception(), ErrorManager.getErrorDescByCode(ErrorManager.DATAFORMAT_ERROR));
//					}
//					return;
//				}
//			}
//		}
//		// 添加文件部分
//		if (info.getUploadFilePath() != null
//				&& !info.getUploadFilePath().isEmpty()) {
//			for (String filePath : info.getUploadFilePath()) {
//				String mime = FileHelper.getMIME(filePath);
//				FileBody fileBody = new FileBody(new File(filePath), mime, RequestInfo.DEFAULT_CHARSET);
//				String fileField = StringHelper.md5Hash(filePath);
//				bodyEntity.addPart(fileField, fileBody);
//			}
//		}
//		fileParams.setBodyEntity(bodyEntity);
//		HttpUtils http = new HttpUtils();
//		final IHttpRequestCallback callback = info.getRequestCallback();
//		http.send(HttpMethod.POST, info.getUrl(), fileParams,
//				new RequestCallBack<String>() {
//					@Override
//					public void onLoading(long total, long current, boolean isUploading) {
//						if (callback != null) {
//							callback.onHttpProgress(total, current, isUploading);
//						}
//					}
//
//					@Override
//					public void onSuccess(ResponseInfo<String> responseInfo) {
//						info.setRecieveData(responseInfo.result);
//						if (callback != null) {
//							if (responseInfo.contentType != null) {
//								info.setResponseContentType(responseInfo.contentType.getValue().trim());
//							}
//							callback.onHttpSuccess(info);
//						}
//					}
//
//					@Override
//					public void onStart() {
//						if (callback != null) {
//							callback.onHttpStart();
//						}
//					}
//
//					@Override
//					public void onFailure(HttpException error, String msg) {
//						info.setHttpResultCode(ErrorManager.NETWORK_FAILURE);
//						if (callback != null) {
//							callback.onHttpFailure(error, msg);
//						}
//					}
//				});
//	}
//
//	/**
//	 * @Title: sendPostRequest
//	 * @Description: HTTP POST方式向服务器发送数据
//	 * @param info
//	 * @return void
//	 */
//	private void sendPostRequest(final RequestInfo info) {
//		doPostPutRequest(info, HttpMethod.POST);
//	}
//
//	/**
//	 * @Title: sendPutRequest
//	 * @Description: http put 操作
//	 * @param info
//	 * @return void
//	 */
//	private void sendPutRequest(RequestInfo info) {
//		doPostPutRequest(info, HttpMethod.PUT);
//	}
//
//	/**
//	 * @Title: sendGetRequest
//	 * @Description: HTTP GET方式向服务器请求数据
//	 * @param info
//	 * @return void
//	 */
//	private void sendGetRequest(RequestInfo info) {
//		doGetRequest(info, false);
//	}
//	/**
//	 * @Title: downloadFile
//	 * @Description: 下载文件
//	 * @param info
//	 * @return void
//	 */
//	private void doDownloadFile(RequestInfo info) {
//		doGetRequest(info, true);
//	}
//	/**
//	 * @Title: sendDeleteRequest
//	 * @Description: http delete 操作
//	 * @param info
//	 * @return void
//	 */
//	private void sendDeleteRequest(final RequestInfo info) {
//		RequestParams fileParams = new RequestParams();
//		List<HeaderItem> headers = mHeaderParams.getHeaders();
//		for (HeaderItem item : headers) {
//			fileParams.addHeader(item.header);
//		}
//		RequestParams jsonParams = new RequestParams();
//		for (HeaderItem item : headers) {
//			jsonParams.addHeader(item.header);
//		}
//		jsonParams.setHeader("Content-Type", RequestInfo.DEFAULT_CONTENTTYPE
//				+ ";charset=UTF-8");
//		jsonParams.setHeader("Accept", RequestInfo.DEFAULT_CONTENTTYPE);
//		final IHttpRequestCallback callback = info.getRequestCallback();
//		HttpUtils http = new HttpUtils();
//		http.send(HttpMethod.DELETE, info.getUrl(), jsonParams,
//				new RequestCallBack<String>() {
//					@Override
//					public void onLoading(long total, long current, boolean isUploading) {
//						if (callback != null) {
//							callback.onHttpProgress(total, current, isUploading);
//						}
//					}
//
//					@Override
//					public void onSuccess(ResponseInfo<String> responseInfo) {
//						info.setRecieveData(responseInfo.result);
//						if (callback != null) {
//							if (responseInfo.contentType != null) {
//								info.setResponseContentType(responseInfo.contentType.getValue().trim());
//							}
//							callback.onHttpSuccess(info);
//						}
//					}
//
//					@Override
//					public void onStart() {
//						if (callback != null) {
//							callback.onHttpStart();
//						}
//					}
//
//					@Override
//					public void onFailure(HttpException except, String msg) {
//						info.setHttpResultCode(ErrorManager.NETWORK_FAILURE);
//						if (callback != null) {
//							callback.onHttpFailure(except, msg);
//						}
//					}
//				});
//	}
//
//	private void doPostPutRequest(final RequestInfo info, HttpMethod method) {
//		List<HeaderItem> headers = mHeaderParams.getHeaders();
//		RequestParams jsonParams = new RequestParams();
//		for (HeaderItem item : headers) {
//            jsonParams.addHeader(item.header);
//		}
//		final IHttpRequestCallback callback = info.getRequestCallback();
//		HttpUtils http = new HttpUtils();
//        //设置当前请求的缓存时间
//        http.configCurrentHttpCacheExpiry(0);
//        //设置默认请求的缓存时间
//        http.configDefaultHttpCacheExpiry(0);
//
//		// 带文件附件
//		if (info.getUploadFilePath() != null
//				&& !info.getUploadFilePath().isEmpty()) {
//			MultipartEntity bodyEntity = new MultipartEntity();
//			// 添加文本部分
//			if (info.getFormFieldParams() != null
//					&& !info.getFormFieldParams().isEmpty()) {
//				Set<Map.Entry<String, String>> entrySet = info.getFormFieldParams().entrySet();
//				for (Map.Entry<String, String> entry : entrySet) {
//					try {
//						StringBody jsonBody = new StringBody(entry.getKey(), entry.getValue(), Charset.forName(info.getCharset()));
//						bodyEntity.addPart(entry.getKey(), jsonBody);
//					} catch (UnsupportedEncodingException e) {
//						Logger.getInstance(TAG).debug(e.getMessage(), e);
//						info.setHttpResultCode(ErrorManager.DATAFORMAT_ERROR);
//						if (info.getRequestCallback() != null) {
//							info.getRequestCallback().onHttpFailure(new Exception(), ErrorManager.getErrorDescByCode(ErrorManager.DATAFORMAT_ERROR));
//						}
//						return;
//					}
//				}
//			}
//			// 添加文件
//			for (String filePath : info.getUploadFilePath()) {
//                String mime = FileHelper.getMIME(filePath);
//				FileBody fileBody = new FileBody(new File(filePath), mime, RequestInfo.DEFAULT_CHARSET);
//                String fileField = StringHelper.md5Hash(filePath);
////                bodyEntity.addPart(fileField, fileBody);    //原来的代码
//                bodyEntity.addPart("file", fileBody);
//			}
//			jsonParams.setBodyEntity(bodyEntity);
//		} else {
//			// 不带文件附件
//			jsonParams.setHeader("Content-Type", RequestInfo.DEFAULT_CONTENTTYPE);
//
//			try {
//				if(!TextUtils.isEmpty(info.getBody())){
//					StringEntity entity = new StringEntity(info.getBody(), info.getCharset());
//					entity.setContentType("application/json");
//					jsonParams.setBodyEntity(entity);
//				}
//			} catch (UnsupportedEncodingException e) {
//				Logger.getInstance(TAG).debug(e.getMessage(), e);
//				info.setHttpResultCode(ErrorManager.DATAFORMAT_ERROR);
//				if (info.getRequestCallback() != null) {
//					info.getRequestCallback().onHttpFailure(new Exception(), ErrorManager.getErrorDescByCode(ErrorManager.DATAFORMAT_ERROR));
//				}
//				return;
//			}
//		}
//
//		http.send(method, info.getUrl(), jsonParams,
//				new RequestCallBack<String>() {
//					@Override
//					public void onLoading(long total, long current, boolean isUploading) {
//						if (callback != null) {
//							callback.onHttpProgress(total, current, isUploading);
//						}
//					}
//
//					@Override
//					public void onSuccess(ResponseInfo<String> responseInfo) {
//						info.setRecieveData(responseInfo.result);
//						if (callback != null) {
//							if (responseInfo.contentType != null) {
//								info.setResponseContentType(responseInfo.contentType.getValue().trim());
//							}
//							callback.onHttpSuccess(info);
//						}
//					}
//
//					@Override
//					public void onStart() {
//						if (callback != null) {
//							callback.onHttpStart();
//						}
//					}
//
//					@Override
//					public void onFailure(HttpException error, String msg) {
//						info.setHttpResultCode(ErrorManager.NETWORK_FAILURE);
//						if (callback != null) {
//							callback.onHttpFailure(error, msg);
//						}
//					}
//				});
//	}
//
//	private void doGetRequest(final RequestInfo info, boolean isDownFile) {
//		final IHttpRequestCallback callback = info.getRequestCallback();
//		HttpUtils http = new HttpUtils();
//        //设置当前请求的缓存时间
//        http.configCurrentHttpCacheExpiry(0);
//        //设置默认请求的缓存时间
//        http.configDefaultHttpCacheExpiry(0);
//        if (isDownFile) {
//			HttpHandler<File> handler = http.download(info.getUrl(), info.getSavedFilePath(), mHeaderParams, true, true,
//					new RequestCallBack<File>() {
//						@Override
//						public void onLoading(long total, long current, boolean isUploading) {
//							if (callback != null) {
//								callback.onHttpProgress(total, current, isUploading);
//							}
//						}
//
//						@Override
//						public void onSuccess(ResponseInfo<File> responseInfo) {
//							info.setSavedFilePath(responseInfo.result.getPath());
//							if (responseInfo.contentType != null) {
//								info.setResponseContentType(responseInfo.contentType.getValue().trim());
//							}
//							callback.onHttpSuccess(info);
//						}
//
//						@Override
//						public void onStart() {
//							callback.onHttpStart();
//						}
//
//						@Override
//						public void onFailure(HttpException except, String msg) {
//							info.setHttpResultCode(ErrorManager.NETWORK_FAILURE);
//							if (callback != null) {
//								callback.onHttpFailure(except, msg);
//							}
//						}
//					});
//		} else {
//			RequestParams jsonParams = new RequestParams();
//			List<HeaderItem> headers = mHeaderParams.getHeaders();
//			for (HeaderItem item : headers) {
//                jsonParams.addHeader(item.header);
//			}
//			jsonParams.setHeader("Content-Type", RequestInfo.DEFAULT_CONTENTTYPE + ";charset=UTF-8");
//
//			http.send(HttpMethod.GET, info.getUrl(), jsonParams,
//					new RequestCallBack<String>() {
//						@Override
//						public void onLoading(long total, long current, boolean isUploading) {
//							if (callback != null) {
//								callback.onHttpProgress(total, current, isUploading);
//							}
//						}
//
//						@Override
//						public void onSuccess(ResponseInfo<String> responseInfo) {
//							info.setRecieveData(responseInfo.result);
//							if (callback != null) {
//								if (responseInfo.contentType != null) {
//									info.setResponseContentType(responseInfo.contentType.getValue().trim());
//								}
//								callback.onHttpSuccess(info);
//							}
//						}
//
//						@Override
//						public void onStart() {
//							if (callback != null) {
//								callback.onHttpStart();
//							}
//						}
//
//						@Override
//						public void onFailure(HttpException except, String msg) {
//							info.setHttpResultCode(ErrorManager.NETWORK_FAILURE);
//							if (callback != null) {
//								callback.onHttpFailure(except, msg);
//							}
//						}
//					});
//		}
//	}
//	private final static String CONTENT_TYPE = "CSContent-Type";
//	@Override
//	public byte[] downloadFile(RequestInfo info) {
//		DefaultHttpClient httpClient = getHttpClient();
//		if (httpClient == null) {
//			info.setHttpResultCode(ErrorManager.NETWORK_FAILURE);
//			if (info.getRequestCallback() != null) {
//				info.getRequestCallback().onHttpFailure(new Exception(), ErrorManager.getErrorDescByCode(ErrorManager.NETWORK_FAILURE));
//			}
//			return null;
//		} else {
//			httpClient.setHttpRequestRetryHandler(requestRetryHandler);
//			httpClient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,
//					info.getCharset() == null ? RequestInfo.DEFAULT_CHARSET : info.getCharset());
//		}
//		HttpGet httpGet = null;
//		try {
//			httpGet = new HttpGet(info.getUrl());
//		} catch (IllegalArgumentException e) {
//			info.setHttpResultCode(ErrorManager.NETWORK_FAILURE);
//			if (info.getRequestCallback() != null) {
//				info.getRequestCallback().onHttpFailure(new Exception(), ErrorManager.getErrorDescByCode(ErrorManager.NETWORK_FAILURE));
//			}
//			Logger.getInstance(TAG).debug(info.getUrl(), e);
//			return null;
//		}
//        mHeaderParams = new RequestParams();
//        mHeaderParams.addHeader("User-Agent", CSHttpHeader.UA_VALUE);
//        mHeaderParams.addHeader("Accept-Language", "zh-cn,zh;q=0.5");
//        mHeaderParams.addHeader("Accept-Charset", "GBK,GB2312,utf-8;q=0.7,*;q=0.7");
//        mHeaderParams.addHeader("Accept", "*/*");
//        mHeaderParams.addHeader(CSHttpHeader.MOBILE_OS, LZConfig_System.PLATFORM);
//        mHeaderParams.addHeader(CSHttpHeader.MOBILE_OS_VERSION,SystemInfo.getSDKVersion());
//        mHeaderParams.addHeader(CSHttpHeader.APP_VERSION, BaicaijiaApplication.APP_VERSION);
//        mHeaderParams.addHeader(CSHttpHeader.USER_ID,LZConfig_System.getUserId());
//        mHeaderParams.addHeader(CSHttpHeader.REGIONID,LZConfig_System.getRegionId());
//		List<HeaderItem> headerItems = mHeaderParams.getHeaders();
//		for (HeaderItem item : headerItems) {
//            httpGet.addHeader(item.header.getName(), item.header.getValue());
//		}
//		byte[] buffer = null;
//		try {
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			int responseCode = httpResponse.getStatusLine().getStatusCode();
//			if (responseCode == HttpStatus.SC_OK) {
//				HttpEntity httpEntity = httpResponse.getEntity();
//				buffer = EntityUtils.toByteArray(httpEntity);
//				Header[] headers = httpResponse.getAllHeaders();
//				if (headers != null && headers.length > 0) {
//					for (Header h : headers) {
//						if (CONTENT_TYPE.equalsIgnoreCase(h.getName())) {
//							info.setResponseContentType(h.getValue().trim());
//							break;
//						}
//					}
//				}
//				info.setHttpResultCode(ErrorManager.NO_ERROR);
//			} else {
//				Logger.getInstance(TAG).debug("resp url<" + info.getUrl() + "> http status:<" + responseCode + "> data<" + info.getBody() + ">");
//				info.setHttpResultCode(ErrorManager.NETWORK_FAILURE);
//				HttpEntity httpEntity = httpResponse.getEntity();
//				String str = EntityUtils.toString(httpEntity, info.getCharset());
//				info.setRecieveData(str);
//				if (info.getRequestCallback() != null) {
//					info.getRequestCallback().onHttpFailure(new Exception(), ErrorManager.getErrorDescByCode(ErrorManager.NETWORK_FAILURE));
//				}
//			}
//		} catch (ClientProtocolException e) {
//			info.setHttpResultCode(ErrorManager.PROTOCOL_ERROR);
//			if (info.getRequestCallback() != null) {
//				info.getRequestCallback().onHttpFailure(e, ErrorManager.getErrorDescByCode(ErrorManager.PROTOCOL_ERROR));
//			}
//			Logger.getInstance(TAG).debug(e.getMessage(), e);
//		} catch (FileNotFoundException e) {
//			info.setHttpResultCode(ErrorManager.FILENOTEXISTS_ERROR);
//			if (info.getRequestCallback() != null) {
//				info.getRequestCallback().onHttpFailure(e, ErrorManager.getErrorDescByCode(ErrorManager.FILENOTEXISTS_ERROR));
//			}
//			Logger.getInstance(TAG).debug(e.getMessage(), e);
//		} catch (IOException e) {
//			info.setHttpResultCode(ErrorManager.IO_ERROR);
//			if (info.getRequestCallback() != null) {
//				info.getRequestCallback().onHttpFailure(e, ErrorManager.getErrorDescByCode(ErrorManager.IO_ERROR));
//			}
//			Logger.getInstance(TAG).debug(e.getMessage(), e);
//		} catch (Exception e) {
//			info.setHttpResultCode(ErrorManager.NETWORK_FAILURE);
//			if (info.getRequestCallback() != null) {
//				info.getRequestCallback().onHttpFailure(e, ErrorManager.getErrorDescByCode(ErrorManager.NETWORK_FAILURE));
//			}
//			Logger.getInstance(TAG).debug(e.getMessage(), e);
//		} finally {
//			abortRequest(httpGet);
//			shutdown(httpClient);
//		}
//		return buffer;
//	}
//
//	/**
//	 * 异常自动恢复处理 使用HttpRequestRetryHandler接口实现请求的异常恢复
//	 */
//	private HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
//		// 自定义的恢复策略
//		@Override
//		public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
//			// 设置恢复策略，在发生异常时候将自动重试
//			if (executionCount > TRY_COUNT) {
//				// 超过最大次数则不需要重试
//				return false;
//			}
//			if (exception instanceof NoHttpResponseException) {
//				// 服务停掉则重新尝试连接
//				return true;
//			}
//			if (exception instanceof SSLHandshakeException) {
//				// SSL异常不需要重试
//				return false;
//			}
//			HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
//			boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
//			if (!idempotent) {
//				// 请求内容相同则重试
//				return true;
//			}
//			return false;
//		}
//	};
//
//	private DefaultHttpClient getHttpClient() {
//		HttpParams httpParams = new BasicHttpParams();
//		// 代理设置
//		HttpHost proxyHost = ProxySettings.getProxyHost();
//		if (proxyHost != null) {
//			httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHost);
//		}
//		// 设置http头信息
//		httpParams.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//		httpParams.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT_CONNECTION);
//		httpParams.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, TIMEOUT_CONNECTION * 2);
//
//		HttpProtocolParams.setUseExpectContinue(httpParams, true);
//		HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_CONNECTION);
//		HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_CONNECTION);
//
//		// 设置我们的HttpClient支持HTTP和HTTPS两种模式
//		SchemeRegistry schReg = new SchemeRegistry();
//		schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
//		// ssl
//		SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
//	    sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//		schReg.register(new Scheme("https", sslSocketFactory, 443));
//		// 使用线程安全的连接管理来创建HttpClient
//		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(httpParams, schReg);
//		return new DefaultHttpClient(conMgr, httpParams);
//	}
//
//	//
//	private void abortRequest(final HttpRequestBase request) {
//		if (request != null) {
//			request.abort();
//		}
//	}
//
//	/**
//	* @Title: shutdown
//	* @Description: 关闭http连接
//	* @param httpclient
//	* @return void
//	*/
//	private void shutdown(final HttpClient httpclient) {
//		if (httpclient != null) {
//			httpclient.getConnectionManager().shutdown();
//		}
//	}
//}
