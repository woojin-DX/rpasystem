/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : CommonUtils
 *   @Description   :
 *   @Author        : GACHINOEL
 *   @Version       : v1.0
 *   Copyright(c) 2019 WOOJIN All rights reserved
 *   ------------------------------------------------------------------------------
 *                    변         경         사         항
 *   ------------------------------------------------------------------------------
 *      DATE           AUTHOR                       DESCRIPTION
 *   ---------------  ----------    ------------------------------------------------
 *   2019.10.28       gachinoel     신규생성
 *   ------------------------------------------------------------------------------
 */

package com.woojin.commercial.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {

    // 상품코드 쿼리로그 추출
    public static Logger log = LoggerFactory.getLogger(CommonUtils.class.getClass());

    public static String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean isNumber(String str) {

        //먼저 유효성 체크 검사부터....
        if(str==null || str.equals(""))
            return false;

        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static String sGetCurrentTime(String format){
        // 1hour(ms) = 60s * 60m * 1000ms
        int millisPerHour = 60 * 60 * 1000;
        SimpleDateFormat fmt= new SimpleDateFormat(format);

        // TimeZone timeZone = TimeZone.getTimeZone( "UTC" );
        SimpleTimeZone timeZone = new SimpleTimeZone(9*millisPerHour,"KST");

        fmt.setTimeZone(timeZone);
        String str = fmt.format(new Date(System.currentTimeMillis()));
        return str;
    }

    /**
     * 현재 서버의 IP 주소를 가져옵니다.
     *
     * @return IP 주소
     */
    public static String getLocalServerIp()
    {
        try
        {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress())
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex) {}
        return null;
    }

    public static String getClientIP() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip ==  null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-RealIP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // ipv6 (ipv4 127.0.0.1) localhost로 접속했을시
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip = "127.0.0.1";
        }

        return ip;
    }

    /**
     * 에러 결과 반환용 ModelAndView를 생성하여 반환.
     *
     * @return
     */
    public static ModelAndView getErpMessageModelAndView(int restState, String sClass, String sMethod) {
        ModelAndView mv = new ModelAndView("jsonView"); //JSON 모델 생성
        HashMap<String,Object> map1 = new HashMap<String, Object>();

        String retMessage = "";

        switch (restState) {
            case 0    :
                retMessage = "서버에서 에러가 발생했습니다. 관리자에게 문의해주세요.";
                break;
            case 1    :
                retMessage = "데이타가 정상적으로 처리되었습니다.";
                break;
            case 2    :
                retMessage = "데이타가 존재하지 않습니다.";
                break;
            case 3    :
                retMessage = "중복데이타가 존재합니다.";
                break;
            case 4    :
                retMessage = "데이타 처리에 실패했습니다.";
                break;
            case 5    :
                retMessage = "데이타 처리 중 오류가 발생했습니다.";
                break;
            case 6    :
                retMessage = "로그인 세션시간이 종료되었습니다.\n다시 로그인해주세요.";
                break;
            case 9    :
                retMessage = "정상적인 경로로 접근해주세요";
                break;
        }

        map1.put("retstate", restState);
        map1.put("retClass", sClass);
        map1.put("retMethod", sMethod);
        map1.put("retmesssage", retMessage);

        mv.addObject("resultmsg", map1);

        return mv;
    }

    /**
     * 특정 변수를 제외해서 vo를 map형식으로 변환해서 반환.
     * @param vo VO
     * @param arrExceptList 제외할 property 명 리스트
     * @return
     * @throws Exception
     */
    public static Map<String, Object> domainToMapWithExcept(Object vo, String[] arrExceptList) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        BeanInfo info = Introspector.getBeanInfo(vo.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            Method reader = pd.getReadMethod();
            if (reader != null) {
                if(arrExceptList != null && arrExceptList.length > 0 && isContain(arrExceptList, pd.getName())) continue;
                result.put(pd.getName(), reader.invoke(vo));
            }
        }
        return result;
    }
    public static Boolean isContain(String[] arrList, String name) {
        for (String arr : arrList) {
            if (StringUtils.contains(arr, name))
                return true;
        }
        return false;
    }


}
