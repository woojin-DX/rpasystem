/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 발주정보 service를 이용한 데이타 출력 컨트롤러 
 * 소  스  명 : SupplyDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.supply;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.woojin.commercial.common.SearchVO;
import com.woojin.commercial.login.LoginVO;
import com.woojin.commercial.util.ExcelStyleBuilder;
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
import com.woojin.commercial.common.CommandMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class SupplyController {
	Logger log = LoggerFactory.getLogger(this.getClass());

    //상용자정보 서비스 클래스 호출
    @Autowired
    SupplyService supplyService;

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
    @RequestMapping(value = "/supply", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listSupply(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO != null) {
	            if (userVO.getAuth_cd().equals("SUPPLY")) {
	                commandMap.put("company_cd",userVO.getCompany_cd().toString());
	                if (commandMap.get("process_cd") == null) commandMap.put("process_cd","ST_BOS");
	
	                Map<String, Object> resultMap = supplyService.listSupply(commandMap);
	
	                //jsp 에서 보여줄 정보 추출
	                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
	                mv.addObject("infoParam", userVO); //변수값
	                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
	                mv.addObject("commonList", resultMap.get("commonList")); //검색
	                mv.addObject("supplyList", resultMap.get("supplyList")); //검색
	                mv.addObject("materialList", resultMap.get("materialList")); //검색
	                mv.addObject("placeList", resultMap.get("placeList")); //검색
	
	                mv.setViewName("/supply/supply");
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
    @RequestMapping(value = "/supply/confirm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listSupplyConfirm(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SUPPLY")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                commandMap.put("process_cd","ST_SPG");

                Map<String, Object> resultMap = supplyService.listSupplyConfirm(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("supplyList", resultMap.get("supplyList")); //검색
                mv.addObject("materialList", resultMap.get("materialList")); //검색

                mv.setViewName("/supply/confirm");
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
	@RequestMapping(value = "/supply/resheet", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listSupplyResheet(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();
        FileOutputStream outStream = null;
        InputStream fis = null;

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SUPPLY")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                commandMap.put("process_cd","ST_SPG");

                Map<String, Object> resultMap = supplyService.listSupplyResheet(commandMap);
                List<SupplyVO> listParam = (List<SupplyVO>) resultMap.get("supplyList");

                //jsp 에서 보여줄 정보 추출
                mv.addObject("supplyList", resultMap.get("supplyList")); //검색
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값

                mv.setViewName("/supply/resheet");
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
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings({ "unused", "unchecked" })
	@ResponseBody
    @RequestMapping(value = "/supply/resheetExcel", method = {RequestMethod.GET, RequestMethod.POST})
    public void listSupplyResheetExcel(CommandMap commandMap, HttpSession httpSession, HttpServletResponse response) throws Exception{
        FileOutputStream outStream = null;
        InputStream fis = null;

        BufferedInputStream bisPdf = null;
        BufferedOutputStream bosPdf = null;


        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SUPPLY")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                commandMap.put("process_cd","ST_SPG");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
                Calendar c1 = Calendar.getInstance();
                String strToday = sdf.format(c1.getTime());
                String strPublish = sdf1.format(c1.getTime());
                String docNum = "";
                String place_nm = commandMap.get("place_nm").toString();

                Map<String, Object> docMap = documentService.overlabListDocument(commandMap);
                List<DocumentVO> docListParam = (List<DocumentVO>) docMap.get("documentList");
                if (docListParam.size() > 0){
                    docNum = docListParam.get(0).getDoc_num();
                    strPublish = docListParam.get(0).getPublish_dt();
                }
                else{
                    Map<String, Object> docMapIn = documentService.insertDocument(commandMap);
                    strPublish = commandMap.get("confirm_dt").toString();
                    docNum = docMapIn.get("new_cd").toString();
                }

                if (!docNum.equals("")) {
                    Map<String, Object> resultMap = supplyService.listSupplyResheetDetail(commandMap);
                    List<SupplyVO> listParam = (List<SupplyVO>) resultMap.get("supplyList");

                    fis = getClass().getClassLoader().getResourceAsStream("document/specific.xls");

                    HSSFWorkbook workbook = new HSSFWorkbook(fis);
                    Map<String, HSSFCellStyle> styles = new ExcelStyleBuilder().createExcelStylesHSS(workbook);

                    HSSFSheet sheet = workbook.getSheetAt(0);

                    sheet.getRow(4).getCell(2).setCellValue(docNum);
                    sheet.getRow(4).getCell(9).setCellValue(listParam.get(0).getPlace_nm());
                    sheet.getRow(4).getCell(20).setCellValue("발행일 : " + strPublish);

                    sheet.getRow(5).getCell(16).setCellValue(listParam.get(0).getRegster_no());
                    sheet.getRow(6).getCell(16).setCellValue(listParam.get(0).getCompany_nm());
                    sheet.getRow(7).getCell(16).setCellValue(listParam.get(0).getJ_1kfrepre());
                    sheet.getRow(8).getCell(16).setCellValue(listParam.get(0).getCompany_addr());
                    sheet.getRow(9).getCell(16).setCellValue(listParam.get(0).getJ_uptae());
                    sheet.getRow(9).getCell(19).setCellValue(listParam.get(0).getJ_jongmok());

                    long nPrice = 0;
                    long nTax = 0;
                    long nTotPrice = 0;
                    if (listParam.size() > 0) {
                        for (int i = 0; i < listParam.size(); i++) {
                            sheet.getRow(11 + i).getCell(1).setCellValue(i + 1);
                            sheet.getRow(11 + i).getCell(2).setCellValue(listParam.get(i).getMaterial_num());
                            sheet.getRow(11 + i).getCell(9).setCellValue(listParam.get(i).getSupply_qty());
                            sheet.getRow(11 + i).getCell(12).setCellValue(listParam.get(i).getUnit_price());
                            sheet.getRow(11 + i).getCell(15).setCellValue("EA");
                            sheet.getRow(11 + i).getCell(17).setCellValue(listParam.get(i).getTotal_price());
                            sheet.getRow(11 + i).getCell(20).setCellValue(listParam.get(i).getTax_price());
                            nPrice += listParam.get(i).getTotal_price();
                            nTax += listParam.get(i).getTax_price();
                            nTotPrice += (listParam.get(i).getTotal_price() + listParam.get(i).getTax_price());
                        }
                    }
                    sheet.getRow(40).getCell(3).setCellValue(nPrice);
                    sheet.getRow(40).getCell(10).setCellValue(nTax);
                    sheet.getRow(40).getCell(18).setCellValue(nTotPrice);

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    workbook.write(bos);
                    byte[] bytes = bos.toByteArray();

                    ByteArrayInputStream targetStream = null;
                    OutputStream out = null;
                    targetStream = new ByteArrayInputStream(bytes);

                    DocumentFormat format = new DefaultDocumentFormatRegistry().getFormatByFileExtension("xls");
                    DocumentFormat customPdfFormat = new DefaultDocumentFormatRegistry().getFormatByFileExtension("pdf");

                    SocketOpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);

                    connection.connect();

                    String sGetCurrentTime = commandMap.get("supply_dt").toString();
                    sGetCurrentTime = sGetCurrentTime.substring(0, 4) + sGetCurrentTime.substring(5, 7);
                    String company_cd = commandMap.get("company_cd").toString();

                    String remoteFilePath = "";

                    String filePath = "D:/homepage/web/fileupload/market/";

                    remoteFilePath = filePath + company_cd + "/";

                    // 디렉토리 경로
                    File desti = new File(remoteFilePath);
                    // 디렉토리 생성
                    if (desti.exists() == false) {
                        desti.mkdirs();
                    }

                    String pdfFileName = "거래명세서(" + place_nm + ")_" + strToday + ".pdf";
                    String pdfpath = remoteFilePath + pdfFileName;
                    out = new FileOutputStream(pdfpath);

                    // convert
                    DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
                    //converter.convert(inputFile, outputFile);
                    converter.convert(targetStream, format, out, customPdfFormat);
                    // close the connection
                    connection.disconnect();
                    out.close();
                    targetStream.close();
                    bos.close();

                    CommandMap upCommandMap = new CommandMap();
                    upCommandMap.put("doc_num",docNum);
                    upCommandMap.put("file_name",pdfFileName);
                    upCommandMap.put("file_path",remoteFilePath);

                    Map<String, Object> docMapUp = documentService.updateDocument(upCommandMap);

                    String realExcelFilename = null;
                    realExcelFilename = URLEncoder.encode("거래명세서(" + place_nm + ")_" + strPublish.replace(".",""), "UTF-8");
                    realExcelFilename = realExcelFilename.replaceAll("\\+", " ");

                    /*
                     * HTTP Header 설정.
                     */
                    response.setContentType("application/pdf; name=\"" + realExcelFilename + ".pdf\"");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + realExcelFilename + ".pdf\"");
                    response.setHeader("Content-Transfer-Encoding", "binary");
//            response.setHeader("Content-Length", Long.toString(fileDownLoadInputVO.getlFileSize()));
                    response.setHeader("Cache-Control", "no-cahe, no-store, must-revalidate\r\n");
                    response.setHeader("Connection", "close");

                    //FileOutputStream fileOut = new FileOutputStream(realExcelFilename + ".xlsx");
                    //OutputStream out = response.getOutputStream();
                    byte buffer[] = new byte[256];
                    bisPdf = new BufferedInputStream(new FileInputStream(new File(pdfpath)));
                    bosPdf = new BufferedOutputStream(response.getOutputStream());

                    while (bisPdf.read(buffer) != -1) bosPdf.write(buffer);
                }
                //fileOut.close();


            }

        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        finally {
            if(bisPdf != null) bisPdf.close();

            if(bosPdf != null) bosPdf.close();

            if (fis != null){
                try{
                    fis.close(); // Stream 닫기
                }
                catch(IOException ex){
                    log.info("IO Exception2 : " + ex.getMessage());
                }
            }
            if (outStream != null){
                try{
                    outStream.close(); // Stream 닫기
                }
                catch(IOException ex){
                    log.info("IO Exception2 : " + ex.getMessage());
                }
            }

        }
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/supply/detailSupply", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> detailSupply(CommandMap commandMap, HttpSession httpSession) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SUPPLY")) {
                resultMap = supplyService.detailSupply(commandMap);
                resultMap.put("supplyDetail", resultMap.get("supplyDetail")); //내용
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
    @RequestMapping(value="/supply/formSupply", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView formSupply(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("/supply/supplyForm");
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap = supplyService.detailSupply(commandMap);
            mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
            mv.addObject("supplyDetail", resultMap.get("supplyDetail")); //검색
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
	@RequestMapping(value="/supply/processSupply", method = {RequestMethod.GET, RequestMethod.POST})
    public String processSupply(SearchVO searchVO, HttpSession httpSession,
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

                msgMap = supplyService.insertSupply(commandMap);

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
                msgMap = supplyService.updateSupply(commandMap);
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
                msgMap = supplyService.updateSupplyRecive(commandMap);
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
                msgMap = supplyService.deleteSupply(commandMap);
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
        return "redirect:/supply";
    }

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 등록
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
     * 내      용 : 단일 내용 등록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unused")
	@RequestMapping(value="/supply/insertSupply", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> insertSupply(HttpSession httpSession,
                                           CommandMap commandMap) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SUPPLY")) {
                commandMap.put("modify_id", userVO.getUser_id());
                Calendar mon = Calendar.getInstance();
                mon.add(Calendar.MONTH , -1);
                String firstDate = formatter.format(mon.getTime());

                Calendar time = Calendar.getInstance();
                String lastDate = formatter.format(time.getTime());
                String order_dt = formatter.format(time.getTime());

                commandMap.put("process_cd", "ST_BOS");
                commandMap.put("order_dt", order_dt);
                Map<String, Object> msgMap = supplyService.insertSupply(commandMap);

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
    @RequestMapping(value="/supply/updateSupply", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateSupply(HttpSession httpSession,
                                  CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SUPPLY")) {
                commandMap.put("modify_id", userVO.getUser_id());
                Map<String, Object> msgMap = supplyService.updateSupply(commandMap);

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
    @RequestMapping(value="/supply/updateSupplyRecive", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateSupplyRecive(HttpSession httpSession,
                                            CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SUPPLY")) {
                Map<String, Object> msgMap = supplyService.updateSupplyRecive(commandMap);

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
    @RequestMapping(value="/supply/deleteSupply", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> deleteSupply(HttpServletRequest request,
                                              HttpServletResponse response,
                                              HttpSession httpSession,
                                              CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SUPPLY")) {
                Map<String, Object> msgMap = supplyService.deleteSupply(commandMap);

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
    @RequestMapping(value="/supply/detailMaterialNum", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> detailMaterialNum(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        try {
            Calendar time = Calendar.getInstance();
            String order_dt = formatter.format(time.getTime());

            commandMap.put("order_dt", order_dt);
            resultMap = supplyService.detailMaterialNum(commandMap);

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
    @RequestMapping(value="/supply/overlabSupply", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> overlabSupply(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        try {
            Calendar time = Calendar.getInstance();
            String order_dt = formatter.format(time.getTime());

            commandMap.put("order_dt", order_dt);
            int overlab = supplyService.overlabCountSupply(commandMap);
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
    @RequestMapping(value="/supply/supplyHistory", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> listSupplyHistory(CommandMap commandMap, HttpSession httpSession) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("SUPPLY")) {
                resultMap = supplyService.listSupplyHistory(commandMap);
                resultMap.put("supplyList", resultMap.get("supplyList")); //내용
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
