/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 발주정보 service를 이용한 데이타 출력 컨트롤러 
 * 소  스  명 : DeliveryDocDAO
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
import com.woojin.commercial.supply.SupplyVO;
import com.woojin.commercial.util.ExcelStyleBuilder;

@Controller
public class DeliveryDocController {
	Logger log = LoggerFactory.getLogger(this.getClass());

    //상용자정보 서비스 클래스 호출
    @Autowired
    DeliveryDocService DeliveryDocService;

    @Autowired
    DocumentService documentService;

    /* *******************************************************************************************
     * 함수  제목 : 
     * 작  성  자 :       		작  성  일 : 
     * 내      용 : 
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/web/DeliveryDoc", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listDeliveryDoc(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO != null) {
	            if (userVO.getAuth_cd().equals("WEB")) {
	                commandMap.put("company_cd",userVO.getCompany_cd().toString());
	                if (commandMap.get("process_cd") == null) commandMap.put("process_cd","ST_BOS");
	
	                Map<String, Object> resultMap = DeliveryDocService.listDeliveryDoc(commandMap);
	
	                //jsp 에서 보여줄 정보 추출
	                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
	                mv.addObject("infoParam", userVO); //변수값
	                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
	                mv.addObject("commonList", resultMap.get("commonList")); //검색
	                mv.addObject("DeliveryDocList", resultMap.get("DeliveryDocList")); //검색
	                mv.addObject("materialList", resultMap.get("materialList")); //검색
	                mv.addObject("placeList", resultMap.get("placeList")); //검색
	
	                mv.setViewName("/web/DeliveryDoc");
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
     * 함수  제목 : 
     * 작  성  자 :       		작  성  일 : 
     * 내      용 : 
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unused")
	@RequestMapping(value="/web/insertDeliveryDoc", method = {RequestMethod.GET, RequestMethod.POST})
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
                Map<String, Object> msgMap = DeliveryDocService.insertDeliveryDoc(commandMap);
                
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
     * 함수  제목 : 
     * 작  성  자 :       		작  성  일 : 
     * 내      용 : 
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unused")
	@RequestMapping(value="/web/updateDeliveryDoc", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateDeliveryDoc(HttpSession httpSession,
                                           CommandMap commandMap) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("WEB")) {
                Map<String, Object> msgMap = DeliveryDocService.updateDeliveryDoc(commandMap);

                
                resultMap.put("DOC_NUM", commandMap.get("DOC_NUM"));
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
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings({ "unused", "unchecked" })
	@ResponseBody
    @RequestMapping(value = "/web/resheetExcel1", method = {RequestMethod.GET, RequestMethod.POST})
    public void listSupplyResheetExcel(CommandMap commandMap, HttpSession httpSession, HttpServletResponse response) throws Exception{
    	ModelAndView mv = new ModelAndView();
    	FileOutputStream outStream = null;
        InputStream fis = null;

        BufferedInputStream bisPdf = null;
        BufferedOutputStream bosPdf = null;


        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("WEB")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
            
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
                Calendar c1 = Calendar.getInstance();
                String strToday = sdf.format(c1.getTime());
                String strPublish = sdf1.format(c1.getTime());
                String docNum = "";
                //String place_nm = commandMap.get("place_nm").toString();
                String place_nm = "회사이름";
                
                
                    fis = getClass().getClassLoader().getResourceAsStream("document/Deliverydoc.xls");

                    HSSFWorkbook workbook = new HSSFWorkbook(fis);
                    Map<String, HSSFCellStyle> styles = new ExcelStyleBuilder().createExcelStylesHSS(workbook);

                    HSSFSheet sheet = workbook.getSheetAt(0);

                    
                    
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

                    String sGetCurrentTime = commandMap.get("order_dt_end").toString();
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
                    //           response.setHeader("Content-Length", Long.toString(fileDownLoadInputVO.getlFileSize()));
                    response.setHeader("Cache-Control", "no-cahe, no-store, must-revalidate\r\n");
                    response.setHeader("Connection", "close");

                    //FileOutputStream fileOut = new FileOutputStream(realExcelFilename + ".xlsx");
                    //OutputStream out = response.getOutputStream();
                    byte buffer[] = new byte[256];
                    bisPdf = new BufferedInputStream(new FileInputStream(new File(pdfpath)));
                    bosPdf = new BufferedOutputStream(response.getOutputStream());
                    
                    while (bisPdf.read(buffer) != -1) 
                    	bosPdf.write(buffer);
                    
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
}



 