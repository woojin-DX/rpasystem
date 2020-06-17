/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : LoginController
 *   @Description   :
 *   @Author        : GACHINOEL
 *   @Version       : v1.0
 *   Copyright(c) 2019 WOOJIN All rights reserved
 *   ------------------------------------------------------------------------------
 *                    변         경         사         항
 *   ------------------------------------------------------------------------------
 *      DATE           AUTHOR                       DESCRIPTION
 *   ---------------  ----------    ------------------------------------------------
 *   2019.11.14       gachinoel     신규생성
 *   ------------------------------------------------------------------------------
 */

package com.woojin.commercial.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import com.woojin.commercial.common.CommandMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
	Logger log = LoggerFactory.getLogger(this.getClass());

    //상용자정보 서비스 클래스 호출
    @Autowired
    LoginService loginService;

    @RequestMapping(value="/login")
    public ModelAndView openLogin(HttpServletRequest request, HttpServletResponse response, CommandMap commandMap) throws Exception {
        ModelAndView mav = new ModelAndView();

        //세션정보가 null이 아닐 때
        if (request.getSession().getAttribute("loginInfo") != null)
        {
            String msg = "이미 로그인된 상태입니다.";
            mav.addObject("msg", msg);
            mav.setViewName("/login/loginSuccess");
        }
        else
        {
            mav.setViewName("/login/login");
        }
        return mav;
    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               HttpSession httpSession) throws Exception {
        Object object = httpSession.getAttribute("loginInfo");
        if (object != null) {
            LoginVO loginVO = (LoginVO) object;
            httpSession.removeAttribute("loginInfo");
            httpSession.invalidate();
            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
            if (loginCookie != null) {
                loginCookie.setPath("/");
                loginCookie.setMaxAge(0);
                response.addCookie(loginCookie);
                CommandMap commandMap = new CommandMap();
                commandMap.put("sessionid", "none");
                // currentTimeMillis()가 1/1000초 단위이므로 (* 1000) 한다.
                //Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * (60*60*24*7)));
                commandMap.put("sessionlimit", new Date());
                commandMap.put("user_id", loginVO.getUser_id());
                loginService.keepLogin(commandMap);
            }
        }

        ModelAndView mav = new ModelAndView("/login/logout");
        mav.addObject("msg", "로그아웃되었습니다.");

        return mav;
    }

    @RequestMapping(value="/login/needLogin")
    public ModelAndView needLogin() throws Exception {
        ModelAndView mav = new ModelAndView("/login/loginWarning");
        mav.addObject("msg", "로그인 후 이용해주시기 바랍니다.");

        return mav;
    }

    // 로그인 메소드
    @RequestMapping(value="/loginProcess")
    @ResponseBody
    public Map<String, Object> processLogin(HttpServletRequest request,
                                     HttpServletResponse response,
                                     CommandMap commandMap) throws Exception {

        //String user_pwd = commandMap.get("user_pwd").toString();
        //commandMap.put("user_pwd", ShaUtil.getSHA256(user_pwd));
        // 사용자 정보를 조회한다.
        LoginVO loginInfo = loginService.detailLogin(commandMap);

        // 요청에 응답하기 위한 맵 객체를 성한다.
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(loginInfo == null)
        {
            resultMap.put("status", 1);
            resultMap.put("msg", "입력하신 정보가 잘못되었거나 존재하지 않는 정보입니다.");
        }
        else
        {
            // 로그인 세션 생성
            request.getSession().setAttribute("loginInfo", loginInfo);
            request.getSession().setMaxInactiveInterval(60 * 30);
            // 로그인 쿠키 생성 유무 판단
            if (commandMap.get("isUseCookie").equals("Y"))
            {
                // 쿠키를 생성하고 생성한 세션의 id를 쿠키에 저장한다.
                Cookie cookie = new Cookie("loginCookie", request.getSession().getId());
                // 쿠키를 찾을 경로를 컨텍스트 경로로 변경한다.
                cookie.setPath("/");
                // 7일로 유효기간을 설정한다.
                cookie.setMaxAge(60*60*24*7);
                // 쿠키를 reponse객체에 담는다.
                response.addCookie(cookie);

                CommandMap commandMap1 = new CommandMap();
                commandMap1.put("user_id", loginInfo.getUser_id());
                commandMap1.put("sessionid", request.getSession().getId());
                // currentTimeMillis()가 1/1000초 단위이므로 (* 1000) 한다.
                Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * (60*60*24*7)));
                commandMap1.put("sessionlimit", sessionLimit);
                commandMap1.put("loginInfo", loginInfo);
                loginService.keepLogin(commandMap1);
            }
            loginService.updateLogin(commandMap);

            resultMap.put("userRole", loginInfo.getAuth_cd());
            resultMap.put("status", 0);
            resultMap.put("msg", "정상적으로 로그인되었습니다.");
        }

        return resultMap;
    }
}
