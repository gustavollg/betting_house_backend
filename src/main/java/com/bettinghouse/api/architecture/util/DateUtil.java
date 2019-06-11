package com.bettinghouse.api.architecture.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static String toISO8601UTC(Date date) {
        TimeZone timeZoneUTC = TimeZone.getTimeZone("UTC");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        simpleDateFormat.setTimeZone(timeZoneUTC);
        return simpleDateFormat.format(date);
    }
}