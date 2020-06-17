/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 권한정보 service를 이용한 데이타 출력 컨트롤러 
 * 소  스  명 : AuthorityDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.admin.authority;

import com.woojin.commercial.login.LoginVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.woojin.commercial.common.CommandMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class AuthorityController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    //상용자정보 서비스 클래스 호출
    @Autowired
    AuthorityService authorityService;

    /* *******************************************************************************************
     * 함수  제목 : 권한정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/listAuthority", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listAuthority(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/admin/authorityList");

        try {
            Map<String, Object> resultMap = authorityService.listAuthority(commandMap);

            //jsp 에서 보여줄 정보 추출
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
            mv.addObject("authorityList", resultMap.get("authorityList")); //검색
        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 권한정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/detailAuthority", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView detailAuthority(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/admin/authorityDetail");
        try {
            Map<String, Object> resultMap = authorityService.detailAuthority(commandMap);
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("authorityDetail", resultMap.get("authorityDetail")); //검색
        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 권한정보 수정폼
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 내용을 수정폼에 뿌려준다
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/formAuthority", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView formAuthority(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/admin/authorityForm");
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap = authorityService.detailAuthority(commandMap);
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("authorityDetail", resultMap.get("authorityDetail")); //검색
        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 권한정보 등록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일 내용 등록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/insertAuthority", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> insertAuthority(HttpSession httpSession,
                               CommandMap commandMap) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            if (object != null) {
                LoginVO userVO = (LoginVO) object;
                commandMap.put("reg_id", userVO.getUser_id());
            }
            else{
                commandMap.put("reg_id", "");
            }

            Map<String, Object> msgMap = authorityService.insertAuthority(commandMap);

            resultMap.put("status", msgMap.get("status"));
            resultMap.put("msg", msgMap.get("msg"));
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 권한정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/updateAuthority", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateAuthority(HttpSession httpSession,
                                  CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            if (object != null) {
                LoginVO userVO = (LoginVO) object;
                commandMap.put("mod_id", userVO.getUser_id());
            }
            else{
                commandMap.put("mod_id", "");
            }
            Map<String, Object> msgMap = authorityService.updateAuthority(commandMap);

            resultMap.put("status", msgMap.get("status"));
            resultMap.put("msg", msgMap.get("msg"));
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 권한정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 완전삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/deleteAuthority", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> deleteAuthority(HttpServletRequest request,
                                              HttpServletResponse response,
                                              HttpSession httpSession,
                                              CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> msgMap = authorityService.deleteAuthority(commandMap);

        resultMap.put("status", msgMap.get("status"));
        resultMap.put("pageParam", msgMap.get("pageParam"));
        resultMap.put("msg", msgMap.get("msg"));

        return resultMap;
    }

}
