/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : ExcelFileType
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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelFileType {
    /**
     * --------------------------------------------------------------------
     * @descript : 엑셀파일을 읽어서 Workbook 객체에 리턴한다.
     *               XLS와 XLSX 확장자를 비교한다.
     * @author   : gachinoel
     * @version 1.0.0
     * @since 2019-10-28 오후 2:21
     * ------------------------------------------------------------------------------
     *                   변         경         사         항
     * ------------------------------------------------------------------------------
     *    DATE           AUTHOR                       DESCRIPTION
     * ---------------  ----------    ------------------------------------------------
     * 2019-10-28       gachinoel     신규생성
     * ------------------------------------------------------------------------------
    **/
    public static Workbook getWorkbook(String filePath, byte[] byteFile) {

        /*
         * FileInputStream은 파일의 경로에 있는 파일을
         * 읽어서 Byte로 가져온다.
         *
         * 파일이 존재하지 않는다면은
         * RuntimeException이 발생된다.
         */
        Workbook wb = null;

        InputStream fis = new ByteArrayInputStream(byteFile);


        /*
         * 파일의 확장자를 체크해서 .XLS 라면 HSSFWorkbook에
         * .XLSX라면 XSSFWorkbook에 각각 초기화 한다.
         */
        if(filePath.toUpperCase().endsWith(".XLS")) {
            try {
                wb = new HSSFWorkbook(fis);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        else if(filePath.toUpperCase().endsWith(".XLSX")) {
            try {
                wb = new XSSFWorkbook(fis);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return wb;

    }
}
