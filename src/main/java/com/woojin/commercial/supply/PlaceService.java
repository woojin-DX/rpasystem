/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 납품장소  서비스에 대한 인터페이스 
 * 소  스  명 : PlaceDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.supply;

import com.woojin.commercial.common.CommandMap;
import java.util.Map;

public interface PlaceService {

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 목록
     * 작  성  자 : 가치노을       작  성  일 : 2020-04-09
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> listPlace(CommandMap commandMap) throws Exception;

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 입력
     * 작  성  자 : 가치노을       작  성  일 : 2020-04-09
     * 내      용 : 데이타 단일입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> insertPlace(CommandMap commandMap) throws Exception;

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 수정
     * 작  성  자 : 가치노을       작  성  일 : 2020-04-09
     * 내      용 : 조건에 따른 단일 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    Map<String, Object> updatePlace(CommandMap commandMap) throws Exception;

}

