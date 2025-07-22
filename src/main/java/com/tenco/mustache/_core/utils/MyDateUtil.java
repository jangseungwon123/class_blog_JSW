package com.tenco.mustache._core.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.util.Date;

public class MyDateUtil {

    public static String timestampFormat(Timestamp time){
        Date currnetDate = new Date(time.getTime());
        return DateFormatUtils.format(currnetDate,"yyyy-MM-dd HH:mm");
    }
}
