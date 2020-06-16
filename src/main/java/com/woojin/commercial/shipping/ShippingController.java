/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 출하정보 service를 이용한 데이타 출력 컨트롤러 
 * 소  스  명 : ShippingDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.shipping;

import com.woojin.commercial.common.SearchVO;
import com.woojin.commercial.login.LoginVO;
import com.woojin.commercial.util.ExcelBuilder;
import com.woojin.commercial.util.StringUtil;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.woojin.commercial.common.CommandMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ShippingController {
    Logger log = Logger.getLogger(this.getClass());

    //상용자정보 서비스 클래스 호출
    @Autowired
    ShippingService shippingService;

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/shipping", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listShipping(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO != null) {
	            if (userVO.getAuth_cd().equals("SHIPPING")) {
	                commandMap.put("company_cd",userVO.getCompany_cd().toString());
	                commandMap.put("role","SHIPPING");
	                commandMap.put("common_cd","ST_CFM");
	                Map<String, Object> resultMap = shippingService.listShipping(commandMap);
	
	                //jsp 에서 보여줄 정보 추출
	                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
	                mv.addObject("infoParam", userVO); //변수값
	                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
	                mv.addObject("commonList", resultMap.get("commonList")); //검색
	                mv.addObject("packingList", resultMap.get("packingList")); //검색
	                mv.addObject("materialList", resultMap.get("materialList")); //검색
	                mv.addObject("companyList", resultMap.get("companyList")); //검색
	                mv.addObject("shippingList", resultMap.get("shippingList")); //검색
	                mv.setViewName("/shipping/shipping");
	            }
	            else{
	                mv.setViewName("/login/denied");
	            }
	            mv.addObject("userRole", userVO.getAuth_cd());//변수값
	            mv.addObject("pagecng", "CFMLIST");//변수값
            }
            else {
            	mv.setViewName("/login/login");
            }
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/shipping/detailShipping", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> detailShipping(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> cngMap = new HashMap<String,Object>();
        try {
            Map<String, Object> msgMap = shippingService.detailShipping(commandMap);
            cngMap = StringUtil.convertObjectToMap(msgMap.get("shippingDetail"));

            JSONObject pageParam = StringUtil.getJsonStringFromMap(cngMap);
            resultMap.put("status", "0");
            resultMap.put("msg", "데이타를 정상적으로 호출하였습니다");
            resultMap.put("shippingDetail", pageParam);
        }
        catch(Exception e){
            log.error(e);
            resultMap.put("status", "1");
            resultMap.put("msg", "데이타 호출에 실패하였습니다");
            //throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정폼
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 내용을 수정폼에 뿌려준다
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/shipping/formShipping", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView formShipping(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/shipping/shippingForm");
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap = shippingService.detailShipping(commandMap);
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("shippingDetail", resultMap.get("shippingDetail")); //검색
        }
        catch(Exception e){
            log.error(e);
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
    @RequestMapping(value="/shipping/processShipping", method = {RequestMethod.GET, RequestMethod.POST})
    public String processShipping(SearchVO searchVO, HttpSession httpSession,
                                CommandMap commandMap, RedirectAttributes rttr) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        Calendar cal = Calendar.getInstance();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            if (object != null) {
                LoginVO userVO = (LoginVO) object;
                commandMap.put("modify_id", userVO.getUser_id());
            }
            else{
                commandMap.put("modify_id", "");
            }

            if (commandMap.get("processFlag").toString().equals("insert")) {
                cal.set(Calendar.DATE, 1);
                String firstDate = formatter.format(cal.getTime());
                cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                String lastDate = formatter.format(cal.getTime());

                Calendar time = Calendar.getInstance();
                String accept_dt = formatter.format(time.getTime());
                commandMap.put("order_dt", accept_dt);

                Map<String, Object> msgMap = shippingService.insertShipping(commandMap);

                rttr.addFlashAttribute("resultFlag", "registerOK");
                rttr.addFlashAttribute("nCurrpage", 1);
                rttr.addFlashAttribute("pagemode", "list");
                rttr.addFlashAttribute("order_dt_start", firstDate);
                rttr.addFlashAttribute("order_dt_end", lastDate);
                rttr.addFlashAttribute("common_cd", "");
                rttr.addFlashAttribute("schword", "");
            }
            else if (commandMap.get("processFlag").toString().equals("update")) {
                Map<String, Object> msgMap = shippingService.updateShipping(commandMap);
                rttr.addFlashAttribute("resultFlag", "modifyOK");
                rttr.addFlashAttribute("nCurrpage", searchVO.getNCurrpage());
                rttr.addFlashAttribute("pagemode", searchVO.getPagemode());
                rttr.addFlashAttribute("order_dt_start", searchVO.getOrder_dt_start());
                rttr.addFlashAttribute("order_dt_end", searchVO.getOrder_dt_end());
                rttr.addFlashAttribute("common_cd", searchVO.getCommon_cd());
                rttr.addFlashAttribute("schword", searchVO.getSchword());
            }
            else if (commandMap.get("processFlag").toString().equals("delete")) {
                Map<String, Object> msgMap = shippingService.deleteShipping(commandMap);
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
            log.error(e);
            throw e;
        }
        return "redirect:/shipping";
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 등록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일 내용 등록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/shipping/insertShipping", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> insertShipping(HttpSession httpSession,
                               CommandMap commandMap) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            if (object != null) {
                LoginVO userVO = (LoginVO) object;
                commandMap.put("process_od", userVO.getUser_id());
            }
            else{
                commandMap.put("process_od", "");
            }

            Map<String, Object> msgMap = shippingService.insertShipping(commandMap);

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
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/shipping/updateShipping", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateShipping(HttpSession httpSession,
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
            Map<String, Object> msgMap = shippingService.updateShipping(commandMap);

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
     * 함수  제목 : 출하정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 완전삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/shipping/deleteShipping", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> deleteShipping(HttpServletRequest request,
                                              HttpServletResponse response,
                                              HttpSession httpSession,
                                              CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> msgMap = shippingService.deleteShipping(commandMap);

        resultMap.put("status", msgMap.get("status"));
        resultMap.put("pageParam", msgMap.get("pageParam"));
        resultMap.put("msg", msgMap.get("msg"));

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping(value = "/shipping/shippingexcel", method = {RequestMethod.GET, RequestMethod.POST})
    public void shippingExcel(CommandMap commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            String excelTitle = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());


            Map<String, Object> tempMap = new HashMap<String, Object>();

            Map<String, Object> titleStyleMap = new HashMap<String, Object>();
            List<Map<String, Object>> fieldInfoList = new ArrayList<Map<String, Object>>();
            Map<String, Object> excelInfpMap = new HashMap<String, Object>();

            commandMap.put("role","SHIPPING");
            commandMap.put("common_cd","ST_CFM");
            Map<String, Object> resultMap = shippingService.listShipping(commandMap);
            List<Object> objResult = (List<Object>) resultMap.get("shippingList");

            excelTitle = "출하확정정보";

            //첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
            Map<String, Object> headerMap = new HashMap<String, Object>();

            headerMap.put("sRow", "1");
            headerMap.put("eRow", "1");
            headerMap.put("sCol", "0");
            headerMap.put("eCol", "10");
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
            titleStyleMap.put("eCol", "10");
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
            tempMap.put("field", "supply_dt");
            tempMap.put("cellTitle", "출하일");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "company_nm");
            tempMap.put("cellTitle", "업체명");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 20*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "material_num");
            tempMap.put("cellTitle", "자재코드");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 16*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "mtart_nm");
            tempMap.put("cellTitle", "자재유형");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "bstrf");
            tempMap.put("cellTitle", "포장단위");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "supply_place");
            tempMap.put("cellTitle", "납품처");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "supply_qty");
            tempMap.put("cellTitle", "출하수량");
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
            tempMap.put("field", "packing_nm");
            tempMap.put("cellTitle", "포장방법");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 23*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "shipping_method");
            tempMap.put("cellTitle", "출하방법");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "packing_flag_nm");
            tempMap.put("cellTitle", "포장여부");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
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

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping(value = "/shipping/placeexcel", method = {RequestMethod.GET, RequestMethod.POST})
    public void placeExcel(CommandMap commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception{

        try {
            String excelTitle = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());


            Map<String, Object> tempMap = new HashMap<String, Object>();

            Map<String, Object> titleStyleMap = new HashMap<String, Object>();
            List<Map<String, Object>> fieldInfoList = new ArrayList<Map<String, Object>>();
            Map<String, Object> excelInfpMap = new HashMap<String, Object>();

            commandMap.put("role","SHIPPING");
            commandMap.put("common_cd","ST_CFM");
            Map<String, Object> resultMap = shippingService.listShippingCfmAddr(commandMap);
            List<Object> objResult = (List<Object>) resultMap.get("placeList");

            excelTitle = "납품처정보";

            //첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
            Map<String, Object> headerMap = new HashMap<String, Object>();

            headerMap.put("sRow", "1");
            headerMap.put("eRow", "1");
            headerMap.put("sCol", "0");
            headerMap.put("eCol", "3");
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
            titleStyleMap.put("eCol", "3");
            titleStyleMap.put("fontType", "subtitle");
            titleStyleMap.put("fontColor", "000000");
            titleStyleMap.put("styleColor", "ECF5FC");
            titleStyleMap.put("textAlign", "center");
            titleStyleMap.put("textVAlign", "center");
            titleStyleMap.put("line", "line");

            fieldInfoList = new ArrayList<Map<String, Object>>();

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "supply_dt");
            tempMap.put("cellTitle", "출하일");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "company_nm");
            tempMap.put("cellTitle", "업체명");
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
            tempMap.put("field", "supply_place");
            tempMap.put("cellTitle", "납품처");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 16*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "left");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "place_addr");
            tempMap.put("cellTitle", "납품처 주소");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 30*256);
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
    
    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/shipping/updateShipping_method", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateShipping_method(HttpSession httpSession,
                                  CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Map<String, Object> msgMap = shippingService.updateShipping_method(commandMap);

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
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/shipping/psvlist", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView psvlist(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SHIPPING")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                commandMap.put("role","ADMIN");
                commandMap.put("common_cd","ST_CFM");
                Map<String, Object> resultMap = shippingService.listShippingPsv(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("infoParam", userVO); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("commonList", resultMap.get("commonList")); //검색
                mv.addObject("packingList", resultMap.get("packingList")); //검색
                mv.addObject("materialList", resultMap.get("materialList")); //검색
                mv.addObject("companyList", resultMap.get("companyList")); //검색
                mv.addObject("shippingList", resultMap.get("shippingList")); //검색
                mv.setViewName("/admin/admin_sapshipping");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
            mv.addObject("pagecng", "CFMLIST");//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }
    
    
    
}
