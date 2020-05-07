/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : ExcelStyleBuilder
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

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelStyleBuilder {

    public CellStyle createExcelCellStylesXSS(SXSSFWorkbook wb, String fontType, String fontColor, String styleColor, String textAlign, String textVAlign
            , String Line, String strFormat){

        XSSFDataFormat fmt = (XSSFDataFormat) wb.createDataFormat();

        int nRFontColor = 0;
        int nGFontColor = 0;
        int nBFontColor = 0;
        if (fontColor.length() == 6) {
            nRFontColor = Integer.valueOf( fontColor.substring( 0, 2 ), 16 );
            nGFontColor = Integer.valueOf( fontColor.substring( 2, 4 ), 16 );
            nBFontColor = Integer.valueOf( fontColor.substring( 4, 6 ), 16 );
        }

        int nRCellColor = 255;
        int nGCellColor = 255;
        int nBCellColor = 255;
        if (styleColor.length() == 6) {
            nRCellColor = Integer.valueOf( styleColor.substring( 0, 2 ), 16 );
            nGCellColor = Integer.valueOf( styleColor.substring( 2, 4 ), 16 );
            nBCellColor = Integer.valueOf( styleColor.substring( 4, 6 ), 16 );
        }

        XSSFFont fontStyle = (XSSFFont) wb.createFont();

        fontStyle.setFontName("맑은 고딕");
        fontStyle.setFamily(FontFamily.MODERN);
        if (fontType.equals("title")) {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            fontStyle.setFontHeightInPoints((short)15);
            fontStyle.setUnderline(XSSFFont.U_NONE);
        }
        else if (fontType.equals("titleLine")) {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            fontStyle.setFontHeightInPoints((short)15);
            fontStyle.setUnderline(XSSFFont.U_DOUBLE);
        }
        else if (fontType.equals("subtitle")) {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            fontStyle.setFontHeightInPoints((short)10);
            fontStyle.setUnderline(XSSFFont.U_NONE);
        }
        else if (fontType.equals("subtitleLine")) {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            fontStyle.setFontHeightInPoints((short)10);
            fontStyle.setUnderline(XSSFFont.U_SINGLE);
        }
        else if (fontType.equals("unit")) {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
            fontStyle.setFontHeightInPoints((short)10);
            fontStyle.setUnderline(XSSFFont.U_NONE);
        }
        else if (fontType.equals("unitLine")) {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
            fontStyle.setFontHeightInPoints((short)10);
            fontStyle.setUnderline(XSSFFont.U_SINGLE);
        }
        else {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
            fontStyle.setFontHeightInPoints((short)10);
            fontStyle.setUnderline(XSSFFont.U_NONE);
        }
        fontStyle.setColor(new XSSFColor(new java.awt.Color(nRFontColor, nGFontColor, nBFontColor)));

        XSSFCellStyle cellStyle = (XSSFCellStyle) wb.createCellStyle();
        if (textAlign.equals("left")) {
            cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT); //왼쪽
        }
        else if (textAlign.equals("right")) {
            cellStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT); //오른쪽 정렬
        }
        else {
            cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); //중앙 정렬
        }
        if (textVAlign.equals("top")) {
            cellStyle.setVerticalAlignment(VerticalAlignment.TOP); //왼쪽
        }
        else if (textVAlign.equals("bottom")) {
            cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM); //오른쪽 정렬
        }
        else {
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //중앙 정렬
        }
        if (strFormat.length() > 0) {
            cellStyle.setDataFormat(fmt.getFormat(strFormat));
        }
        cellStyle.setWrapText(true);
        cellStyle.setShrinkToFit(true);
        cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(nRCellColor, nGCellColor, nBCellColor)));
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        if (Line.equals("none")){
            cellStyle.setBorderLeft(XSSFCellStyle.BORDER_NONE);
            cellStyle.setBorderRight(XSSFCellStyle.BORDER_NONE);
            cellStyle.setBorderTop(XSSFCellStyle.BORDER_NONE);
            cellStyle.setBorderBottom(XSSFCellStyle.BORDER_NONE);
        }
        else if (Line.equals("dot")){
            cellStyle.setBorderLeft(XSSFCellStyle.BORDER_DOTTED);
            cellStyle.setBorderRight(XSSFCellStyle.BORDER_DOTTED);
            cellStyle.setBorderTop(XSSFCellStyle.BORDER_DOTTED);
            cellStyle.setBorderBottom(XSSFCellStyle.BORDER_DOTTED);
        }
        else {
            cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        }

        cellStyle.setFont(fontStyle);

        return cellStyle;

    }

    public HSSFCellStyle createExcelCellStylesHSS(HSSFWorkbook wb, String fontType, String fontColor, String styleColor, String textAlign, String textVAlign
            , String Line, String strFormat){

        HSSFDataFormat fmt = wb.createDataFormat();

        HSSFPalette palette = wb.getCustomPalette();

        int nRFontColor = 0;
        int nGFontColor = 0;
        int nBFontColor = 0;
        if (fontColor.length() == 6) {
            nRFontColor = Integer.valueOf( fontColor.substring( 0, 2 ), 16 );
            nGFontColor = Integer.valueOf( fontColor.substring( 2, 4 ), 16 );
            nBFontColor = Integer.valueOf( fontColor.substring( 4, 6 ), 16 );
        }

        int nRCellColor = 255;
        int nGCellColor = 255;
        int nBCellColor = 255;
        if (styleColor.length() == 6) {
            nRCellColor = Integer.valueOf( styleColor.substring( 0, 2 ), 16 );
            nGCellColor = Integer.valueOf( styleColor.substring( 2, 4 ), 16 );
            nBCellColor = Integer.valueOf( styleColor.substring( 4, 6 ), 16 );
        }

        HSSFFont fontStyle = wb.createFont();

        fontStyle.setFontName("맑은 고딕");
        if (fontType.equals("title")) {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            fontStyle.setFontHeightInPoints((short)15);
            fontStyle.setUnderline(XSSFFont.U_NONE);
        }
        else if (fontType.equals("titleLine")) {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            fontStyle.setFontHeightInPoints((short)15);
            fontStyle.setUnderline(XSSFFont.U_DOUBLE);
        }
        else if (fontType.equals("subtitle")) {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            fontStyle.setFontHeightInPoints((short)10);
            fontStyle.setUnderline(XSSFFont.U_NONE);
        }
        else if (fontType.equals("subtitleLine")) {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            fontStyle.setFontHeightInPoints((short)10);
            fontStyle.setUnderline(XSSFFont.U_SINGLE);
        }
        else if (fontType.equals("unit")) {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
            fontStyle.setFontHeightInPoints((short)10);
            fontStyle.setUnderline(XSSFFont.U_NONE);
        }
        else if (fontType.equals("unitLine")) {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
            fontStyle.setFontHeightInPoints((short)10);
            fontStyle.setUnderline(XSSFFont.U_SINGLE);
        }
        else {
            fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
            fontStyle.setFontHeightInPoints((short)10);
            fontStyle.setUnderline(XSSFFont.U_NONE);
        }
        fontStyle.setColor(palette.findSimilarColor(nRFontColor, nGFontColor, nBFontColor).getIndex());

        HSSFCellStyle cellStyle = wb.createCellStyle();
        if (textAlign.equals("left")) {
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); //왼쪽
        }
        else if (textAlign.equals("right")) {
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //오른쪽 정렬
        }
        else {
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //중앙 정렬
        }
        if (textVAlign.equals("top")) {
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP); //왼쪽
        }
        else if (textVAlign.equals("bottom")) {
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM); //오른쪽 정렬
        }
        else {
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //중앙 정렬
        }
        if (strFormat.length() > 0) {
            cellStyle.setDataFormat(fmt.getFormat(strFormat));
        }
        cellStyle.setWrapText(true);
        cellStyle.setShrinkToFit(true);
        cellStyle.setFillForegroundColor(palette.findSimilarColor(nRCellColor, nGCellColor, nBCellColor).getIndex());
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        if (Line.equals("none")){
            cellStyle.setBorderLeft(XSSFCellStyle.BORDER_NONE);
            cellStyle.setBorderRight(XSSFCellStyle.BORDER_NONE);
            cellStyle.setBorderTop(XSSFCellStyle.BORDER_NONE);
            cellStyle.setBorderBottom(XSSFCellStyle.BORDER_NONE);
        }
        else if (Line.equals("dot")){
            cellStyle.setBorderLeft(XSSFCellStyle.BORDER_DOTTED);
            cellStyle.setBorderRight(XSSFCellStyle.BORDER_DOTTED);
            cellStyle.setBorderTop(XSSFCellStyle.BORDER_DOTTED);
            cellStyle.setBorderBottom(XSSFCellStyle.BORDER_DOTTED);
        }
        else {
            cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        }

        cellStyle.setFont(fontStyle);

        return cellStyle;

    }

    public Map<String, HSSFCellStyle> createExcelStylesHSS(HSSFWorkbook wb){
        Map<String, HSSFCellStyle> styles = new HashMap<String, HSSFCellStyle>();
        HSSFDataFormat fmt = wb.createDataFormat();

        HSSFPalette palette = wb.getCustomPalette();

        HSSFFont fontTitle = wb.createFont();
        fontTitle.setFontName("돋움");
        fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontTitle.setFontHeightInPoints((short)15);
        fontTitle.setUnderline(HSSFFont.U_DOUBLE);

        HSSFFont fontBoldTitle = wb.createFont();
        fontBoldTitle.setFontName("돋움");
        fontBoldTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontBoldTitle.setFontHeightInPoints((short)12);
        fontBoldTitle.setUnderline(HSSFFont.U_NONE);

        HSSFFont fontNomal = wb.createFont();
        fontNomal.setFontName("돋움");
        fontNomal.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        fontNomal.setFontHeightInPoints((short)8);
        fontNomal.setUnderline(HSSFFont.U_NONE);

        HSSFFont fontBold = wb.createFont();
        fontBold.setFontName("돋움");
        fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontBold.setFontHeightInPoints((short)8);
        fontBold.setUnderline(HSSFFont.U_NONE);

        HSSFFont fontBoldWhite = wb.createFont();
        fontBoldWhite.setFontName("돋움");
        fontBoldWhite.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontBoldWhite.setColor(palette.findSimilarColor(255, 255, 255).getIndex());
        fontBoldWhite.setFontHeightInPoints((short)8);
        fontBoldWhite.setUnderline(HSSFFont.U_NONE);

        HSSFFont fontNomalLine = wb.createFont();
        fontNomalLine.setFontName("돋움");
        fontNomalLine.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        fontNomalLine.setFontHeightInPoints((short)10);
        fontNomalLine.setUnderline(HSSFFont.U_SINGLE);

        HSSFFont fontSubTitle10 = wb.createFont();
        fontSubTitle10.setFontName("돋움");
        fontSubTitle10.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontSubTitle10.setColor(palette.findSimilarColor(89, 89, 89).getIndex());
        fontSubTitle10.setFontHeightInPoints((short)12);
        fontSubTitle10.setUnderline(HSSFFont.U_NONE);

        HSSFCellStyle styleNoneLine = wb.createCellStyle();
        styleNoneLine.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        styleNoneLine.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        styleNoneLine.getDataFormatString(); //데이타포맷
        styleNoneLine.setBorderTop(HSSFCellStyle.BORDER_NONE);
        styleNoneLine.setBorderBottom(HSSFCellStyle.BORDER_NONE);
        styleNoneLine.setBorderLeft(HSSFCellStyle.BORDER_NONE);
        styleNoneLine.setBorderRight(HSSFCellStyle.BORDER_NONE);
        styleNoneLine.setFont(fontTitle);
        styles.put("htitle", styleNoneLine);

        HSSFCellStyle subTitle = wb.createCellStyle();
        subTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        subTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        subTitle.getDataFormatString(); //데이타포맷
        subTitle.setBorderTop(HSSFCellStyle.BORDER_NONE);
        subTitle.setBorderBottom(HSSFCellStyle.BORDER_NONE);
        subTitle.setBorderLeft(HSSFCellStyle.BORDER_NONE);
        subTitle.setBorderRight(HSSFCellStyle.BORDER_NONE);
        subTitle.setFont(fontNomalLine);
        styles.put("subtitle", subTitle);

        HSSFCellStyle subTitle1 = wb.createCellStyle();
        subTitle1.setAlignment(HSSFCellStyle.ALIGN_LEFT); //가로 정렬
        subTitle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        subTitle1.getDataFormatString(); //데이타포맷
        subTitle1.setBorderTop(HSSFCellStyle.BORDER_NONE);
        subTitle1.setBorderBottom(HSSFCellStyle.BORDER_NONE);
        subTitle1.setBorderLeft(HSSFCellStyle.BORDER_NONE);
        subTitle1.setBorderRight(HSSFCellStyle.BORDER_NONE);
        subTitle1.setFont(fontSubTitle10);
        styles.put("subtitle1", subTitle1);

        HSSFCellStyle blankCell = wb.createCellStyle();
        blankCell.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        blankCell.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        blankCell.getDataFormatString(); //데이타포맷
        blankCell.setBorderTop(HSSFCellStyle.BORDER_NONE);
        blankCell.setBorderBottom(HSSFCellStyle.BORDER_NONE);
        blankCell.setBorderLeft(HSSFCellStyle.BORDER_NONE);
        blankCell.setBorderRight(HSSFCellStyle.BORDER_NONE);
        blankCell.setFont(fontNomal);
        styles.put("blankcell", blankCell);

        HSSFCellStyle companyNm = wb.createCellStyle();
        companyNm.setAlignment(HSSFCellStyle.ALIGN_LEFT); //가로 정렬
        companyNm.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        companyNm.getDataFormatString(); //데이타포맷
        companyNm.setBorderTop(HSSFCellStyle.BORDER_NONE);
        companyNm.setBorderBottom(HSSFCellStyle.BORDER_NONE);
        companyNm.setBorderLeft(HSSFCellStyle.BORDER_NONE);
        companyNm.setBorderRight(HSSFCellStyle.BORDER_NONE);
        companyNm.setFont(fontNomalLine);
        styles.put("companynm", companyNm);

        HSSFCellStyle unitNm = wb.createCellStyle();
        unitNm.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        unitNm.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        unitNm.getDataFormatString(); //데이타포맷
        unitNm.setBorderTop(HSSFCellStyle.BORDER_NONE);
        unitNm.setBorderBottom(HSSFCellStyle.BORDER_NONE);
        unitNm.setBorderLeft(HSSFCellStyle.BORDER_NONE);
        unitNm.setBorderRight(HSSFCellStyle.BORDER_NONE);
        unitNm.setFont(fontNomalLine);
        styles.put("unitnm", unitNm);

        HSSFCellStyle tailNm = wb.createCellStyle();
        tailNm.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        tailNm.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        tailNm.getDataFormatString(); //데이타포맷
        tailNm.setBorderTop(HSSFCellStyle.BORDER_NONE);
        tailNm.setBorderBottom(HSSFCellStyle.BORDER_NONE);
        tailNm.setBorderLeft(HSSFCellStyle.BORDER_NONE);
        tailNm.setBorderRight(HSSFCellStyle.BORDER_NONE);
        tailNm.setFont(fontBoldTitle);
        styles.put("tailnm", tailNm);

        HSSFCellStyle tailValue = wb.createCellStyle();
        tailValue.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        tailValue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        tailValue.setDataFormat(fmt.getFormat("#,##0"));
        tailValue.setBorderTop(HSSFCellStyle.BORDER_NONE);
        tailValue.setBorderBottom(HSSFCellStyle.BORDER_NONE);
        tailValue.setBorderLeft(HSSFCellStyle.BORDER_NONE);
        tailValue.setBorderRight(HSSFCellStyle.BORDER_NONE);
        tailValue.setFont(fontBoldTitle);
        styles.put("tailvalue", tailValue);

        HSSFCellStyle tailValuefloat = wb.createCellStyle();
        tailValuefloat.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        tailValuefloat.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        tailValuefloat.setDataFormat(fmt.getFormat("#,##0.00"));
        tailValuefloat.setBorderTop(HSSFCellStyle.BORDER_NONE);
        tailValuefloat.setBorderBottom(HSSFCellStyle.BORDER_NONE);
        tailValuefloat.setBorderLeft(HSSFCellStyle.BORDER_NONE);
        tailValuefloat.setBorderRight(HSSFCellStyle.BORDER_NONE);
        tailValuefloat.setFont(fontBoldTitle);
        styles.put("tailvaluefloat", tailValuefloat);

        HSSFCellStyle gridTitle = wb.createCellStyle();
        gridTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridTitle.getDataFormatString(); //데이타포맷
        gridTitle.setFillForegroundColor(palette.findSimilarColor(225, 225, 225).getIndex());
        gridTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridTitle.setFont(fontBold);
        styles.put("gridtitle", gridTitle);

        HSSFCellStyle gridTitle1 = wb.createCellStyle();
        gridTitle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridTitle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridTitle1.getDataFormatString(); //데이타포맷
        gridTitle1.setFillForegroundColor(palette.findSimilarColor(255, 242, 204).getIndex());
        gridTitle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridTitle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridTitle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridTitle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridTitle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridTitle1.setFont(fontBold);
        styles.put("gridtitle1", gridTitle1);

        HSSFCellStyle gridTitle2 = wb.createCellStyle();
        gridTitle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridTitle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridTitle2.getDataFormatString(); //데이타포맷
        gridTitle2.setFillForegroundColor(palette.findSimilarColor(226, 239, 218).getIndex());
        gridTitle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridTitle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridTitle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridTitle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridTitle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridTitle2.setFont(fontBold);
        styles.put("gridtitle2", gridTitle2);

        HSSFCellStyle gridTitle3 = wb.createCellStyle();
        gridTitle3.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridTitle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridTitle3.getDataFormatString(); //데이타포맷
        gridTitle3.setFillForegroundColor(palette.findSimilarColor(204, 204, 204).getIndex());
        gridTitle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridTitle3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridTitle3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridTitle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridTitle3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridTitle3.setFont(fontBold);
        styles.put("gridtitle3", gridTitle3);

        HSSFCellStyle gridTitleApricot = wb.createCellStyle();
        gridTitleApricot.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridTitleApricot.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridTitleApricot.getDataFormatString(); //데이타포맷
        gridTitleApricot.setFillForegroundColor(palette.findSimilarColor(255, 216, 177).getIndex());
        gridTitleApricot.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridTitleApricot.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridTitleApricot.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridTitleApricot.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridTitleApricot.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridTitleApricot.setFont(fontNomal);
        styles.put("gridtitleapricot", gridTitleApricot);

        HSSFCellStyle gridTitleGray = wb.createCellStyle();
        gridTitleGray.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridTitleGray.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridTitleGray.getDataFormatString(); //데이타포맷
        gridTitleGray.setFillForegroundColor(palette.findSimilarColor(225, 225, 225).getIndex());
        gridTitleGray.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridTitleGray.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridTitleGray.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridTitleGray.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridTitleGray.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridTitleGray.setFont(fontNomal);
        styles.put("gridtitlegray", gridTitleGray);

        HSSFCellStyle gridCont = wb.createCellStyle();
        gridCont.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridCont.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridCont.getDataFormatString(); //데이타포맷
        gridCont.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridCont.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridCont.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridCont.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridCont.setFont(fontNomal);
        styles.put("gridcont", gridCont);

        HSSFCellStyle gridContCenter = wb.createCellStyle();
        gridContCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridContCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContCenter.getDataFormatString(); //데이타포맷
        gridContCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridContCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridContCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridContCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridContCenter.setFont(fontNomal);
        styles.put("gridcontcenter", gridContCenter);

        HSSFCellStyle gridContCenterDate = wb.createCellStyle();
        gridContCenterDate.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridContCenterDate.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContCenterDate.getDataFormatString(); //데이타포맷
        gridContCenterDate.setDataFormat(fmt.getFormat("####-##-##"));
        gridContCenterDate.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridContCenterDate.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridContCenterDate.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridContCenterDate.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridContCenterDate.setFont(fontNomal);
        styles.put("gridcontcenterdate", gridContCenterDate);

        HSSFCellStyle gridContRight = wb.createCellStyle();
        gridContRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridContRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContRight.getDataFormatString(); //데이타포맷
        gridContRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridContRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridContRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridContRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridContRight.setFont(fontNomal);
        styles.put("gridcontright", gridContRight);

        HSSFCellStyle gridValue = wb.createCellStyle();
        gridValue.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridValue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridValue.setDataFormat(fmt.getFormat("#,##0"));
        gridValue.setBorderTop(HSSFCellStyle.BORDER_NONE);
        gridValue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridValue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridValue.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridValue.setFont(fontNomal);
        styles.put("gridvalue", gridValue);

        HSSFCellStyle gridValuefloat = wb.createCellStyle();
        gridValuefloat.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridValuefloat.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridValuefloat.setDataFormat(fmt.getFormat("#,##0.00"));
        gridValuefloat.setBorderTop(HSSFCellStyle.BORDER_NONE);
        gridValuefloat.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridValuefloat.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridValuefloat.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridValuefloat.setFont(fontNomal);
        styles.put("gridvaluefloat", gridValuefloat);

        HSSFCellStyle gridValueGray = wb.createCellStyle();
        gridValueGray.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridValueGray.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridValueGray.setFillForegroundColor(palette.findSimilarColor(225, 225, 225).getIndex());
        gridValueGray.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridValueGray.setDataFormat(fmt.getFormat("#,##0"));
        gridValueGray.setBorderTop(HSSFCellStyle.BORDER_NONE);
        gridValueGray.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridValueGray.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridValueGray.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridValueGray.setFont(fontBold);
        styles.put("gridvaluegray", gridValueGray);

        HSSFCellStyle gridValuefloatGray = wb.createCellStyle();
        gridValuefloatGray.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridValuefloatGray.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridValuefloatGray.setFillForegroundColor(palette.findSimilarColor(225, 225, 225).getIndex());
        gridValuefloatGray.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridValuefloatGray.setDataFormat(fmt.getFormat("#,##0.00"));
        gridValuefloatGray.setBorderTop(HSSFCellStyle.BORDER_NONE);
        gridValuefloatGray.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridValuefloatGray.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridValuefloatGray.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridValuefloatGray.setFont(fontBold);
        styles.put("gridvaluefloatgray", gridValuefloatGray);

        HSSFCellStyle gridContM = wb.createCellStyle();
        gridContM.setAlignment(HSSFCellStyle.ALIGN_LEFT); //가로 정렬
        gridContM.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContM.setFillForegroundColor(palette.findSimilarColor(255, 242, 204).getIndex());
        gridContM.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridContM.getDataFormatString(); //데이타포맷
        gridContM.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridContM.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridContM.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridContM.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridContM.setFont(fontNomal);
        styles.put("gridcontm", gridContM);

        HSSFCellStyle gridContCenterM = wb.createCellStyle();
        gridContCenterM.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridContCenterM.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContCenterM.setFillForegroundColor(palette.findSimilarColor(255, 242, 204).getIndex());
        gridContCenterM.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridContCenterM.getDataFormatString(); //데이타포맷
        gridContCenterM.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridContCenterM.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridContCenterM.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridContCenterM.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridContCenterM.setFont(fontNomal);
        styles.put("gridcontcenterm", gridContCenterM);

        HSSFCellStyle gridContCenterDateM = wb.createCellStyle();
        gridContCenterDateM.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridContCenterDateM.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContCenterDateM.setFillForegroundColor(palette.findSimilarColor(255, 242, 204).getIndex());
        gridContCenterDateM.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridContCenterDateM.getDataFormatString(); //데이타포맷
        gridContCenterDateM.setDataFormat(fmt.getFormat("####-##-##"));
        gridContCenterDateM.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridContCenterDateM.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridContCenterDateM.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridContCenterDateM.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridContCenterDateM.setFont(fontNomal);
        styles.put("gridcontcenterdatem", gridContCenterDateM);

        HSSFCellStyle gridContRightM = wb.createCellStyle();
        gridContRightM.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridContRightM.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContRightM.setFillForegroundColor(palette.findSimilarColor(255, 242, 204).getIndex());
        gridContRightM.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridContRightM.getDataFormatString(); //데이타포맷
        gridContRightM.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridContRightM.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridContRightM.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridContRightM.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridContRightM.setFont(fontNomal);
        styles.put("gridcontrightm", gridContRightM);

        HSSFCellStyle gridValueM = wb.createCellStyle();
        gridValueM.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridValueM.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridValueM.setFillForegroundColor(palette.findSimilarColor(255, 242, 204).getIndex());
        gridValueM.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridValueM.setDataFormat(fmt.getFormat("#,##0"));
        gridValueM.setBorderTop(HSSFCellStyle.BORDER_NONE);
        gridValueM.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridValueM.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridValueM.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridValueM.setFont(fontNomal);
        styles.put("gridvaluem", gridValueM);

        HSSFCellStyle gridValuefloatM = wb.createCellStyle();
        gridValuefloatM.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridValuefloatM.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridValuefloatM.setFillForegroundColor(palette.findSimilarColor(255, 242, 204).getIndex());
        gridValuefloatM.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridValuefloatM.setDataFormat(fmt.getFormat("#,##0.00"));
        gridValuefloatM.setBorderTop(HSSFCellStyle.BORDER_NONE);
        gridValuefloatM.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridValuefloatM.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridValuefloatM.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridValuefloatM.setFont(fontNomal);
        styles.put("gridvaluefloatm", gridValuefloatM);

        HSSFCellStyle gridContR = wb.createCellStyle();
        gridContR.setAlignment(HSSFCellStyle.ALIGN_LEFT); //가로 정렬
        gridContR.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContR.getDataFormatString(); //데이타포맷
        gridContR.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridContR.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridContR.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridContR.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridContR.setFont(fontNomal);
        styles.put("gridcontr", gridContR);

        HSSFCellStyle gridContCenterR = wb.createCellStyle();
        gridContCenterR.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridContCenterR.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContCenterR.getDataFormatString(); //데이타포맷
        gridContCenterR.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridContCenterR.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridContCenterR.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridContCenterR.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridContCenterR.setFont(fontNomal);
        styles.put("gridcontcenterr", gridContCenterR);

        HSSFCellStyle gridContCenterDateR = wb.createCellStyle();
        gridContCenterDateR.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridContCenterDateR.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContCenterDateR.getDataFormatString(); //데이타포맷
        gridContCenterDateR.setDataFormat(fmt.getFormat("####-##-##"));
        gridContCenterDateR.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridContCenterDateR.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridContCenterDateR.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridContCenterDateR.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridContCenterDateR.setFont(fontNomal);
        styles.put("gridcontcenterdater", gridContCenterDateR);

        HSSFCellStyle gridContRightR = wb.createCellStyle();
        gridContRightR.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridContRightR.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContRightR.getDataFormatString(); //데이타포맷
        gridContRightR.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridContRightR.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridContRightR.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridContRightR.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridContRightR.setFont(fontNomal);
        styles.put("gridcontrightr", gridContRightR);

        HSSFCellStyle gridValueR = wb.createCellStyle();
        gridValueR.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridValueR.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridValueR.setDataFormat(fmt.getFormat("#,##0"));
        gridValueR.setBorderTop(HSSFCellStyle.BORDER_NONE);
        gridValueR.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridValueR.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridValueR.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridValueR.setFont(fontNomal);
        styles.put("gridvaluer", gridValueR);

        HSSFCellStyle gridValuefloatR = wb.createCellStyle();
        gridValuefloatR.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridValuefloatR.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridValuefloatR.setDataFormat(fmt.getFormat("#,##0.00"));
        gridValuefloatR.setBorderTop(HSSFCellStyle.BORDER_NONE);
        gridValuefloatR.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridValuefloatR.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridValuefloatR.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridValuefloatR.setFont(fontNomal);
        styles.put("gridvaluefloatr", gridValuefloatR);

        HSSFCellStyle gridtailContCenter = wb.createCellStyle();
        gridtailContCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridtailContCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridtailContCenter.getDataFormatString(); //데이타포맷
        gridtailContCenter.setFillForegroundColor(palette.findSimilarColor(255, 225, 225).getIndex());
        gridtailContCenter.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridtailContCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridtailContCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridtailContCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridtailContCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridtailContCenter.setFont(fontBold);
        styles.put("gridtailcontCenter", gridtailContCenter);

        HSSFCellStyle gridtailValue = wb.createCellStyle();
        gridtailValue.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridtailValue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridtailValue.setDataFormat(fmt.getFormat("#,##0"));
        gridtailValue.setFillForegroundColor(palette.findSimilarColor(255, 225, 225).getIndex());
        gridtailValue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridtailValue.setBorderTop(HSSFCellStyle.BORDER_NONE);
        gridtailValue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridtailValue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridtailValue.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridtailValue.setFont(fontBold);
        styles.put("gridtailvalue", gridtailValue);

        HSSFCellStyle gridtailValuefloat = wb.createCellStyle();
        gridtailValuefloat.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridtailValuefloat.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridtailValuefloat.setDataFormat(fmt.getFormat("#,##0.00"));
        gridtailValuefloat.setFillForegroundColor(palette.findSimilarColor(255, 225, 225).getIndex());
        gridtailValuefloat.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        gridtailValuefloat.setBorderTop(HSSFCellStyle.BORDER_NONE);
        gridtailValuefloat.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridtailValuefloat.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridtailValuefloat.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridtailValuefloat.setFont(fontBold);
        styles.put("gridtailvaluefloat", gridtailValuefloat);

        HSSFCellStyle gridtailBlank = wb.createCellStyle();
        gridtailBlank.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridtailBlank.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridtailBlank.getDataFormatString(); //데이타포맷
        gridtailBlank.setFillForegroundColor(palette.findSimilarColor(242, 242, 242).getIndex());
        gridtailBlank.setFillPattern(HSSFCellStyle.BORDER_DASH_DOT);
        gridtailBlank.setBorderTop(HSSFCellStyle.BORDER_THIN);
        gridtailBlank.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        gridtailBlank.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        gridtailBlank.setBorderRight(HSSFCellStyle.BORDER_THIN);
        gridtailBlank.setFont(fontNomal);
        styles.put("gridtailblank", gridtailBlank);

        HSSFCellStyle gridContNone = wb.createCellStyle();
        gridContNone.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridContNone.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContNone.getDataFormatString(); //데이타포맷
        gridContNone.setFont(fontNomal);
        styles.put("gridcontnone", gridContNone);

        HSSFCellStyle gridContNoneCenter = wb.createCellStyle();
        gridContNoneCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridContNoneCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContNoneCenter.getDataFormatString(); //데이타포맷
        gridContNoneCenter.setFont(fontNomal);
        styles.put("gridcontnonecenter", gridContNoneCenter);

        HSSFCellStyle gridContNoneCenterDate = wb.createCellStyle();
        gridContNoneCenterDate.setAlignment(HSSFCellStyle.ALIGN_CENTER); //가로 정렬
        gridContNoneCenterDate.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContNoneCenterDate.getDataFormatString(); //데이타포맷
        gridContNoneCenterDate.setDataFormat(fmt.getFormat("####-##-##"));
        gridContNoneCenterDate.setFont(fontNomal);
        styles.put("gridcontnonecenterdate", gridContNoneCenterDate);

        HSSFCellStyle gridContNoneRight = wb.createCellStyle();
        gridContNoneRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridContNoneRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridContNoneRight.getDataFormatString(); //데이타포맷
        gridContNoneRight.setFont(fontNomal);
        styles.put("gridcontnoneright", gridContNoneRight);

        HSSFCellStyle gridNoneValue = wb.createCellStyle();
        gridNoneValue.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridNoneValue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridNoneValue.setDataFormat(fmt.getFormat("#,##0"));
        gridNoneValue.setFont(fontNomal);
        styles.put("gridnonevalue", gridNoneValue);

        HSSFCellStyle gridNoneValuefloat = wb.createCellStyle();
        gridNoneValuefloat.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //가로 정렬
        gridNoneValuefloat.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //가로 정렬
        gridNoneValuefloat.setDataFormat(fmt.getFormat("#,##0.00"));
        gridNoneValuefloat.setFont(fontNomal);
        styles.put("gridnonevaluefloat", gridNoneValuefloat);
        return styles;
    }
}
