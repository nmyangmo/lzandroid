package com.lazy.android.baseui.tools;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/11.
 */
public class JudgeWeek {
    public static String judgeWeek(long systemTime){
        Calendar calendar = Calendar.getInstance();
        String str = "星期";
        Date date = new Date(systemTime);
        calendar.setTime(date);
        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case 1:
                str += "天";
                break;
            case 2:
                str += "一";
                break;
            case 3:
                str += "二";
                break;
            case 4:
                str += "三";
                break;
            case 5:
                str += "四";
                break;
            case 6:
                str += "五";
                break;
            case 7:
                str += "六";
                break;
            default:
                break;
        }
        return str;
    }
}
