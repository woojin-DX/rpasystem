/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : mtm정보 service를 이용한 데이타 출력 컨트롤러 
 * 소  스  명 : ShippingMtmDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.shipping.shippingmtm;

import com.woojin.commercial.login.LoginVO;
import com.woojin.commercial.util.ExcelBuilder;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.shipping.shippingmtm.ShippingMtmService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ShippingMtmController {
    Logger log = Logger.getLogger(this.getClass());

    //상용자정보 서비스 클래스 호출
    @Autowired
    ShippingMtmService shippingMtmService;

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/shipping/mtmlist", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listShippingMtm(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SHIPPING")) {
                commandMap.put("process_cd", "ST_CFM");

                Map<String, Object> resultMap = shippingMtmService.listShippingMtm(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("shippingMtmList", resultMap.get("shippingMtmList")); //검색
                mv.setViewName("/shipping/mtmlist");
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
     * 함수  제목 : mtm정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/shipping/detailShippingMtm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView detailShippingMtm(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/shipping/shippingMtmDetail");
        try {
            Map<String, Object> resultMap = shippingMtmService.detailShippingMtm(commandMap);
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("shippingMtmDetail", resultMap.get("shippingMtmDetail")); //검색
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 팝업
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 내용을 수정폼에 뿌려준다
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/shipping/mtmpop", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mtmPop(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SHIPPING")) {
                commandMap.put("modify_id", userVO.getUser_id());
                commandMap.put("process_id", userVO.getUser_id());

                //jsp 에서 보여줄 정보 추출
                //Map<String, Object> resultMap = adminService.detailShipping(commandMap);
                Map<String, Object> matrialMap = shippingMtmService.listShippingMtmPop(commandMap);

                //mv.addObject("shippingDetail", resultMap.get("shippingDetail")); //변수값
                mv.addObject("shippingMtmList", matrialMap.get("shippingMtmList")); //변수값
                mv.setViewName("/shipping/mtmpop");
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
     * 함수  제목 : mtm정보 수정폼
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 내용을 수정폼에 뿌려준다
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/shipping/mtmreg", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView formShippingMtm(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SHIPPING")) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap = shippingMtmService.detailShippingMtm(commandMap);
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("shippingMtmDetail", resultMap.get("shippingMtmDetail")); //검색
                mv.setViewName("/shipping/mtmpopReg");
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
     * 함수  제목 : mtm정보 등록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일 내용 등록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/shipping/insertShippingMtm", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> insertShippingMtm(HttpSession httpSession,
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

            Map<String, Object> msgMap = shippingMtmService.insertShippingMtm(commandMap);

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
     * 함수  제목 : mtm정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/shipping/updateShippingMtm", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateShippingMtm(HttpSession httpSession,
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
            Map<String, Object> msgMap = shippingMtmService.updateShippingMtm(commandMap);

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
     * 함수  제목 : mtm정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 완전삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/shipping/deleteShippingMtm", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> deleteShippingMtm(HttpServletRequest request,
                                              HttpServletResponse response,
                                              HttpSession httpSession,
                                              CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> msgMap = shippingMtmService.deleteShippingMtm(commandMap);

        resultMap.put("status", msgMap.get("status"));
        resultMap.put("pageParam", msgMap.get("pageParam"));
        resultMap.put("msg", msgMap.get("msg"));

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @ResponseBody
    @RequestMapping(value = "/shipping/mtmlistExcel", method = {RequestMethod.GET, RequestMethod.POST})
    public void listShippingMtmExcel(CommandMap commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception{

        try {
            String excelTitle = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());


            Map<String, Object> tempMap = new HashMap<String, Object>();

            Map<String, Object> titleStyleMap = new HashMap<String, Object>();
            List<Map<String, Object>> fieldInfoList = new ArrayList<Map<String, Object>>();
            Map<String, Object> excelInfpMap = new HashMap<String, Object>();

            Map<String, Object> resultMap = shippingMtmService.listShippingMtm(commandMap);

            List<Object> objResult = (List<Object>) resultMap.get("shippingMtmList");

            excelTitle = "MTM목록정보";

            //첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
            Map<String, Object> headerMap = new HashMap<String, Object>();

            headerMap.put("sRow", "1");
            headerMap.put("eRow", "1");
            headerMap.put("sCol", "0");
            headerMap.put("eCol", "7");
            headerMap.put("fontType", "titleLine");
            headerMap.put("fontColor", "000000");
            headerMap.put("styleColor", "FFFFFF");
            headerMap.put("textAlign", "center");
            headerMap.put("textVAlign", "center");
            headerMap.put("line", "none");
            headerMap.put("title", excelTitle);

            titleStyleMap.put("sRow", "3");
            titleStyleMap.put("eRow", "3");
            titleStyleMap.put("sCol", "0");
            titleStyleMap.put("eCol", "7");
            titleStyleMap.put("fontType", "subtitle");
            titleStyleMap.put("fontColor", "000000");
            titleStyleMap.put("styleColor", "ECF5FC");
            titleStyleMap.put("textAlign", "center");
            titleStyleMap.put("textVAlign", "center");
            titleStyleMap.put("line", "line");

            fieldInfoList = new ArrayList<Map<String, Object>>();

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "process_nm");
            tempMap.put("cellTitle", "상태");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 10*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "company_cd");
            tempMap.put("cellTitle", "업체코드");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 20*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "left");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "supply_dt");
            tempMap.put("cellTitle", "출하일");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "left");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "modi_meterial_num");
            tempMap.put("cellTitle", "변경 전 자재코드");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 20*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "left");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "pre_storage_loc");
            tempMap.put("cellTitle", "변경 전 저장위치");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 20*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "left");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "modi_qty");
            tempMap.put("cellTitle", "MTM수량");
            tempMap.put("fileType", "Int");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "right");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "material_num");
            tempMap.put("cellTitle", "변경 후 자재코드");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 20*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "left");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "storage_loc");
            tempMap.put("cellTitle", "변경 후 저장위치");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 20*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "left");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            excelInfpMap = new HashMap<String, Object>();
            excelInfpMap.put("headerMap", headerMap);
            excelInfpMap.put("titleStyleMap", titleStyleMap);
            excelInfpMap.put("fieldInfoList", fieldInfoList);

            SXSSFWorkbook workbook = ExcelBuilder.buildExcelXSS(excelTitle, excelInfpMap, objResult, false);


            String realExcelFilename = null;
            realExcelFilename = URLEncoder.encode(excelTitle + "_"  + strToday, "UTF-8");
            realExcelFilename = realExcelFilename.replaceAll("\\+", " ");

            /*
             * HTTP Header 설정.
             */
            response.setContentType("application/vnd.ms-excel; name=\"" + realExcelFilename + ".xlsx\"");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + realExcelFilename + ".xlsx\"");
            response.setHeader("Content-Transfer-Encoding", "binary");
//            response.setHeader("Content-Length", Long.toString(fileDownLoadInputVO.getlFileSize()));
            response.setHeader("Cache-Control", "no-cahe, no-store, must-revalidate\r\n");
            response.setHeader("Connection", "close");

            //FileOutputStream fileOut = new FileOutputStream(realExcelFilename + ".xlsx");
            //OutputStream out = response.getOutputStream();
            OutputStream fileOut = response.getOutputStream();

            workbook.write(fileOut);
            fileOut.close();

        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
    }

}
