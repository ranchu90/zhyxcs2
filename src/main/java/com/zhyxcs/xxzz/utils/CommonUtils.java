package com.zhyxcs.xxzz.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CommonUtils {
    public static boolean compareString(String srcStr, String destStr) {
        if (srcStr == null && destStr == null) {
            return true;
        } else {
            if (srcStr != null && destStr == null) {
                return false;
            }
            if (srcStr == null && destStr != null) {
                return false;
            }
            return srcStr.equals(destStr);
        }
    }

    public static boolean isEmpty(String stringValue) {
        return stringValue == null || ("null").equals(stringValue)
                || stringValue.trim().length() == 0;
    }

    public static String MD5(String source) {
        StringBuilder sb = new StringBuilder();
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(source.getBytes());
            for (byte b : md5.digest()) {
                // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
                sb.append(String.format("%02X", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String objectToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = null;
        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (isEmpty(ipAddresses) || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (isEmpty(ipAddresses) || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isEmpty(ipAddresses) || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (isEmpty(ipAddresses) || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (!isEmpty(ipAddresses)) {
            ip = ipAddresses.split(",")[0];
        }
        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (isEmpty(ipAddresses) || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }


    public static String getBankKindCode(String transactionNum) {
        if (transactionNum == null || transactionNum.length() != 24) {
            return null;
        }
        return transactionNum.substring(0, 1);
    }

    public static String getBankTypeCode(String transactionNum) {
        if (transactionNum == null || transactionNum.length() != 24) {
            return null;
        }
        return transactionNum.substring(0, 3);
    }

    public static String getBankCode(String transactionNum) {
        if (transactionNum == null || transactionNum.length() != 24) {
            return null;
        }
        return transactionNum.substring(0, 12);
    }

    public static void downloadImage(String path, HttpServletResponse response){

        File file = new File(path);
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            long size = file.length();
            byte[] temp = new byte[(int) size];
            fis.read(temp, 0, (int) size);
            fis.close();
            byte[] data = temp;
            response.setContentType("blob");
            OutputStream out = response.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //如果是客户端断开连接，不记录异常，不打印异常
            if (!(e instanceof org.apache.catalina.connector.ClientAbortException)) {
                e.printStackTrace();
            } else {
                System.out.println("客户端断开下载图片链接");
            }
        }
    }

    public static void main(String[] args) {
        //System.out.println(MD5("crams888"));
        //System.out.println(getBankCode("104569018015201805210001"));
        System.out.println(OrgaLevelEnum.BANKOFCOMMERCE);
    }

    public static Date newDate(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(0);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }
}
