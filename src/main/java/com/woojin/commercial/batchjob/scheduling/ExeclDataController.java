package com.woojin.commercial.batchjob.scheduling;

import com.woojin.commercial.util.ExcelBuilder;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ExeclDataController {
    @Autowired
    ExcelDataService excelDataService;

    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping(value = "/admin/exceldata", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<Object, Object> autoExcelMake(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        try {

            String excelTitle = "";

            Map<String, Object> tempMap = new HashMap<String, Object>();

            Map<String, Object> titleStyleMap = new HashMap<String, Object>();
            List<Map<String, Object>> fieldInfoList = new ArrayList<Map<String, Object>>();
            Map<String, Object> excelInfpMap = new HashMap<String, Object>();

            SXSSFWorkbook workbook = new SXSSFWorkbook();

            Map<String, Object> excelSumMap = excelDataService.listExcelDataSum();
            //List<ExcelDataVO> lstResultSum = (List<ExcelDataVO>) excelSumMap.get("listExcelDataSum");
            List<Object> objResultSum = (List<Object>) excelSumMap.get("listExcelDataSum");

            excelTitle = "INFO";

            titleStyleMap.put("sRow", "0");
            titleStyleMap.put("eRow", "0");
            titleStyleMap.put("sCol", "0");
            titleStyleMap.put("eCol", "12");
            titleStyleMap.put("fontType", "subtitle");
            titleStyleMap.put("fontColor", "000000");
            titleStyleMap.put("styleColor", "ECF5FC");
            titleStyleMap.put("textAlign", "center");
            titleStyleMap.put("textVAlign", "center");
            titleStyleMap.put("line", "line");

            fieldInfoList = new ArrayList<Map<String, Object>>();

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "company_cd");
            tempMap.put("cellTitle", "업체코드");
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
            tempMap.put("field", "mtart");
            tempMap.put("cellTitle", "자재유형");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 15*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "prdgrp_nm");
            tempMap.put("cellTitle", "아이템유형");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 15*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "ordertype");
            tempMap.put("cellTitle", "오더유형");
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
            tempMap.put("field", "ordercorp");
            tempMap.put("cellTitle", "판매조직");
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
            tempMap.put("field", "orderpath");
            tempMap.put("cellTitle", "유통경로");
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
            tempMap.put("field", "ordergroup");
            tempMap.put("cellTitle", "제품군");
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
            tempMap.put("field", "srow");
            tempMap.put("cellTitle", "시작행");
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
            tempMap.put("field", "cnt");
            tempMap.put("cellTitle", "품목갯수");
            tempMap.put("fileType", "Int");
            tempMap.put("cellWidth", 23*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "right");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "supply_dt");
            tempMap.put("cellTitle", "출하일자");
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
            tempMap.put("field", "ppno");
            tempMap.put("cellTitle", "PO번호");
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
            tempMap.put("field", "docnum");
            tempMap.put("cellTitle", "납품문서번호");
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
            tempMap.put("field", "supply_num");
            tempMap.put("cellTitle", "출하지시번호");
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

            excelInfpMap = new HashMap<String, Object>();
            excelInfpMap.put("titleStyleMap", titleStyleMap);
            excelInfpMap.put("fieldInfoList", fieldInfoList);

            ExcelBuilder.buildExcelXSSSheet(workbook, excelTitle, excelInfpMap, objResultSum, false);

            Map<String, Object> excelMap = excelDataService.listExcelData();

            //List<ExcelDataVO> lstResult = (List<ExcelDataVO>) excelMap.get("listExcelData");
            List<Object> objResult = (List<Object>) excelMap.get("listExcelData");

            excelTitle = "DATA";
            titleStyleMap.put("sRow", "0");
            titleStyleMap.put("eRow", "0");
            titleStyleMap.put("sCol", "0");
            titleStyleMap.put("eCol", "9");
            titleStyleMap.put("fontType", "subtitle");
            titleStyleMap.put("fontColor", "000000");
            titleStyleMap.put("styleColor", "ECF5FC");
            titleStyleMap.put("textAlign", "center");
            titleStyleMap.put("textVAlign", "center");
            titleStyleMap.put("line", "line");

            fieldInfoList = new ArrayList<Map<String, Object>>();
            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "company_cd");
            tempMap.put("cellTitle", "업체코드");
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
            tempMap.put("field", "mtart");
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
            tempMap.put("field", "prdgrp_nm");
            tempMap.put("cellTitle", "아이템유형");
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
            tempMap.put("field", "material_num");
            tempMap.put("cellTitle", "자재코드");
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
            tempMap.put("cellTitle", "수량");
            tempMap.put("fileType", "int");
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
            tempMap.put("field", "supply_place");
            tempMap.put("cellTitle", "출하지점");
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
            tempMap.put("field", "supply_dt");
            tempMap.put("cellTitle", "출하일자");
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
            tempMap.put("field", "");
            tempMap.put("cellTitle", "");
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
            tempMap.put("cellWidth", 25*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "left");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);
            
            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "place_nm");
            tempMap.put("cellTitle", "납품처");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 25*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "left");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);            
            excelInfpMap = new HashMap<String, Object>();
            excelInfpMap.put("titleStyleMap", titleStyleMap);
            excelInfpMap.put("fieldInfoList", fieldInfoList);

            ExcelBuilder.buildExcelXSSSheet(workbook, excelTitle, excelInfpMap, objResult, false);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());

            String realExcelFilename = null;
            realExcelFilename = URLEncoder.encode("commercial_" + strToday, "UTF-8");
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

        } catch (Exception e) {
            e.printStackTrace();
        }


        return returnMap;
    }
}
