/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 발주정보 service를 이용한 데이타 출력 컨트롤러 
 * 소  스  명 : webDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.common.SearchVO;
import com.woojin.commercial.login.LoginVO;
import com.woojin.commercial.supply.DocumentService;
import com.woojin.commercial.supply.DocumentVO;
import com.woojin.commercial.util.ExcelStyleBuilder;

@Controller
public class webController {
	Logger log = LoggerFactory.getLogger(this.getClass());

    //상용자정보 서비스 클래스 호출
    @Autowired
    webService webService;

    @Autowired
    DocumentService documentService;

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/web", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listweb(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO != null) {
	            if (userVO.getAuth_cd().equals("WEB")) {
	                commandMap.put("company_cd",userVO.getCompany_cd().toString());
	                if (commandMap.get("process_cd") == null) commandMap.put("process_cd","ST_BOS");
	
	                Map<String, Object> resultMap = webService.listweb(commandMap);
	
	                //jsp 에서 보여줄 정보 추출
	                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
	                mv.addObject("infoParam", userVO); //변수값
	                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
	                mv.addObject("commonList", resultMap.get("commonList")); //검색
	                mv.addObject("webList", resultMap.get("webList")); //검색
	                mv.addObject("materialList", resultMap.get("materialList")); //검색
	                mv.addObject("placeList", resultMap.get("placeList")); //검색
	
	                mv.setViewName("/web/web");
	            }
	            else{
	                mv.setViewName("/login/denied");
	            }
	            mv.addObject("userRole", userVO.getAuth_cd());//변수값
            }
            else {
            	mv.setViewName("/login/login");
            }

        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/web/confirm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listwebConfirm(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("web")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                commandMap.put("process_cd","ST_SPG");

                Map<String, Object> resultMap = webService.listwebConfirm(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("webList", resultMap.get("webList")); //검색
                mv.addObject("materialList", resultMap.get("materialList")); //검색

                mv.setViewName("/web/confirm");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값

        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/web/resheet", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listwebResheet(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();
        FileOutputStream outStream = null;
        InputStream fis = null;

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("web")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                commandMap.put("process_cd","ST_SPG");

                Map<String, Object> resultMap = webService.listwebResheet(commandMap);
                List<webVO> listParam = (List<webVO>) resultMap.get("webList");

                //jsp 에서 보여줄 정보 추출
                mv.addObject("webList", resultMap.get("webList")); //검색
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값

                mv.setViewName("/web/resheet");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값

        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }
   
    /* *******************************************************************************************
     * 함수  제목 : 발주정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/web/detailweb", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> detailweb(CommandMap commandMap, HttpSession httpSession) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("web")) {
                resultMap = webService.detailweb(commandMap);
                resultMap.put("webDetail", resultMap.get("webDetail")); //내용
                resultMap.put("status", "0");
                resultMap.put("msg", "정상적으로 호출하였습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "권한이 업습니다.\r\n관리자에게 문의해주세요");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            resultMap.put("status", "1");
            resultMap.put("msg", "로그인 종료로 인하여 데이타 호출에 실패하였습니다\n로그인을 다시 해주세요");
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정폼
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 내용을 수정폼에 뿌려준다
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/web/formweb", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView formweb(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/web/webForm");
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap = webService.detailweb(commandMap);
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("webDetail", resultMap.get("webDetail")); //검색
        }
        catch(Exception e){
            log.error(e.toString());
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 등록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일 내용 등록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unused")
	@RequestMapping(value="/web/processweb", method = {RequestMethod.GET, RequestMethod.POST})
    public String processweb(SearchVO searchVO, HttpSession httpSession,
                                             CommandMap commandMap, RedirectAttributes rttr) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);

        try {
            Object object = httpSession.getAttribute("loginInfo");
            if (object != null) {
                LoginVO userVO = (LoginVO) object;
                commandMap.put("modify_id", userVO.getUser_id());
            }
            else{
                commandMap.put("modify_id", "");
            }

            Map<String, Object> msgMap = new HashMap<String, Object>();
            if (commandMap.get("processFlag").toString().equals("insert")) {
                Calendar mon = Calendar.getInstance();
                mon.add(Calendar.MONTH , -1);
                String firstDate = formatter.format(mon.getTime());

                Calendar time = Calendar.getInstance();
                String lastDate = formatter.format(time.getTime());
                String order_dt = formatter.format(time.getTime());

                commandMap.put("process_cd", "ST_BOS");
                commandMap.put("order_dt", order_dt);

                msgMap = webService.insertweb(commandMap);

                rttr.addFlashAttribute("resultMsg", msgMap.get("msg"));
                rttr.addFlashAttribute("resultFlag", "registerOK");
                rttr.addFlashAttribute("nCurrpage", 1);
                rttr.addFlashAttribute("pagemode", "list");
                rttr.addFlashAttribute("order_dt_start", firstDate);
                rttr.addFlashAttribute("order_dt_end", lastDate);
                rttr.addFlashAttribute("common_cd", "");
                rttr.addFlashAttribute("schword", "");
            }
            else if (commandMap.get("processFlag").toString().equals("update")) {
                msgMap = webService.updateweb(commandMap);
                rttr.addFlashAttribute("resultMsg", msgMap.get("msg"));
                rttr.addFlashAttribute("resultFlag", "modifyOK");
                rttr.addFlashAttribute("nCurrpage", searchVO.getNCurrpage());
                rttr.addFlashAttribute("pagemode", searchVO.getPagemode());
                rttr.addFlashAttribute("order_dt_start", searchVO.getOrder_dt_start());
                rttr.addFlashAttribute("order_dt_end", searchVO.getOrder_dt_end());
                rttr.addFlashAttribute("common_cd", searchVO.getCommon_cd());
                rttr.addFlashAttribute("schword", searchVO.getSchword());
            }
            else if (commandMap.get("processFlag").toString().equals("comfirm")) {
                msgMap = webService.updatewebRecive(commandMap);
                rttr.addFlashAttribute("resultMsg", msgMap.get("msg"));
                rttr.addFlashAttribute("resultFlag", "confirmOK");
                rttr.addFlashAttribute("nCurrpage", searchVO.getNCurrpage());
                rttr.addFlashAttribute("pagemode", searchVO.getPagemode());
                rttr.addFlashAttribute("order_dt_start", searchVO.getOrder_dt_start());
                rttr.addFlashAttribute("order_dt_end", searchVO.getOrder_dt_end());
                rttr.addFlashAttribute("common_cd", searchVO.getCommon_cd());
                rttr.addFlashAttribute("schword", searchVO.getSchword());
            }
            else if (commandMap.get("processFlag").toString().equals("delete")) {
                msgMap = webService.deleteweb(commandMap);
                rttr.addFlashAttribute("resultMsg", msgMap.get("msg"));
                rttr.addFlashAttribute("resultFlag", "deleteOK");
                rttr.addFlashAttribute("nCurrpage", 1);
                rttr.addFlashAttribute("pagemode", "list");
                rttr.addFlashAttribute("order_dt_start", searchVO.getOrder_dt_start());
                rttr.addFlashAttribute("order_dt_end", searchVO.getOrder_dt_end());
                rttr.addFlashAttribute("common_cd", searchVO.getCommon_cd());
                rttr.addFlashAttribute("schword", searchVO.getSchword());
            }

        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return "redirect:/web";
    }

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 등록
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
     * 내      용 : 단일 내용 등록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unused")
	@RequestMapping(value="/web/insertweb", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> insertweb(HttpSession httpSession,
                                           CommandMap commandMap) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("WEB")) {
                commandMap.put("modify_id", userVO.getUser_id());
                Calendar mon = Calendar.getInstance();
                mon.add(Calendar.MONTH , -1);
                String firstDate = formatter.format(mon.getTime());

                Calendar time = Calendar.getInstance();
                String lastDate = formatter.format(time.getTime());
                String order_dt = formatter.format(time.getTime());

                commandMap.put("process_cd", "ST_BOS");
                commandMap.put("order_dt", order_dt);
                Map<String, Object> msgMap = webService.insertweb(commandMap);

                resultMap.put("status", "0");
                resultMap.put("msg", "성공");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "권한이 업습니다.\r\n관리자에게 문의해주세요");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/web/updateweb", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateweb(HttpSession httpSession,
                                  CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("web")) {
                commandMap.put("modify_id", userVO.getUser_id());
                Map<String, Object> msgMap = webService.updateweb(commandMap);

                resultMap.put("status", msgMap.get("status"));
                resultMap.put("msg", msgMap.get("msg"));
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "권한이 업습니다.\r\n관리자에게 문의해주세요");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/web/updatewebRecive", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updatewebRecive(HttpSession httpSession,
                                            CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("web")) {
                Map<String, Object> msgMap = webService.updatewebRecive(commandMap);

                resultMap.put("status", msgMap.get("status"));
                resultMap.put("msg", msgMap.get("msg"));
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "권한이 업습니다.\r\n관리자에게 문의해주세요");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 완전삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/web/deleteweb", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> deleteweb(HttpServletRequest request,
                                              HttpServletResponse response,
                                              HttpSession httpSession,
                                              CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("web")) {
                Map<String, Object> msgMap = webService.deleteweb(commandMap);

                resultMap.put("status", msgMap.get("status"));
                resultMap.put("msg", msgMap.get("msg"));
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "권한이 업습니다.\r\n관리자에게 문의해주세요");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/web/detailMaterialNum", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> detailMaterialNum(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        try {
            Calendar time = Calendar.getInstance();
            String order_dt = formatter.format(time.getTime());

            commandMap.put("order_dt", order_dt);
            resultMap = webService.detailMaterialNum(commandMap);

            resultMap.put("detailMaterialNum", resultMap.get("detailMaterialNum")); //내용
            resultMap.put("status", "0");
            resultMap.put("msg", "데이타를 정상적으로 호출하였습니다");
        }
        catch(Exception e) {
            log.error(e.toString());
            resultMap.put("status", "1");
            resultMap.put("msg", "데이타 호출에 실패하였습니다");
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/web/overlabweb", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> overlabweb(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        try {
            Calendar time = Calendar.getInstance();
            String order_dt = formatter.format(time.getTime());

            commandMap.put("order_dt", order_dt);
            int overlab = webService.overlabCountweb(commandMap);
            if (overlab > 0){
                resultMap.put("status", "0");
                resultMap.put("msg", "금일 품번과 남품처가 발주건이 존재합니다.");
            }
            else{
                resultMap.put("status", "1");
                resultMap.put("msg", "정상등록 가능합니다.");
            }
        }
        catch(Exception e) {
            log.error(e.toString());
            resultMap.put("status", "0");
            resultMap.put("msg", "데이타 호출에 실패하였습니다");
        }
        return resultMap;
    }
    
    /* *******************************************************************************************
     * 함수  제목 : 발주정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/web/webHistory", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> listwebHistory(CommandMap commandMap, HttpSession httpSession) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("web")) {
                resultMap = webService.listwebHistory(commandMap);
                resultMap.put("webList", resultMap.get("webList")); //내용
                resultMap.put("status", "0");
                resultMap.put("msg", "정상적으로 호출하였습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "권한이 업습니다.\r\n관리자에게 문의해주세요");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            resultMap.put("status", "1");
            resultMap.put("msg", "로그인 종료로 인하여 데이타 호출에 실패하였습니다\n로그인을 다시 해주세요");
        }
        return resultMap;
    }

}
