/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 :  service를 이용한 데이타 출력 컨트롤러 
 * 소  스  명 : DocumentDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.supply;

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
public class DocumentController {
	Logger log = LoggerFactory.getLogger(this.getClass());

    //상용자정보 서비스 클래스 호출
    @Autowired
    DocumentService documentService;

    /* *******************************************************************************************
     * 함수  제목 :  목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/supply/listDocument.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listDocument(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/supply/documentList");

        try {
            Map<String, Object> resultMap = documentService.listDocument(commandMap);

            //jsp 에서 보여줄 정보 추출
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
            mv.addObject("documentList", resultMap.get("documentList")); //검색
        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 :  상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/supply/detailDocument.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView detailDocument(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/supply/documentDetail");
        try {
            Map<String, Object> resultMap = documentService.detailDocument(commandMap);
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("documentDetail", resultMap.get("documentDetail")); //검색
        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 :  수정폼
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 해당 내용을 수정폼에 뿌려준다
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/supply/formDocument.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView formDocument(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/supply/documentForm");
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap = documentService.detailDocument(commandMap);
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("documentDetail", resultMap.get("documentDetail")); //검색
        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 :  등록
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 단일 내용 등록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/supply/insertDocument.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> insertDocument(HttpSession httpSession,
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

            Map<String, Object> msgMap = documentService.insertDocument(commandMap);

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
     * 함수  제목 :  수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 단일내용 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/supply/updateDocument.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateDocument(HttpSession httpSession,
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
            Map<String, Object> msgMap = documentService.updateDocument(commandMap);

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
     * 함수  제목 :  완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 단일내용 완전삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/supply/deleteDocument.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> deleteDocument(HttpServletRequest request,
                                              HttpServletResponse response,
                                              HttpSession httpSession,
                                              CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> msgMap = documentService.deleteDocument(commandMap);

        resultMap.put("status", msgMap.get("status"));
        resultMap.put("pageParam", msgMap.get("pageParam"));
        resultMap.put("msg", msgMap.get("msg"));

        return resultMap;
    }

}
