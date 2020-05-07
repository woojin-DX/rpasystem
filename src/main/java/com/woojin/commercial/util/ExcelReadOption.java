/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : ExcelReadOption
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReadOption {
    /**
     * 엑셀파일의 경로
     */
    private String filePath;
    private String sheetName;
    private int sheetNum;
    private byte[] byteFile;

    /**
     * 추출할 컬럼 명
     */
    private List<String> outputColumns;
    private Map<String, Object> outputObjColumns;
    private Map<String, String> outputHeadColumns;

    /**
     * 추출을 시작할 행 번호
     */
    private int startRow;

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSheetName() {
        return sheetName;
    }
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getSheetNum() {
        return sheetNum;
    }
    public void setSheetNum(int sheetNum) {
        this.sheetNum = sheetNum;
    }

    public byte[] getFileByte() {
        return byteFile;
    }
    public void setFileByte(byte[] byteFile) {
        this.byteFile = byteFile;
    }

    public List<String> getOutputColumns() {

        List<String> temp = new ArrayList<String>();
        temp.addAll(outputColumns);

        return temp;
    }
    public void setOutputColumns(List<String> outputColumns) {

        List<String> temp = new ArrayList<String>();
        temp.addAll(outputColumns);

        this.outputColumns = temp;
    }

    public void setOutputColumns(String ... outputColumns) {

        if(this.outputColumns == null) {
            this.outputColumns = new ArrayList<String>();
        }

        for(String ouputColumn : outputColumns) {
            this.outputColumns.add(ouputColumn);
        }
    }

    public Map<String, Object> getOutputObjColumns() {

        Map<String, Object> temp = new HashMap<String, Object>();
        temp.putAll(outputObjColumns);

        return temp;
    }
    public void setOutputObjColumns(Map<String, Object> outputObjColumns) {

        Map<String, Object> temp = new HashMap<String, Object>();
        temp.putAll(outputObjColumns);

        this.outputObjColumns = temp;
    }

    public Map<String, String> getOutputHeadColumns() {

        Map<String, String> temp = new HashMap<String,String>();
        temp.putAll(outputHeadColumns);

        return temp;
    }

    public void setOutputHeadColumns(Map<String, String> outputHeadColumns) {

        Map<String, String> temp = new HashMap<String,String>();
        temp.putAll(outputHeadColumns);

        this.outputHeadColumns = temp;
    }

    public int getStartRow() {
        return startRow;
    }
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
}
