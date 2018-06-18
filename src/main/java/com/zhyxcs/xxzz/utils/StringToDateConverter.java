package com.zhyxcs.xxzz.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {
    private static final String shortDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS Z";

    @Override
    public Date convert(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        value = value.replace("Z", " UTC");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(shortDateFormat);
        try {
            return simpleDateFormat.parse(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
