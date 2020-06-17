/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : AuthorizationAspect
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

package com.woojin.commercial.framework.aop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.util.WebUtils;
import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.login.LoginService;

@Aspect
public class AuthorizationAspect {
	Logger log = LoggerFactory.getLogger(this.getClass());

    private ArrayList<String> excludeUrls;

    @Resource(name="loginService")
    private LoginService loginService;


    /** 접근인증을 기능을 사용할 경우에는 true로 설정해야 함 */
    private boolean bCheckAuthor = false;

    /**
     * 예외 URL을 생성자 매개변수로 받는다.
     *
     * @paramater excludeUrl
     */
    public AuthorizationAspect(boolean bCheckAuthor, ArrayList<String> excludeUrls) {
        this.bCheckAuthor = bCheckAuthor;
        this.excludeUrls = excludeUrls;
    }

    /**
     * 모든 Controller에 적용.
     *
     * 정상적인 접근인 접근인가 확인.
     *
     * Controller에서 Exception 발생시 오류 JSON으로 대체 처리.
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @SuppressWarnings("unused")
	@Around("execution(* com.woojin.commercial..*Controller.*(..)) ")
    public Object checkAuthorization(ProceedingJoinPoint joinPoint) throws Throwable {
        // 접근 인증 부분 적용하지 않을 때

        // 접근 URL를 구한다.
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        //HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
        Object[] args = joinPoint.getArgs();

        if ( log.isDebugEnabled() ) {
            // 접근 Parameter 출력
            if (request.getMethod().equalsIgnoreCase("POST")) {
                StringBuilder strBuf = new StringBuilder();
                strBuf.append("request : ")
                        .append(request.getRequestURI())
                        .append(" [JSON] ");
                for (Object object : args) {
                    if (object instanceof CommandMap && 0 < ((CommandMap) object).getMap().size()) {
                        strBuf.append( JSONObject.toJSONString(((CommandMap) object).getMap()) );
                    }
                    else if ( object instanceof String ) {
                        strBuf.append( (String) object );
                    }
                }
                log.debug(strBuf.toString());
            }
            else if (request.getMethod().equalsIgnoreCase("GET")) {
                log.debug("request : " + request.getRequestURI() + "?" + ((request.getQueryString() != null) ? request.getQueryString() : "") );
            }
        }

        // 접근 인증 부분 적용하지 않을 때

        if ( bCheckAuthor == true ) {
            try {
                String strUrl = request.getRequestURI();
                boolean isPass = false;

                // 권한Check 예외 URL 확인
                for (String url : excludeUrls) {
                    if ( strUrl.indexOf(url) != -1 ) {
                        isPass = true;
                        break;
                    }
                }

                if ( isPass == false  ) {
                    // 접근권한을 검사한다.
                    CommandMap commandMap = null;
                    String params = null;

                    if (request.getMethod().equalsIgnoreCase("POST")) {
                        for (Object object : args) {
                            if ( object instanceof CommandMap ) {
                                commandMap = (CommandMap) object;
                            }
                            else if ( object instanceof String ) {
                                params = (String) object;
                            }
                        }
                    }
                    else if (request.getMethod().equalsIgnoreCase("GET")) {
                        for (Object object : args) {
                            if ( object instanceof CommandMap ) {
                                commandMap = (CommandMap) object;
                            }
                            else if ( object instanceof String ) {
                                params = (String) object;
                            }
                        }

                    }
                    else {
                        return "login/login";
                        //return CommonUtils.getErpMessageModelAndView(9, null,null);  // "정상적인 경로로 접근해주세요"
                    }
                    //로그인상태가 아니면 홈으로 이동시킨다.
                    if (request.getSession().getAttribute("loginInfo") == null)
                    {
                        // 세션정보가 없으면 쿠키 정보를 확인한다.
                        Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
                        if (loginCookie != null)
                        {
                            // 쿠키에서 세션ID를 꺼낸다.
                            CommandMap sessionMap = new CommandMap();
                            sessionMap.put("sessionid", loginCookie.getValue());

                            // 세션ID값을 기준으로 검색하여 로그인 정보를 가져온다.
                            HashMap<String, Object> resultMap = loginService.checkUserInfoWithCookie(sessionMap);
                            // 로그인 정보가 존재하는 경우
                            if (resultMap != null)
                            {
                                // 세션에 로그인 정보를 저장한다.
                                request.getSession().setAttribute("loginInfo", resultMap);
                                request.getSession().setMaxInactiveInterval(60 * 30);
//                                return true;
                            }
                        }
                        else
                        {
                            // 프로젝트의 Context Path명을 반환하고 그 경로에 /home/openHome.do를 추가한다.
                            //response.sendRedirect(request.getContextPath() + "/login");
                            //CommonUtils.getErpMessageModelAndView(0, null,null);
                            //return "login/login";
                        }

                    }
                    //else{
                        //CommonUtils.getErpMessageModelAndView(0, null,null);
                      //  return false;
                    //}


                }
            }
            catch (Exception e) {
                log.error(e.toString());
                e.printStackTrace();
                //return CommonUtils.getErpMessageModelAndView(0, null,null);  // "서버에서 에러가 발생했습니다. 관리자에게 문의해주세요."
                return "login/login";
            }
        }
        // 계속 진행.
        try {
            Object retVal = joinPoint.proceed();

            try {
                // LOG
                if (log.isDebugEnabled()) {
                    if (retVal instanceof ModelAndView) {
                        Map<String, Object> logMap = ((ModelAndView) retVal).getModel();
                        log.debug("response : " + JSONObject.toJSONString(logMap));
                    }
                }
            }
            catch (Exception e) {
                log.error(e.toString());
                e.printStackTrace();
                //return CommonUtils.getErpMessageModelAndView(0, null,null);
                return false;
            }

            return retVal;
        }
        catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
            //return CommonUtils.getErpMessageModelAndView(0, null,null);
            return false;
        }
    }
}
