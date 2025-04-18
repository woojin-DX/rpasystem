/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : ExcelCellRef
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelCellRef {
    /**
     * Cell에 해당하는 Column Name을 가젼온다(A,B,C..)
     * 만약 Cell이 Null이라면 int cellIndex의 값으로
     * Column Name을 가져온다.
     * @param cell
     * @param cellIndex
     * @return
     */
    public static String getName(Cell cell, int cellIndex) {
        int cellNum = 0;
        if(cell != null) {
            cellNum = cell.getColumnIndex();
        }
        else {
            cellNum = cellIndex;
        }

        return CellReference.convertNumToColString(cellNum);
    }

    public static String getValue(Workbook wb, Cell cell) {
        String value = "";

        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

        if(cell == null) {
            value = "";
        }
        switch(cell.getCellType()) {
            case Cell.CELL_TYPE_FORMULA :
                if(!(cell.toString()=="") ) {
                    if (evaluator.evaluateFormulaCell(cell) == 0) {
                        double fddata = cell.getNumericCellValue();
                        value = String.valueOf(fddata);
                    } else if (evaluator.evaluateFormulaCell(cell) == 1) {
                        value = cell.getStringCellValue();
                    } else if (evaluator.evaluateFormulaCell(cell) == 4) {
                        boolean fbdata = cell.getBooleanCellValue();
                        value = String.valueOf(fbdata);
                    }
                    else{
                        value = "";
                    }
                }
                else {
                    value = "";
                }
                break;

            case Cell.CELL_TYPE_NUMERIC :
                if(DateUtil.isCellDateFormatted(cell)){
                    Date date = cell.getDateCellValue();
                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                }else{
                    Object obj= cell.getNumericCellValue();

                    if(isInteger(obj.toString())){
                        value = Integer.toString((int)cell.getNumericCellValue());
                    }else{

                        value = String.format("%.2f", (float)cell.getNumericCellValue());
                    }
                }

                break;

            case Cell.CELL_TYPE_STRING :
                value = cell.getStringCellValue();
                if (validationDate(value)){
                    value = value.replaceAll("/","-");
                }
                if (isInteger(value)) {
                    value = Long.toString(Long.parseLong(value.toString()));
                } else if (isDouble(value)) {

                    value = String.format("%.2f", Double.parseDouble(value.toString()));
                }
                break;

            case Cell.CELL_TYPE_BOOLEAN :
                value = cell.getBooleanCellValue() + "";
                break;

            case Cell.CELL_TYPE_BLANK :
                value = "";
                break;

            case Cell.CELL_TYPE_ERROR :
                value = cell.getErrorCellValue() + "";
                break;
            default:
                value = cell.getStringCellValue();
        }
        return value;
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

    public static boolean isInteger(String num)
    {
        char tempCh;
        int dotCount = 0;   //실수일 경우 .의 개수를 체크하는 변수
        boolean result = true;

        try
        {
            Double.parseDouble(num);

            //NumberFormat f = NumberFormat.getInstance();
            //f.setGroupingUsed(false);

            for (int i=0; i<num.length(); i++){
                tempCh= num.charAt(i);    //입력받은 문자열을 문자단위로 검사
                // 아스키 코드 값이 48 ~ 57사이면 0과 9사이의 문자이다.
                if ((int)tempCh < 48 || (int)tempCh > 57){
                    //만약 0~9사이의 문자가 아닌 tempCh가 .도 아니거나
                    //.의 개수가 이미 1개 이상이라면 그 문자열은 숫자가 아니다.
                    if(tempCh!='.' || dotCount > 0){
                        if (dotCount != 1 && tempCh != 'E')
                            result = false;
                        break;
                    }else{
                        //.일 경우 .개수 증가
                        dotCount++;
                    }
                }
            }
            if (dotCount == 1) {
                Double dNum = Double.parseDouble(num);
                DecimalFormat df = new DecimalFormat("################.##");
                num = df.format(dNum);
                Long.parseLong(num);    // int 형으로 변환해보고
            }
            else {
                Long.parseLong(num);    // int 형으로 변환해보고
            }
            return result;                      // 이상없으면 true를 리턴
        }
        catch (NumberFormatException e)
        {
            return false;                    // 이상 있으면 false를 리턴
        }
    }

    // 이 함수는 string이 double형 인지를 판별, 원리는 isInteger 메써드와 같음
    public static boolean isDouble(String num)
    {
        char tempCh;
        int dotCount = 0;   //실수일 경우 .의 개수를 체크하는 변수
        boolean result = false;

        try
        {
            Double.parseDouble(num);

            for (int i=0; i<num.length(); i++){
                tempCh= num.charAt(i);    //입력받은 문자열을 문자단위로 검사
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
            if (dotCount == 1) result = true;


            return result;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    /** 입력 date가 yyyy-MM-dd 형태로 들어옴 */
    public  static boolean  validationDate(String checkDate){

        try{
            checkDate = checkDate.replaceAll("/","-");
            SimpleDateFormat  dateFormat = new  SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            dateFormat.parse(checkDate);
            return  true;
        }catch (ParseException  e){
            return  false;
        }

    }
}
