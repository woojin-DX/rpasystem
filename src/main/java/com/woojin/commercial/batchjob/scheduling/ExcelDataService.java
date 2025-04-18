package com.woojin.commercial.batchjob.scheduling;

import java.util.Map;

public interface ExcelDataService {
    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> listExcelData() throws Exception;
    
    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> listExcelData1() throws Exception;

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> listExcelDataSum() throws Exception;

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    int autoSupplyCheck() throws Exception;
    
    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> listElectricStatic() throws Exception;
    


}
