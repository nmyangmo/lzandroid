package com.lazy.android.sample.divview.netlist;

import android.content.Context;

import com.lazy.android.basedata.model.LZRefreshListData;
import com.lazy.android.basefunc.LZLogger.Logger;
import com.lazy.android.baseprotocol.LZHttpProtocolListHandlerBase;
import com.lazy.android.baseprotocol.LZHttpIProtocolCallback;
import com.lazy.android.baseprotocol.LZHttpRequestInfo;
import com.lazy.android.config.ConfigProtocolType;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/5/14.
 */

/**
 * 技师评论列表的协议层
 */
public class CommentsProtocolListHandler extends LZHttpProtocolListHandlerBase<datamodel> {
    private static final String TAG = "CommentsListProtocolHandler";
    private Logger logger;
    public CommentsProtocolListHandler(Context context, boolean refreshed, LZRefreshListData<datamodel> data, LZHttpIProtocolCallback callBack, int page) {
        super(context, refreshed, data, callBack);
        mSubUrl = mListUrl + "?page=" + page;

        //测试数据
        mProtocolType = ConfigProtocolType.SAMPLE_LIST;
        //组装请求地址，组装数据域，发送数据
        mRequestInfo = new LZHttpRequestInfo(mSubUrl, LZHttpRequestInfo.RequestType.GET);
        this.sendRequest();
    }

    @Override
    public boolean parse() {
//        String json = mRequestInfo.getRecieveData();
//        mReceiveDataList = parseOrder(json);
        return true;
    }
    public ArrayList<datamodel> parseOrder(String json){


        ArrayList<datamodel> adList = new ArrayList<datamodel>();
//        try {
//            if (mProtocolStatus == CSConsts.ProtocolStatus.RESULT_SUCCESS) {
//                if (!mResponeVO.isNull("data")) {
//                    JSONArray objArray = mResponeVO.getJSONArray("data");
//                    if (objArray != null && objArray.length() > 0) {
//                        JSONObject item;
//                        for (int index = 0, count = objArray.length(); index < count; ++index) {
//                            item = objArray.getJSONObject(index);
//                            CommentList ad = CommentList.parse(item);
//                            if (ad != null) {
//                                adList.add(ad);
//                            }
//                        }
//                    } else {
//                        // 没有结果集(最后一页)
//                        mProtocolStatus = CSConsts.ProtocolStatus.RESULT_SUCCESS_EMPTY;
//                        mDataList.setLastPage(true);
//                    }
//                } else {
//                    // 没有结果集(最后一页)
//                    mProtocolStatus = CSConsts.ProtocolStatus.RESULT_SUCCESS_EMPTY;
//                    mDataList.setLastPage(true);
//                }
//            } else if (mProtocolStatus == CSConsts.ProtocolStatus.RESULT_SUCCESS_EMPTY) {
//                // 没有结果集(最后一页)
//                mDataList.setLastPage(true);
//            }
//        } catch (Exception e) {
//            Logger.getInstance(TAG).debug(e.getMessage(), e);
//        }
        return adList;
    }

    @Override
    public void afterParse() {
        super.afterParse();
    }
}
