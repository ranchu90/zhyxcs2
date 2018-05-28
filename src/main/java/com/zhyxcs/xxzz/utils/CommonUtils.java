package com.zhyxcs.xxzz.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

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
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }


    public static String getBankKindCode(String transactionNum){
        if(transactionNum==null||transactionNum.length()!=24){
            return null;
        }
        return transactionNum.substring(0,1);
    }

    public static String getBankTypeCode(String transactionNum){
        if(transactionNum==null||transactionNum.length()!=24){
            return null;
        }
        return transactionNum.substring(0,3);
    }

    public static String getBankCode(String transactionNum){
        if(transactionNum==null||transactionNum.length()!=24){
            return null;
        }
        return transactionNum.substring(0,12);
    }

    public static void main(String[] args) {
        //System.out.println(MD5("crams888"));
        System.out.println(getBankCode("104569018015201805210001"));
    }
}
