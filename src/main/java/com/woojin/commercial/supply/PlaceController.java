/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 납품장소 service를 이용한 데이타 출력 컨트롤러 
 * 소  스  명 : PlaceDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.supply;

import com.woojin.commercial.login.LoginVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.woojin.commercial.common.CommandMap;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class PlaceController {
    Logger log = Logger.getLogger(this.getClass());

    //상용자정보 서비스 클래스 호출
    @Autowired
    PlaceService placeService;

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/supply/listPlace", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listPlace(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView("/orderplace/placeList");

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SUPPLY")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                Map<String, Object> resultMap = placeService.listPlace(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("placeList", resultMap.get("placeList")); //검색
                mv.setViewName("/supply/place");
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
     * 함수  제목 : 납품장소 등록
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
     * 내      용 : 단일 내용 등록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/supply/insertPlace", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> insertPlace(HttpSession httpSession,
                               CommandMap commandMap) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SUPPLY")) {
                Map<String, Object> msgMap = placeService.insertPlace(commandMap);

                resultMap.put("status", msgMap.get("status"));
                resultMap.put("msg", msgMap.get("msg"));
            }
            else{
                resultMap.put("status", 0);
                resultMap.put("msg", "권한이 업습니다.\r\n관리자에게 문의해주세요");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
     * 내      용 : 단일내용 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/supply/updatePlace", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updatePlace(HttpSession httpSession,
                                  CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SUPPLY")) {
                Map<String, Object> msgMap = placeService.updatePlace(commandMap);

                resultMap.put("status", msgMap.get("status"));
                resultMap.put("msg", msgMap.get("msg"));
            }
            else{
                resultMap.put("status", 0);
                resultMap.put("msg", "권한이 업습니다.\r\n관리자에게 문의해주세요");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }

        return resultMap;
    }

}
