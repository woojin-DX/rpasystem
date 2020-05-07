/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 공통코드 service를 이용한 데이타 출력 컨트롤러 
 * 소  스  명 : CommonCodeDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.admin.commoncode;

import com.woojin.commercial.login.LoginVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.admin.commoncode.CommonCodeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class CommonCodeController {
    Logger log = Logger.getLogger(this.getClass());

    //상용자정보 서비스 클래스 호출
    @Autowired
    CommonCodeService commonCodeService;

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/commoncode", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listCommonCode(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {

                Map<String, Object> resultMap = commonCodeService.listCommonCode(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("commonCodeList", resultMap.get("commonCodeList")); //검색
                mv.setViewName("/admin/commonCode");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }


    /* *******************************************************************************************
     * 함수  제목 : 공통코드 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/detailCommonCode", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView detailCommonCode(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/admin/commonCodeDetail");
        try {
            Map<String, Object> resultMap = commonCodeService.detailCommonCode(commandMap);
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("commonCodeDetail", resultMap.get("commonCodeDetail")); //검색
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 수정폼
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 내용을 수정폼에 뿌려준다
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/formCommonCode", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView formCommonCode(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/admin/commonCodeForm");
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap = commonCodeService.detailCommonCode(commandMap);
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("commonCodeDetail", resultMap.get("commonCodeDetail")); //검색
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 등록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일 내용 등록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/insertCommonCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> insertCommonCode(HttpSession httpSession,
                               CommandMap commandMap) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            if (object != null) {
                LoginVO userVO = (LoginVO) object;
                commandMap.put("register_id", userVO.getUser_id());
                commandMap.put("modify_id", userVO.getUser_id());
            }
            else{
                commandMap.put("register_id", "");
                commandMap.put("modify_id", "");
            }

            Map<String, Object> msgMap = commonCodeService.insertCommonCode(commandMap);

            resultMap.put("status", msgMap.get("status"));
            resultMap.put("msg", msgMap.get("msg"));
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/updateCommonCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateCommonCode(HttpSession httpSession,
                                  CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            if (object != null) {
                LoginVO userVO = (LoginVO) object;
                commandMap.put("modify_id", userVO.getUser_id());
            }
            else{
                commandMap.put("modify_id", "");
            }
            Map<String, Object> msgMap = commonCodeService.updateCommonCode(commandMap);

            resultMap.put("status", msgMap.get("status"));
            resultMap.put("msg", msgMap.get("msg"));
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 완전삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/deleteCommonCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> deleteCommonCode(HttpServletRequest request,
                                              HttpServletResponse response,
                                              HttpSession httpSession,
                                              CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> msgMap = commonCodeService.deleteCommonCode(commandMap);

        resultMap.put("status", msgMap.get("status"));
        resultMap.put("pageParam", msgMap.get("pageParam"));
        resultMap.put("msg", msgMap.get("msg"));

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 완전삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/multiDeleteCommonCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> deleteMultiCommonCode(HttpServletRequest request,
                                                HttpServletResponse response,
                                                HttpSession httpSession,
                                                CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> msgMap = commonCodeService.deleteCommonCode(commandMap);

        resultMap.put("status", msgMap.get("status"));
        resultMap.put("pageParam", msgMap.get("pageParam"));
        resultMap.put("msg", msgMap.get("msg"));

        return resultMap;
    }
}
