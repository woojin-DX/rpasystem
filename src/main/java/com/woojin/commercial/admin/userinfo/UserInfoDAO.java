/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 사용자정보 DB에서 Data를 가져와서 Model에 생성 
 * 소  스  명 : UserInfoDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.admin.userinfo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.woojin.commercial.common.AbstractDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userInfoDAO")
public class UserInfoDAO extends AbstractDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listUserInfo(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<UserInfoVO> lsListTable = new ArrayList<UserInfoVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("listUserInfo", paramMap);
            //데이타 총갯수를 추출.
            int noOfRecords = localSqlSession.selectOne("listCountUserInfo", paramMap);
            resultMap.put("datalist", lsListTable);
            resultMap.put("totalcnt", noOfRecords);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
            resultMap.put("totalcnt", 0);
        } finally {
            localSqlSession.close();
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public UserInfoVO detailUserInfo(Map<String, Object> paramMap) {
        return (UserInfoVO) selectOne("detailUserInfo", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록에 대한 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int totalCountUserInfo(Map<String, Object> paramMap) {
        return (int) selectOne("totalCountUserInfo", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int overlabCountUserInfo(Map<String, Object> paramMap) {
        return (int) selectOne("overlabCntUserInfo", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public List<UserInfoVO> overlabListUserInfo(Map<String, Object> paramMap) {
        return (List<UserInfoVO>) selectList("overlabListUserInfo", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int insertUserInfo(Map<String, Object> paramMap) {
        return (int) update("insertUserInfo", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateUserInfo(Map<String, Object> paramMap) {
        return (int) update("updateUserInfo", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int deleteUserInfo(Map<String, Object> paramMap) {
        return (int) delete("deleteUserInfo", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 다중입력/수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 다중 입력 및 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int multiInsertUpdateUserInfo(Map<String, Object> paramMap) {
        return (int) update("multiInsertUpdateUserInfo", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 다중완전 삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 선택된 데이타 다중 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int multiIDeleteUserInfo(Map<String, Object> paramMap) {
        return (int) delete("multiIDeleteUserInfo", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listCompany(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<CompanyVO> lsListTable = new ArrayList<CompanyVO>();


        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listCompany", paramMap);
            resultMap.put("companylist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("companylist", lsListTable);
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 다중입력/수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 다중 입력 및 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int insertUpdateCallUserInfo(Map<String, Object> paramMap) {
        return (int) update("callUserinfo", paramMap);
    }

}

