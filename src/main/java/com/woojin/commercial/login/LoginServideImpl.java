/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : LoginServideImpl
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.login.LoginService;

import javax.annotation.Resource;
import java.util.HashMap;

@Service("loginService")
public class LoginServideImpl implements LoginService {
    // 상품코드 쿼리로그 추출
	Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource(name="loginDAO")
    private LoginDAO loginDAO;

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 상세내역
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public LoginVO detailLogin(CommandMap commandMap)  throws Exception {
        return loginDAO.detailLogin(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 수정
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public int updateLogin(CommandMap commandMap) throws Exception {
        return loginDAO.updateLogin(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 자동로그인 세션ID 저장
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public int keepLogin(CommandMap commandMap) throws Exception {
        return loginDAO.keepLogin(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 쿠키 유무에 따른 로그인 정보 조회
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public HashMap<String, Object> checkUserInfoWithCookie(CommandMap commandMap)  throws Exception {
        return loginDAO.checkUserInfoWithCookie(commandMap.getHashMap());
    }
}
