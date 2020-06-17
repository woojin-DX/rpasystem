/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 사용자정보 service를 이용한 데이타 출력 컨트롤러 
 * 소  스  명 : UserInfoDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.admin.userinfo;

import com.woojin.commercial.common.SearchVO;
import com.woojin.commercial.login.LoginVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.woojin.commercial.common.CommandMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UserInfoController {
	Logger log = LoggerFactory.getLogger(this.getClass());

    //상용자정보 서비스 클래스 호출
    @Autowired
    UserInfoService userInfoService;

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/userinfo", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listUserInfo(@ModelAttribute("searchVO") SearchVO searchVO, CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                mv.addObject("userRole", userVO.getAuth_cd());//변수값

                Map<String, Object> resultMap = userInfoService.listUserInfo(searchVO, commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("companyList", resultMap.get("companyList")); //검색
                mv.addObject("authorityList", resultMap.get("authorityList")); //검색
                mv.addObject("userInfoList", resultMap.get("userInfoList")); //검색

                mv.setViewName("/admin/userList");
            }
            else{
                mv.setViewName("/login/denied");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/detailUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView detailUserInfo(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/admin/userInfoDetail");
        try {
            Map<String, Object> resultMap = userInfoService.detailUserInfo(commandMap);
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("userInfoDetail", resultMap.get("userInfoDetail")); //검색
        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 수정폼
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 내용을 수정폼에 뿌려준다
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/formUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView formUserInfo(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/admin/userInfoForm");
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap = userInfoService.detailUserInfo(commandMap);
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("userInfoDetail", resultMap.get("userInfoDetail")); //검색
        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 등록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일 내용 등록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/insertUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> insertUserInfo(HttpSession httpSession,
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

            Map<String, Object> msgMap = userInfoService.insertUserInfo(commandMap);

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
     * 함수  제목 : 사용자정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/updateUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateUserInfo(HttpSession httpSession,
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
            Map<String, Object> msgMap = userInfoService.updateUserInfo(commandMap);

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
     * 함수  제목 : 사용자정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 완전삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/deleteUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> deleteUserInfo(HttpServletRequest request,
                                              HttpServletResponse response,
                                              HttpSession httpSession,
                                              CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> msgMap = userInfoService.deleteUserInfo(commandMap);

        resultMap.put("status", msgMap.get("status"));
        resultMap.put("pageParam", msgMap.get("pageParam"));
        resultMap.put("msg", msgMap.get("msg"));

        return resultMap;
    }

     /* *******************************************************************************************
     * 함수  제목 : 사용자정보 다중입력/수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 다중입력/수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/multiInsertUpdateUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> insertUpdateMultiUserInfo(HttpSession httpSession,
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

            Map<String, Object> msgMap = userInfoService.multiInsertUpdateUserInfo(commandMap);

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
     * 함수  제목 : 사용자정보 다중 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 다중내용 완전삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/multiDeleteUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> deleteMultiUserInfo(HttpServletRequest request,
                                              HttpServletResponse response,
                                              HttpSession httpSession,
                                              CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> msgMap = userInfoService.multiIDeleteUserInfo(commandMap);

        resultMap.put("status", msgMap.get("status"));
        resultMap.put("pageParam", msgMap.get("pageParam"));
        resultMap.put("msg", msgMap.get("msg"));

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 다중 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 다중내용 완전삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/calluser", method = {RequestMethod.GET, RequestMethod.POST})

    public String insertUpdateCallUser(SearchVO searchVO, HttpSession httpSession, CommandMap commandMap, RedirectAttributes rttr) throws Exception {

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                //Map<String, Object> msgMap = userInfoService.insertUpdateCallUserInfo(commandMap);
                rttr.addAttribute("nCurrpage", searchVO.getNCurrpage());
                rttr.addAttribute("pagemode", searchVO.getPagemode());
                rttr.addAttribute("schword", searchVO.getSchword());
                rttr.addAttribute("coperation_cd", searchVO.getCoperation_cd());

//                Map<String, Object> resultMap = userInfoService.listUserInfo(searchVO,commandMap);

                //jsp 에서 보여줄 정보 추출
                /*
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("companyList", resultMap.get("companyList")); //검색
                mv.addObject("authorityList", resultMap.get("authorityList")); //검색
                mv.addObject("userInfoList", resultMap.get("userInfoList")); //검색

                mv.setViewName("/admin/userList");
                */

            }
            else{
                //mv.setViewName("/login/denied");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return "redirect:/admin/userinfo";
    }

}
