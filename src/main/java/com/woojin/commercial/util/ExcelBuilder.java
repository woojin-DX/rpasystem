/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : ExcelBuilder
 *   @Description   :
 *   @Author        : GACHINOEL
 *   @Version       : v1.0
 *   Copyright(c) 2019 WOOJIN All rights reserved
 *   ------------------------------------------------------------------------------
 *                    변         경         사         항
 *   ------------------------------------------------------------------------------
 *      DATE           AUTHOR                       DESCRIPTION
 *   ---------------  ----------    ------------------------------------------------
 *   2019.10.28       gachinoel     신규생성
 *   ------------------------------------------------------------------------------
 */

package com.woojin.commercial.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelBuilder {

    /**
     * Excel File을 생성하여 Workbook을 반환.
     *
     * sheetTitle Sheet 제목
     * excelCellInfos Cell의 생성 정보
     * rows 실제 Data
     * XSSFWorkbook
     */
    @SuppressWarnings("unchecked")
    public SXSSFWorkbook buildExcelMergeXSS(String sheetTitle, Map<String, Object> excelInfpMap
            , List<Object> result, List<Object> result1, long nTotla, int nMon)
            throws Exception {

        SXSSFWorkbook workbook = new SXSSFWorkbook();

        ExcelStyleBuilder stylesXss = new ExcelStyleBuilder();

        Sheet sheet = workbook.createSheet(sheetTitle);

        sheet.setDisplayGridlines(false);

        PrintSetup print = sheet.getPrintSetup();
        //인쇄 용지를 A4로 설정
        print.setPaperSize(PrintSetup.A4_PAPERSIZE);

        //인쇄 방향을 가로로 설정 true면 가로 false면 세로(default)
        print.setLandscape(false);

        //인쇄시 확대/축소 배율
        print.setScale( (short)80 );
        print.setUsePage(true);
        print.setPageStart((short)1);

        Row row = null;
        Cell cell = null;

        //첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
        Map<String, Object> headerMap = (Map<String, Object>) excelInfpMap.get("headerMap");
        Map<String, Object> titleStyleMap = (Map<String, Object>) excelInfpMap.get("titleStyleMap");
        List<Map<String, Object>> titleCellList = (List<Map<String, Object>>) excelInfpMap.get("titleCellList");
        List<Map<String, Object>> fieldInfoList = (List<Map<String, Object>>) excelInfpMap.get("fieldInfoList");
        List<Map<String, Object>> fieldStyleList = (List<Map<String, Object>>) excelInfpMap.get("fieldStyleList");

        int nRow = 1;

        if (headerMap != null){
            for (int i = Integer.parseInt(headerMap.get("sRow").toString()); i <= Integer.parseInt(headerMap.get("eRow").toString()); i++) {
                row = sheet.createRow(i);
                for (int j = Integer.parseInt(headerMap.get("sCol").toString()); j <= Integer.parseInt(headerMap.get("eCol").toString()); j++) {
                    cell = row.createCell(j);
                    CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                            , headerMap.get("fontType").toString(), headerMap.get("fontColor").toString(), headerMap.get("styleColor").toString()
                            , headerMap.get("textAlign").toString(), headerMap.get("textVAlign").toString()
                            , headerMap.get("line").toString(),"");
                    cell.setCellStyle(titleStyle);
                }

                int nRow1 = Integer.parseInt(headerMap.get("sRow").toString());
                int nRow2 = Integer.parseInt(headerMap.get("eRow").toString());
                int nCol1 = Integer.parseInt(headerMap.get("sCol").toString());
                int nCol2 = Integer.parseInt(headerMap.get("eCol").toString());

                row = sheet.getRow(nRow1);

                cell = row.getCell(nCol1);

                cell.setCellValue(headerMap.get("title").toString());

                if ((nRow1 != nRow2) || (nCol1 != nCol2)){
                    sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
                }
                //sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
                //sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
                row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
                row.setHeight((short)600);
            }
            nRow = Integer.parseInt(headerMap.get("eRow").toString());

        }

        if (titleStyleMap != null){
            for (int i = Integer.parseInt(titleStyleMap.get("sRow").toString()); i <= Integer.parseInt(titleStyleMap.get("eRow").toString()); i++) {
                row = sheet.createRow(i);
                for (int j = Integer.parseInt(titleStyleMap.get("sCol").toString()); j <= Integer.parseInt(titleStyleMap.get("eCol").toString()); j++) {
                    cell = row.createCell(j);
                    CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                            , titleStyleMap.get("fontType").toString(), titleStyleMap.get("fontColor").toString(), titleStyleMap.get("styleColor").toString()
                            , titleStyleMap.get("textAlign").toString(), titleStyleMap.get("textVAlign").toString()
                            , titleStyleMap.get("line").toString(),"");
                    cell.setCellStyle(titleStyle);
                }
            }

            if (titleCellList != null){
                for(int n = 0; n < titleCellList.size(); n++) {
                    int nRow1 = Integer.parseInt(titleCellList.get(n).get("sRow").toString());
                    int nRow2 = Integer.parseInt(titleCellList.get(n).get("eRow").toString());
                    int nCol1 = Integer.parseInt(titleCellList.get(n).get("sCol").toString());
                    int nCol2 = Integer.parseInt(titleCellList.get(n).get("eCol").toString());

                    row = sheet.getRow(nRow1);

                    cell = row.getCell(nCol1);

                    cell.setCellValue(titleCellList.get(n).get("cellTitle").toString());

                    if ((nRow1 != nRow2) || (nCol1 != nCol2)){
                        sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
                    }
                    //sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
                    //sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
                    row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
                    row.setHeight((short)300);
                }
            }

            nRow = Integer.parseInt(titleStyleMap.get("eRow").toString());

        }

        if (titleStyleMap != null){

            nRow = Integer.parseInt(titleStyleMap.get("firstRow").toString());

            String[] tempField = new String[fieldInfoList.size()];
            for (int n = 0; n < fieldInfoList.size(); n++) {
                tempField[n] = fieldInfoList.get(n).get("field").toString();
            }

            if (result != null){
                int[] arrStartCnt = new int[5];
                int[] arrEndCnt = new int[5];
                String[][] arrField = new String[5][result.size()];
                Arrays.fill(arrStartCnt, nRow);
                Arrays.fill(arrEndCnt, 0);
                for (String[] arrRow: arrField)
                    Arrays.fill(arrRow, "");

                int nCnt = 0;

                for (Object item : result) {
                    Map<String, Object> testMap = new HashMap<String, Object>();
                    try {
                        testMap = domainToMapWithAccept(item, tempField);
                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                    //row = sheet.getRow(nRow++);
                    row = sheet.createRow(nRow++);
                    String fieldTitle = "";
                    for (int i = 0; i < fieldInfoList.size(); i++) {

                        //cell = row.getCell(i);
                        cell = row.createCell(i);

                        if (nCnt > 0) {
                            if (fieldInfoList.get(i).get("field").equals("extra_company")
                                    && arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
                                arrEndCnt[0] = nRow - 1;
                            }
                        }


                        if ((testMap.get(fieldInfoList.get(i).get("field")) != null) && (!testMap.get(fieldInfoList.get(i).get("field")).equals(""))) {
                            if (fieldInfoList.get(i).get("fileType").toString().equals("Int")) {
                                double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(d);
                            } else if (fieldInfoList.get(i).get("fileType").toString().equals("Float")) {
                                double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(d);
                            } else {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cell.setCellValue(testMap.get(fieldInfoList.get(i).get("field")).toString());
                            }

                        } else {
                            cell.setCellValue(" ");
                        }

                        if (i == 0 && testMap.get(tempField[i]).equals("합계")) {
                            sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 0, 2));
                            fieldTitle = "allTot";
                        }
                        else if (i == 1 && !testMap.get(tempField[0]).equals("합계") && testMap.get(tempField[i]).equals("합계")) {
                            sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 1, 2));
                            fieldTitle = "subTot";
                        }

                        if(fieldTitle.equals("allTot")) {
                            CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                                    , "subtitle", fieldStyleList.get(i).get("fontColor").toString()
                                    , "c4c4c4"
                                    , fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
                                    , fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
                            cell.setCellStyle(titleStyle);

                        }
                        else if(fieldTitle.equals("subTot")) {
                            if (i > 0) {
                                CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                                        , fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
                                        , "f4f4f4"
                                        , fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
                                        , fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
                                cell.setCellStyle(titleStyle);
                            }
                            else {
                                CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                                        , fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
                                        , fieldStyleList.get(i).get("styleColor").toString()
                                        , fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
                                        , fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
                                cell.setCellStyle(titleStyle);
                            }

                        }
                        else {
                            CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                                    , fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
                                    , fieldStyleList.get(i).get("styleColor").toString()
                                    , fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
                                    , fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
                            cell.setCellStyle(titleStyle);
                        }

                        //sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
                        sheet.setColumnWidth(i, Integer.parseInt(fieldInfoList.get(i).get("cellWidth").toString()) );
                        if (nCnt > 0) {
                            if (fieldInfoList.get(i).get("field").equals("extra_company")
                                    && !arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
                                if (arrStartCnt[0] != 0 && arrEndCnt[0] != 0 && arrEndCnt[0] > arrStartCnt[0]) {
                                    sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[0], arrEndCnt[0], 0, 0));
                                }

                                arrStartCnt[0] = nRow - 1;
                                arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
                            } else if (fieldInfoList.get(i).get("field").equals("extra_company")
                                    && arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
                                arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
                            }
                        }
                        else{
                            if (fieldInfoList.get(i).get("field").equals("extra_company")) {
                                arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
                            }
                        }
                    }
                    nCnt++;

                }

                for(int nMer = 0; nMer < 1;nMer++) {
                    arrEndCnt[nMer] = nRow - 1;
                    if (arrStartCnt[nMer] != 0 && arrEndCnt[nMer] != 0 && arrEndCnt[nMer] > arrStartCnt[nMer]) {
                        sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[nMer], arrEndCnt[nMer], nMer, nMer));
                    }
                }

            }

            nRow = Integer.parseInt(titleStyleMap.get("lastRow").toString());

        }


        if (headerMap != null){
            for (int i = Integer.parseInt(headerMap.get("sRow1").toString()); i <= Integer.parseInt(headerMap.get("eRow1").toString()); i++) {
                row = sheet.createRow(i);
                for (int j = Integer.parseInt(headerMap.get("sCol").toString()); j <= Integer.parseInt(headerMap.get("eCol").toString()); j++) {
                    cell = row.createCell(j);
                    CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                            , headerMap.get("fontType").toString(), headerMap.get("fontColor").toString(), headerMap.get("styleColor").toString()
                            , headerMap.get("textAlign").toString(), headerMap.get("textVAlign").toString()
                            , headerMap.get("line").toString(),"");
                    cell.setCellStyle(titleStyle);
                }

                int nRow1 = Integer.parseInt(headerMap.get("sRow1").toString());
                int nRow2 = Integer.parseInt(headerMap.get("eRow1").toString());
                int nCol1 = Integer.parseInt(headerMap.get("sCol").toString());
                int nCol2 = Integer.parseInt(headerMap.get("eCol").toString());

                row = sheet.getRow(nRow1);

                cell = row.getCell(nCol1);

                cell.setCellValue(headerMap.get("title1").toString());

                if ((nRow1 != nRow2) || (nCol1 != nCol2)){
                    sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
                }
                //sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
                //sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
                row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
                row.setHeight((short)600);
            }
            nRow = Integer.parseInt(headerMap.get("eRow1").toString());

        }

        if (titleStyleMap != null){
            for (int i = Integer.parseInt(titleStyleMap.get("sRow1").toString()); i <= Integer.parseInt(titleStyleMap.get("eRow1").toString()); i++) {
                row = sheet.createRow(i);
                for (int j = Integer.parseInt(titleStyleMap.get("sCol").toString()); j <= Integer.parseInt(titleStyleMap.get("eCol").toString()); j++) {
                    cell = row.createCell(j);
                    CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                            , titleStyleMap.get("fontType").toString(), titleStyleMap.get("fontColor").toString(), titleStyleMap.get("styleColor").toString()
                            , titleStyleMap.get("textAlign").toString(), titleStyleMap.get("textVAlign").toString()
                            , titleStyleMap.get("line").toString(),"");
                    cell.setCellStyle(titleStyle);
                }
            }

            nRow = Integer.parseInt(titleStyleMap.get("sRow1").toString());

            if (titleCellList != null){
                for(int n = 0; n < titleCellList.size(); n++) {
                    int nRow1 = nRow + Integer.parseInt(titleCellList.get(n).get("sRow").toString()) - 3;
                    int nRow2 = nRow + Integer.parseInt(titleCellList.get(n).get("eRow").toString()) - 3;
                    int nCol1 = Integer.parseInt(titleCellList.get(n).get("sCol").toString());
                    int nCol2 = Integer.parseInt(titleCellList.get(n).get("eCol").toString());

                    row = sheet.getRow(nRow1);

                    cell = row.getCell(nCol1);

                    cell.setCellValue(titleCellList.get(n).get("cellTitle").toString());

                    if ((nRow1 != nRow2) || (nCol1 != nCol2)){
                        sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
                    }
                    //sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
                    //sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
                    row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
                    row.setHeight((short)300);
                }
            }

            nRow = Integer.parseInt(titleStyleMap.get("eRow1").toString());

        }

        if (titleStyleMap != null){

            nRow = Integer.parseInt(titleStyleMap.get("firstRow1").toString());

            String[] tempField = new String[fieldInfoList.size()];
            for (int n = 0; n < fieldInfoList.size(); n++) {
                tempField[n] = fieldInfoList.get(n).get("field1").toString();
            }

            if (result1 != null){
                int[] arrStartCnt = new int[5];
                int[] arrEndCnt = new int[5];
                String[][] arrField = new String[5][result1.size()];
                Arrays.fill(arrStartCnt, nRow);
                Arrays.fill(arrEndCnt, 0);
                for (String[] arrRow: arrField)
                    Arrays.fill(arrRow, "");

                int nCnt = 0;

                for (Object item : result1) {
                    Map<String, Object> testMap = new HashMap<String, Object>();
                    try {
                        testMap = domainToMapWithAccept(item, tempField);
                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                    //row = sheet.getRow(nRow++);
                    row = sheet.createRow(nRow++);
                    String fieldTitle = "";
                    for (int i = 0; i < fieldInfoList.size(); i++) {

                        //cell = row.getCell(i);
                        cell = row.createCell(i);

                        if (nCnt > 0) {
                            if (fieldInfoList.get(i).get("field1").equals("extra1_company")
                                    && arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field1")).toString())) {
                                arrEndCnt[0] = nRow - 1;
                            }
                        }


                        if ((testMap.get(fieldInfoList.get(i).get("field1")) != null) && (!testMap.get(fieldInfoList.get(i).get("field1")).equals(""))) {

                            if (fieldInfoList.get(i).get("fileType").toString().equals("Int")) {
                                double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field1")).toString());
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(d);
                            } else if (fieldInfoList.get(i).get("fileType").toString().equals("Float")) {
                                double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field1")).toString());
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(d);
                            } else {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cell.setCellValue(testMap.get(fieldInfoList.get(i).get("field1")).toString());
                            }

                        } else {
                            cell.setCellValue(" ");
                        }

                        if (i == 0 && testMap.get(tempField[i]).equals("합계")) {
                            sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 0, 2));
                            fieldTitle = "allTot";
                        }
                        else if (i == 1 && !testMap.get(tempField[0]).equals("합계") && testMap.get(tempField[i]).equals("합계")) {
                            sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 1, 2));
                            fieldTitle = "subTot";
                        }

                        if(fieldTitle.equals("allTot")) {
                            CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                                    , "subtitle", fieldStyleList.get(i).get("fontColor").toString()
                                    , "c4c4c4"
                                    , fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
                                    , fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
                            cell.setCellStyle(titleStyle);

                        }
                        else if(fieldTitle.equals("subTot")) {
                            if (i > 0) {
                                CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                                        , fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
                                        , "f4f4f4"
                                        , fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
                                        , fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
                                cell.setCellStyle(titleStyle);
                            }
                            else {
                                CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                                        , fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
                                        , fieldStyleList.get(i).get("styleColor").toString()
                                        , fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
                                        , fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
                                cell.setCellStyle(titleStyle);
                            }

                        }
                        else {
                            CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                                    , fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
                                    , fieldStyleList.get(i).get("styleColor").toString()
                                    , fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
                                    , fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
                            cell.setCellStyle(titleStyle);
                        }

                        //sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
                        //sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
                        sheet.setColumnWidth(i, Integer.parseInt(fieldInfoList.get(i).get("cellWidth").toString()) );
                        if (nCnt > 0) {
                            if (fieldInfoList.get(i).get("field1").equals("extra1_company")
                                    && !arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field1")).toString())) {
                                if (arrStartCnt[0] != 0 && arrEndCnt[0] != 0 && arrEndCnt[0] > arrStartCnt[0]) {
                                    sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[0], arrEndCnt[0], 0, 0));
                                }

                                arrStartCnt[0] = nRow - 1;
                                arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field1")).toString();
                            } else if (fieldInfoList.get(i).get("field1").equals("extra1_company")
                                    && arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field1")).toString())) {
                                arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field1")).toString();
                            }
                        }
                        else{
                            if (fieldInfoList.get(i).get("field1").equals("extra1_company")) {
                                arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field1")).toString();
                            }
                        }
                    }
                    nCnt++;

                }

                for(int nMer = 0; nMer < 1;nMer++) {
                    arrEndCnt[nMer] = nRow - 1;
                    if (arrStartCnt[nMer] != 0 && arrEndCnt[nMer] != 0 && arrEndCnt[nMer] > arrStartCnt[nMer]) {
                        sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[nMer], arrEndCnt[nMer], nMer, nMer));
                    }
                }

            }

            nRow = Integer.parseInt(titleStyleMap.get("lastRow1").toString());

        }

        row = sheet.createRow(nRow);
        for (int i = 0; i < fieldInfoList.size(); i++) {
            cell = row.createCell(i);
            if (i < 3) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(nMon + "월 수입 부대비용 합계");
            }
            else {
                double d = Double.parseDouble(Long.toString(nTotla));
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(d);
            }

            CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                    , "subtitle", fieldStyleList.get(i).get("fontColor").toString()
                    , "FEFFE8"
                    , fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
                    , fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
            cell.setCellStyle(titleStyle);
        }
        sheet.addMergedRegion(new CellRangeAddress(nRow, nRow, 0, 2));
        sheet.addMergedRegion(new CellRangeAddress(nRow, nRow, 3, 5));
        row.setHeight((short)500);



        //sheet.createFreezePane(0, 4, 8, 4);
        //sheet.setAutoFilter(new CellRangeAddress(3, 3, 0, 8));
        return workbook;
    }


    /**
     * Excel File을 생성하여 Workbook을 반환.
     *
     * sheetTitle Sheet 제목
     * excelCellInfos Cell의 생성 정보
     * rows 실제 Data
     * XSSFWorkbook
     */
    @SuppressWarnings("unchecked")
    public static SXSSFWorkbook buildExcelXSS(String sheetTitle, Map<String, Object> excelInfpMap
            , List<Object> result, boolean excelOption)
            throws Exception {

        SXSSFWorkbook workbook = new SXSSFWorkbook();

        ExcelStyleBuilder stylesXss = new ExcelStyleBuilder();

        Sheet sheet = workbook.createSheet(sheetTitle);

        sheet.setDisplayGridlines(false);

        Row row = null;
        Cell cell = null;

        //첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
        Map<String, Object> headerMap = (Map<String, Object>) excelInfpMap.get("headerMap");
        Map<String, Object> unitMap = (Map<String, Object>) excelInfpMap.get("unitMap");
        Map<String, Object> titleStyleMap = (Map<String, Object>) excelInfpMap.get("titleStyleMap");
        List<Map<String, Object>> fieldInfoList = (List<Map<String, Object>>) excelInfpMap.get("fieldInfoList");

        int nRow = 1;

        if (headerMap != null){
            for (int i = Integer.parseInt(headerMap.get("sRow").toString()); i <= Integer.parseInt(headerMap.get("eRow").toString()); i++) {
                row = sheet.createRow(i);
                for (int j = Integer.parseInt(headerMap.get("sCol").toString()); j <= Integer.parseInt(headerMap.get("eCol").toString()); j++) {
                    cell = row.createCell(j);
                    CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                            , headerMap.get("fontType").toString(), headerMap.get("fontColor").toString(), headerMap.get("styleColor").toString()
                            , headerMap.get("textAlign").toString(), headerMap.get("textVAlign").toString()
                            , headerMap.get("line").toString(),"");
                    cell.setCellStyle(titleStyle);
                }

                int nRow1 = Integer.parseInt(headerMap.get("sRow").toString());
                int nRow2 = Integer.parseInt(headerMap.get("eRow").toString());
                int nCol1 = Integer.parseInt(headerMap.get("sCol").toString());
                int nCol2 = Integer.parseInt(headerMap.get("eCol").toString());

                row = sheet.getRow(nRow1);

                cell = row.getCell(nCol1);

                cell.setCellValue(headerMap.get("title").toString());

                if ((nRow1 != nRow2) || (nCol1 != nCol2)){
                    sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
                }
                //sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
                //sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
                row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
                row.setHeight((short)600);
            }
            nRow = Integer.parseInt(headerMap.get("eRow").toString());

        }

        if (unitMap != null){
            for (int i = Integer.parseInt(unitMap.get("sRow").toString()); i <= Integer.parseInt(unitMap.get("eRow").toString()); i++) {
                row = sheet.createRow(i);
                for (int j = Integer.parseInt(unitMap.get("sCol").toString()); j <= Integer.parseInt(unitMap.get("eCol").toString()); j++) {
                    cell = row.createCell(j);
                    CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                            , unitMap.get("fontType").toString(), unitMap.get("fontColor").toString(), unitMap.get("styleColor").toString()
                            , unitMap.get("textAlign").toString(), unitMap.get("textVAlign").toString()
                            , unitMap.get("line").toString(),"");
                    cell.setCellStyle(titleStyle);
                }

                int nRow1 = Integer.parseInt(unitMap.get("sRow").toString());
                int nRow2 = Integer.parseInt(unitMap.get("eRow").toString());
                int nCol1 = Integer.parseInt(unitMap.get("sCol").toString());
                int nCol2 = Integer.parseInt(unitMap.get("eCol").toString());

                row = sheet.getRow(nRow1);

                cell = row.getCell(nCol1);

                cell.setCellValue(unitMap.get("title").toString());

                if ((nRow1 != nRow2) || (nCol1 != nCol2)){
                    sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
                }
                //sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
                //sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
                row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
                row.setHeight((short)300);
            }
            nRow = Integer.parseInt(unitMap.get("eRow").toString());

        }


        if (titleStyleMap != null){
            for (int i = Integer.parseInt(titleStyleMap.get("sRow").toString()); i <= Integer.parseInt(titleStyleMap.get("eRow").toString()); i++) {
                row = sheet.createRow(i);
                for (int j = Integer.parseInt(titleStyleMap.get("sCol").toString()); j <= Integer.parseInt(titleStyleMap.get("eCol").toString()); j++) {
                    cell = row.createCell(j);
                    CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                            , titleStyleMap.get("fontType").toString(), titleStyleMap.get("fontColor").toString(), titleStyleMap.get("styleColor").toString()
                            , titleStyleMap.get("textAlign").toString(), titleStyleMap.get("textVAlign").toString()
                            , titleStyleMap.get("line").toString(),"");
                    cell.setCellStyle(titleStyle);
                }
            }

            if (fieldInfoList != null){
                for(int n = 0; n < fieldInfoList.size(); n++) {
                    int nRow1 = Integer.parseInt(titleStyleMap.get("sRow").toString());
                    int nRow2 = Integer.parseInt(titleStyleMap.get("eRow").toString());
                    int nCol1 = n;
                    int nCol2 = n;

                    row = sheet.getRow(nRow1);

                    cell = row.getCell(nCol1);

                    cell.setCellValue(fieldInfoList.get(n).get("cellTitle").toString());

                    if ((nRow1 != nRow2) || (nCol1 != nCol2)){
                        sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
                    }
                    //sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
                    //sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
                    sheet.setColumnWidth(n, Integer.parseInt(fieldInfoList.get(n).get("cellWidth").toString()) );
                    row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
                    row.setHeight((short)300);
                }
            }

            nRow = Integer.parseInt(titleStyleMap.get("eRow").toString());

        }

        if (titleStyleMap != null){

            nRow = nRow + 1;

            String[] tempField = new String[fieldInfoList.size()];
            for (int n = 0; n < fieldInfoList.size(); n++) {
                tempField[n] = fieldInfoList.get(n).get("field").toString();
            }

            if (result != null){

                int[] arrStartCnt = new int[5];
                int[] arrEndCnt = new int[5];
                String[][] arrField = new String[5][result.size()];
                Arrays.fill(arrStartCnt, nRow);
                Arrays.fill(arrEndCnt, 0);
                for (String[] arrRow: arrField)
                    Arrays.fill(arrRow, "");

                int nCnt = 0;
                CellStyle titleStyle;

                for (Object item : result) {
                    Map<String, Object> testMap = new HashMap<String, Object>();
                    try {
                        testMap = domainToMapWithAccept(item, tempField);
                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                    row = sheet.createRow(nRow++);

                    for (int i = 0; i < fieldInfoList.size(); i++) {

                        cell = row.createCell(i);

                        if (nCnt > 0) {
                            if (fieldInfoList.get(i).get("field").equals(tempField[0])
                                    && arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
                                arrEndCnt[0] = nRow - 1;
                            }
                        }

                        if ((testMap.get(fieldInfoList.get(i).get("field")) != null) && (!testMap.get(fieldInfoList.get(i).get("field")).equals(""))) {
                            if (fieldInfoList.get(i).get("fileType").toString().equals("Int")) {
                                double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(d);
                            } else if (fieldInfoList.get(i).get("fileType").toString().equals("Float")) {
                                double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(d);
                            } else {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cell.setCellValue(testMap.get(fieldInfoList.get(i).get("field")).toString());
                            }

                        } else {
                            cell.setCellValue(" ");
                        }
                        
                        titleStyle = null;

                        titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                                , fieldInfoList.get(i).get("fontType").toString(), fieldInfoList.get(i).get("fontColor").toString()
                                , fieldInfoList.get(i).get("styleColor").toString()
                                , fieldInfoList.get(i).get("textAlign").toString(), fieldInfoList.get(i).get("textVAlign").toString()
                                , fieldInfoList.get(i).get("line").toString(), fieldInfoList.get(i).get("fomule").toString());
                        cell.setCellStyle(titleStyle);

                        if (nCnt > 0) {
                            if (fieldInfoList.get(i).get("field").equals(tempField[0])
                                    && !arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
                                if (arrStartCnt[0] != 0 && arrEndCnt[0] != 0 && arrEndCnt[0] > arrStartCnt[0]) {
                                    sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[0], arrEndCnt[0], 0, 0));
                                }

                                arrStartCnt[0] = nRow - 1;
                                arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
                            } else if (fieldInfoList.get(i).get("field").equals(tempField[0])
                                    && arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
                                arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
                            }
                        }
                        else{
                            if (fieldInfoList.get(i).get("field").equals(tempField[0])) {
                                arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
                            }
                        }

                        //sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
                        //sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );

                    }
                    nCnt++;

                }
                for(int nMer = 0; nMer < 1;nMer++) {
                    arrEndCnt[nMer] = nRow - 1;
                    if (arrStartCnt[nMer] != 0 && arrEndCnt[nMer] != 0 && arrEndCnt[nMer] > arrStartCnt[nMer]) {
                        sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[nMer], arrEndCnt[nMer], nMer, nMer));
                    }
                }

            }

        }

        if (excelOption) {
            sheet.createFreezePane(0, Integer.parseInt(titleStyleMap.get("sRow").toString())+1, Integer.parseInt(titleStyleMap.get("eCol").toString()), Integer.parseInt(titleStyleMap.get("sRow").toString())+1);
            sheet.setAutoFilter(new CellRangeAddress(Integer.parseInt(titleStyleMap.get("sRow").toString()), Integer.parseInt(titleStyleMap.get("sRow").toString()), 0, Integer.parseInt(titleStyleMap.get("eCol").toString())));
        }
        return workbook;
    }

    
    /**
     * Excel File을 생성하여 Workbook을 반환.
     *
     * sheetTitle Sheet 제목
     * excelCellInfos Cell의 생성 정보
     * rows 실제 Data
     * XSSFWorkbook
     */
    @SuppressWarnings("unchecked")
    public static SXSSFWorkbook buildExcelXSSNon(String sheetTitle, Map<String, Object> excelInfpMap
            , List<Object> result, boolean excelOption)
            throws Exception {

        SXSSFWorkbook workbook = new SXSSFWorkbook();

        ExcelStyleBuilder stylesXss = new ExcelStyleBuilder();

        Sheet sheet = workbook.createSheet(sheetTitle);

        sheet.setDisplayGridlines(false);

        Row row = null;
        Cell cell = null;

        //첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
        Map<String, Object> headerMap = (Map<String, Object>) excelInfpMap.get("headerMap");
        Map<String, Object> unitMap = (Map<String, Object>) excelInfpMap.get("unitMap");
        Map<String, Object> titleStyleMap = (Map<String, Object>) excelInfpMap.get("titleStyleMap");
        List<Map<String, Object>> fieldInfoList = (List<Map<String, Object>>) excelInfpMap.get("fieldInfoList");

        int nRow = 1;

        if (headerMap != null){
            for (int i = Integer.parseInt(headerMap.get("sRow").toString()); i <= Integer.parseInt(headerMap.get("eRow").toString()); i++) {
                row = sheet.createRow(i);
                for (int j = Integer.parseInt(headerMap.get("sCol").toString()); j <= Integer.parseInt(headerMap.get("eCol").toString()); j++) {
                    cell = row.createCell(j);
                    CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                            , headerMap.get("fontType").toString(), headerMap.get("fontColor").toString(), headerMap.get("styleColor").toString()
                            , headerMap.get("textAlign").toString(), headerMap.get("textVAlign").toString()
                            , headerMap.get("line").toString(),"");
                    cell.setCellStyle(titleStyle);
                }

                int nRow1 = Integer.parseInt(headerMap.get("sRow").toString());
                int nRow2 = Integer.parseInt(headerMap.get("eRow").toString());
                int nCol1 = Integer.parseInt(headerMap.get("sCol").toString());
                int nCol2 = Integer.parseInt(headerMap.get("eCol").toString());

                row = sheet.getRow(nRow1);

                cell = row.getCell(nCol1);

                cell.setCellValue(headerMap.get("title").toString());

                if ((nRow1 != nRow2) || (nCol1 != nCol2)){
                    sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
                }
                //sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
                //sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
                row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
                row.setHeight((short)600);
            }
            nRow = Integer.parseInt(headerMap.get("eRow").toString());

        }

        if (unitMap != null){
            for (int i = Integer.parseInt(unitMap.get("sRow").toString()); i <= Integer.parseInt(unitMap.get("eRow").toString()); i++) {
                row = sheet.createRow(i);
                for (int j = Integer.parseInt(unitMap.get("sCol").toString()); j <= Integer.parseInt(unitMap.get("eCol").toString()); j++) {
                    cell = row.createCell(j);
                    CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                            , unitMap.get("fontType").toString(), unitMap.get("fontColor").toString(), unitMap.get("styleColor").toString()
                            , unitMap.get("textAlign").toString(), unitMap.get("textVAlign").toString()
                            , unitMap.get("line").toString(),"");
                    cell.setCellStyle(titleStyle);
                }

                int nRow1 = Integer.parseInt(unitMap.get("sRow").toString());
                int nRow2 = Integer.parseInt(unitMap.get("eRow").toString());
                int nCol1 = Integer.parseInt(unitMap.get("sCol").toString());
                int nCol2 = Integer.parseInt(unitMap.get("eCol").toString());

                row = sheet.getRow(nRow1);

                cell = row.getCell(nCol1);

                cell.setCellValue(unitMap.get("title").toString());

                if ((nRow1 != nRow2) || (nCol1 != nCol2)){
                    sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
                }
                //sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
                //sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
                row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
                row.setHeight((short)300);
            }
            nRow = Integer.parseInt(unitMap.get("eRow").toString());

        }


        if (titleStyleMap != null){
            for (int i = Integer.parseInt(titleStyleMap.get("sRow").toString()); i <= Integer.parseInt(titleStyleMap.get("eRow").toString()); i++) {
                row = sheet.createRow(i);
                for (int j = Integer.parseInt(titleStyleMap.get("sCol").toString()); j <= Integer.parseInt(titleStyleMap.get("eCol").toString()); j++) {
                    cell = row.createCell(j);
                    CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                            , titleStyleMap.get("fontType").toString(), titleStyleMap.get("fontColor").toString(), titleStyleMap.get("styleColor").toString()
                            , titleStyleMap.get("textAlign").toString(), titleStyleMap.get("textVAlign").toString()
                            , titleStyleMap.get("line").toString(),"");
                    cell.setCellStyle(titleStyle);
                }
            }

            if (fieldInfoList != null){
                for(int n = 0; n < fieldInfoList.size(); n++) {
                    int nRow1 = Integer.parseInt(titleStyleMap.get("sRow").toString());
                    int nRow2 = Integer.parseInt(titleStyleMap.get("eRow").toString());
                    int nCol1 = n;
                    int nCol2 = n;

                    row = sheet.getRow(nRow1);

                    cell = row.getCell(nCol1);

                    cell.setCellValue(fieldInfoList.get(n).get("cellTitle").toString());

                    if ((nRow1 != nRow2) || (nCol1 != nCol2)){
                        sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
                    }
                    //sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
                    //sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
                    sheet.setColumnWidth(n, Integer.parseInt(fieldInfoList.get(n).get("cellWidth").toString()) );
                    row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
                    row.setHeight((short)300);
                }
            }

            nRow = Integer.parseInt(titleStyleMap.get("eRow").toString());

        }

        if (titleStyleMap != null){

            nRow = nRow + 1;

            String[] tempField = new String[fieldInfoList.size()];
            for (int n = 0; n < fieldInfoList.size(); n++) {
                tempField[n] = fieldInfoList.get(n).get("field").toString();
            }

            if (result != null){
            	CellStyle titleStyle;
                for (Object item : result) {
                    Map<String, Object> testMap = new HashMap<String, Object>();
                    try {
                        testMap = domainToMapWithAccept(item, tempField);
                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                    row = sheet.createRow(nRow++);

                    for (int i = 0; i < fieldInfoList.size(); i++) {

                        cell = row.createCell(i);


                        if ((testMap.get(fieldInfoList.get(i).get("field")) != null) && (!testMap.get(fieldInfoList.get(i).get("field")).equals(""))) {
                        	if (testMap.get(fieldInfoList.get(i).get("field")) == null) {
                        		cell.setCellValue(" ");
                        	}
                        	else {
	                            if (fieldInfoList.get(i).get("fileType").toString().equals("Int")) {
	                                double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
	                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
	                                cell.setCellValue(d);
	                            } else if (fieldInfoList.get(i).get("fileType").toString().equals("Float")) {
	                                double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
	                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
	                                cell.setCellValue(d);
	                            } else {
	                                cell.setCellType(Cell.CELL_TYPE_STRING);
	                                cell.setCellValue(testMap.get(fieldInfoList.get(i).get("field")).toString());
	                            }
                        	}

                        } else {
                            cell.setCellValue(" ");
                        }
                        titleStyle = null;
                        titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                                , fieldInfoList.get(i).get("fontType").toString(), fieldInfoList.get(i).get("fontColor").toString()
                                , fieldInfoList.get(i).get("styleColor").toString()
                                , fieldInfoList.get(i).get("textAlign").toString(), fieldInfoList.get(i).get("textVAlign").toString()
                                , fieldInfoList.get(i).get("line").toString(), fieldInfoList.get(i).get("fomule").toString());
                        cell.setCellStyle(titleStyle);


                    }

                }

            }

        }

        if (excelOption) {
            sheet.createFreezePane(0, Integer.parseInt(titleStyleMap.get("sRow").toString())+1, Integer.parseInt(titleStyleMap.get("eCol").toString()), Integer.parseInt(titleStyleMap.get("sRow").toString())+1);
            sheet.setAutoFilter(new CellRangeAddress(Integer.parseInt(titleStyleMap.get("sRow").toString()), Integer.parseInt(titleStyleMap.get("sRow").toString()), 0, Integer.parseInt(titleStyleMap.get("eCol").toString())));
        }
        return workbook;
    }

    /**
     * Excel File을 생성하여 Workbook을 반환.
     *
     * sheetTitle Sheet 제목
     * excelCellInfos Cell의 생성 정보
     * rows 실제 Data
     * XSSFWorkbook
     */
    @SuppressWarnings({ "unchecked", "unused" })
    public static Sheet buildExcelXSSSheet(SXSSFWorkbook workbook, String sheetTitle, Map<String, Object> excelInfpMap
            , List<Object> result, boolean excelOption)
            throws Exception {

        ExcelStyleBuilder stylesXss = new ExcelStyleBuilder();

        Sheet sheet = workbook.createSheet(sheetTitle);

        sheet.setDisplayGridlines(false);

        Row row = null;
        Cell cell = null;

        //첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
        Map<String, Object> titleStyleMap = (Map<String, Object>) excelInfpMap.get("titleStyleMap");
        List<Map<String, Object>> fieldInfoList = (List<Map<String, Object>>) excelInfpMap.get("fieldInfoList");

        int nRow = 0;

        if (titleStyleMap != null){
            for (int i = Integer.parseInt(titleStyleMap.get("sRow").toString()); i <= Integer.parseInt(titleStyleMap.get("eRow").toString()); i++) {
                row = sheet.createRow(i);
                for (int j = Integer.parseInt(titleStyleMap.get("sCol").toString()); j <= Integer.parseInt(titleStyleMap.get("eCol").toString()); j++) {
                    cell = row.createCell(j);
                    CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                            , titleStyleMap.get("fontType").toString(), titleStyleMap.get("fontColor").toString(), titleStyleMap.get("styleColor").toString()
                            , titleStyleMap.get("textAlign").toString(), titleStyleMap.get("textVAlign").toString()
                            , titleStyleMap.get("line").toString(),"");
                    cell.setCellStyle(titleStyle);
                }
            }

            if (fieldInfoList != null){
                for(int n = 0; n < fieldInfoList.size(); n++) {
                    int nRow1 = Integer.parseInt(titleStyleMap.get("sRow").toString());
                    int nRow2 = Integer.parseInt(titleStyleMap.get("eRow").toString());
                    int nCol1 = n;
                    int nCol2 = n;

                    row = sheet.getRow(nRow1);

                    cell = row.getCell(nCol1);

                    cell.setCellValue(fieldInfoList.get(n).get("cellTitle").toString());

                    if ((nRow1 != nRow2) || (nCol1 != nCol2)){
                        sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
                    }
                    //sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
                    //sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
                    sheet.setColumnWidth(n, Integer.parseInt(fieldInfoList.get(n).get("cellWidth").toString()) );
                    row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
                    row.setHeight((short)300);
                }
            }

            nRow = Integer.parseInt(titleStyleMap.get("eRow").toString());

        }

        if (titleStyleMap != null){

            nRow = nRow + 1;

            String[] tempField = new String[fieldInfoList.size()];
            for (int n = 0; n < fieldInfoList.size(); n++) {
                tempField[n] = fieldInfoList.get(n).get("field").toString();
            }

            if (result != null){

                int[] arrStartCnt = new int[5];
                int[] arrEndCnt = new int[5];
                String[][] arrField = new String[5][result.size()];
                Arrays.fill(arrStartCnt, nRow);
                Arrays.fill(arrEndCnt, 0);
                for (String[] arrRow: arrField)
                    Arrays.fill(arrRow, "");

                int nCnt = 0;

                for (Object item : result) {
                    Map<String, Object> testMap = new HashMap<String, Object>();
                    try {
                        testMap = domainToMapWithAccept(item, tempField);
                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                    row = sheet.createRow(nRow++);

                    for (int i = 0; i < fieldInfoList.size(); i++) {

                        cell = row.createCell(i);

                        if ((testMap.get(fieldInfoList.get(i).get("field")) != null) && (!testMap.get(fieldInfoList.get(i).get("field")).equals(""))) {
                        	if (testMap.get(fieldInfoList.get(i).get("field")) == null) {
                        		cell.setCellValue(" ");
                        	}
                        	else {
	                            if (fieldInfoList.get(i).get("fileType").toString().equals("Int")) {
	                                double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
	                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
	                                cell.setCellValue(d);
	                            } else if (fieldInfoList.get(i).get("fileType").toString().equals("Float")) {
	                                double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
	                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
	                                cell.setCellValue(d);
	                            } else {
	                                cell.setCellType(Cell.CELL_TYPE_STRING);
	                                cell.setCellValue(testMap.get(fieldInfoList.get(i).get("field")).toString());
	                            }
                        	}

                        } else {
                            cell.setCellValue(" ");
                        }

                        CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
                                , fieldInfoList.get(i).get("fontType").toString(), fieldInfoList.get(i).get("fontColor").toString()
                                , fieldInfoList.get(i).get("styleColor").toString()
                                , fieldInfoList.get(i).get("textAlign").toString(), fieldInfoList.get(i).get("textVAlign").toString()
                                , fieldInfoList.get(i).get("line").toString(), fieldInfoList.get(i).get("fomule").toString());
                        cell.setCellStyle(titleStyle);

                        //sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
                        //sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );

                    }
                    nCnt++;

                }

            }

        }

        if (excelOption) {
            sheet.createFreezePane(0, Integer.parseInt(titleStyleMap.get("sRow").toString())+1, Integer.parseInt(titleStyleMap.get("eCol").toString()), Integer.parseInt(titleStyleMap.get("sRow").toString())+1);
            sheet.setAutoFilter(new CellRangeAddress(Integer.parseInt(titleStyleMap.get("sRow").toString()), Integer.parseInt(titleStyleMap.get("sRow").toString()), 0, Integer.parseInt(titleStyleMap.get("eCol").toString())));
        }
        return sheet;
    }


    /**
     * 특정 변수를 제외해서 vo를 map형식으로 변환해서 반환.
     * vo VO
     * arrExceptList 제외할 property 명 리스트
     * @return
     * @throws Exception
     */
    public static Map<String, Object> domainToMapWithAccept(Object vo, String[] arrAccepttList) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        BeanInfo info = Introspector.getBeanInfo(vo.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            Method reader = pd.getReadMethod();
            if (reader != null) {
                if(arrAccepttList != null && arrAccepttList.length > 0 && isContain(arrAccepttList, pd.getName()))
                    result.put(pd.getName(), reader.invoke(vo));
                else
                    continue;

            }
        }
        return result;
    }
    public static Boolean isContain(String[] arrList, String name) {
        for (String arr : arrList) {
            if (StringUtils.contains(arr, name))
                return true;
        }
        return false;
    }

    public static boolean isStringDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //문자열이 숫자(정수, 실수)인지 아닌지 판별한다.
    public static boolean isNumber(String str) {
        char tempCh;
        int dotCount = 0;   //실수일 경우 .의 개수를 체크하는 변수
        boolean result = true;

        for (int i=0; i<str.length(); i++){
            tempCh= str.charAt(i);    //입력받은 문자열을 문자단위로 검사
            // 아스키 코드 값이 48 ~ 57사이면 0과 9사이의 문자이다.
            if ((int)tempCh < 48 || (int)tempCh > 57){
                //만약 0~9사이의 문자가 아닌 tempCh가 .도 아니거나
                //.의 개수가 이미 1개 이상이라면 그 문자열은 숫자가 아니다.
                if(tempCh!='.' || dotCount > 0){
                    result = false;
                    break;
                }else{
                    //.일 경우 .개수 증가
                    dotCount++;
                }
            }
        }
        if (dotCount == 1) result = false;
        return result;
    }

    @SuppressWarnings("unused")
    public static boolean checkDate(String szDate) {

        boolean bResult = true;
        SimpleDateFormat oDateFormat = new SimpleDateFormat();
        Date oDate = new Date();

        oDateFormat.applyPattern("yyyy-MM-dd");
        oDateFormat.setLenient(false);      // 엄밀하게 검사한다는 옵션 (반드시 있어야 한다)

        try {
            oDate = oDateFormat.parse(szDate);
        } catch (ParseException e) {
            bResult = false;
        }

        return bResult;

    }


}
