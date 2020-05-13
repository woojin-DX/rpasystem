/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : LoginDAO
 *   @Description   :
 *   @Author        : GACHINOEL
 *   @Version       : v1.0
 *   Copyright(c) 2019 WOOJIN All rights reserved
 *   ------------------------------------------------------------------------------
 *                    변         경         사         항
 *   ------------------------------------------------------------------------------
 *      DATE           AUTHOR                       DESCRIPTION
 *   ---------------  ----------    ------------------------------------------------
 *   2019.11.14       gachinoel     신규생성
 *   ------------------------------------------------------------------------------
 */

package com.woojin.commercial.login;

import com.woojin.commercial.common.AbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("loginDAO")
public class LoginDAO extends AbstractDAO {
    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 상세내역
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public LoginVO detailLogin(Map<String, Object> paramMap) {
        return (LoginVO) selectOne("detailLogin", paramMap);
    }
    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 수정
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateLogin(Map<String, Object> paramMap) {
        return (int) update("updateLogin", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 자동로그인 세션ID 저장
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int keepLogin(Map<String, Object> paramMap) {
        return (int) update("keepLogin", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 쿠키 유무에 따른 로그인 정보 조회
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public HashMap<String, Object> checkUserInfoWithCookie(HashMap<String, Object> paramMap) {
        return (HashMap<String, Object>) selectOne("checkUserInfoWithCookie", paramMap);
    }
}
