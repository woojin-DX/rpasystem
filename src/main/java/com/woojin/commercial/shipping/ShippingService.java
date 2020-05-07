/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 출하정보  서비스에 대한 인터페이스 
 * 소  스  명 : ShippingDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.shipping;

import com.woojin.commercial.common.CommandMap;
import java.util.Map;

public interface ShippingService {

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> listShipping(CommandMap commandMap) throws Exception;

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 상세내역
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> detailShipping(CommandMap commandMap) throws Exception;

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 전체레코드수
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 전체 레코드갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    int totalCountShipping(CommandMap commandMap) throws Exception;

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 중복여부
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    int overlabCountShipping(CommandMap commandMap) throws Exception;

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 중복목록
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 중복데이타 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> overlabListShipping(CommandMap commandMap) throws Exception;

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 입력
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 데이타 단일입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> insertShipping(CommandMap commandMap) throws Exception;

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> updateShipping(CommandMap commandMap) throws Exception;

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 완전삭제
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> deleteShipping(CommandMap commandMap) throws Exception;

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> listShippingCfmAddr(CommandMap commandMap) throws Exception;
    
    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을       작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> updateShipping_method(CommandMap commandMap) throws Exception;
    

}

