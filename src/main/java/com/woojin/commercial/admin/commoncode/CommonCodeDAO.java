/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 공통코드 DB에서 Data를 가져와서 Model에 생성 
 * 소  스  명 : CommonCodeDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.admin.commoncode;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.woojin.commercial.common.AbstractDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("commonCodeDAO")
public class CommonCodeDAO extends AbstractDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */

    public Map<String, Object> listCommonCode(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<CommonCodeVO> lsListTable = new ArrayList<CommonCodeVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("listCommonCode", paramMap);
            //데이타 총갯수를 추출.
            int noOfRecords = localSqlSession.selectOne("listCountCommonCode", paramMap);
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
     * 함수  제목 : 공통코드 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public CommonCodeVO detailCommonCode(Map<String, Object> paramMap) {
        return (CommonCodeVO) selectOne("detailCommonCode", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록에 대한 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int totalCountCommonCode(Map<String, Object> paramMap) {
        return (int) selectOne("totalCountCommonCode", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int overlabCountCommonCode(Map<String, Object> paramMap) {
        return (int) selectOne("overlabCntCommonCode", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public List<CommonCodeVO> overlabListCommonCode(Map<String, Object> paramMap) {
        return (List<CommonCodeVO>) selectList("overlabListCommonCode", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 정렬순서 최고값
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-31
     * 내      용 : 데이타 입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int getCommonCodeMaxSeq(Map<String, Object> paramMap) {
        return (int) selectOne("getCommonCodeMaxSeq", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int insertCommonCode(Map<String, Object> paramMap) {
        return (int) update("insertCommonCode", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateCommonCode(Map<String, Object> paramMap) {
        return (int) update("updateCommonCode", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 정렬순서 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-31
     * 내      용 : 데이타 입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int setCommonCodeSeqModify(Map<String, Object> paramMap) {
        return (int) update("setCommonCodeSeqModify", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 정렬순서 전체 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-31
     * 내      용 : 데이타 입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int setCommonCodeSeqReset(Map<String, Object> paramMap) {
        return (int) update("setCommonCodeSeqReset", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int deleteCommonCode(Map<String, Object> paramMap) {
        return (int) delete("deleteCommonCode", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int multiDeleteCommonCode(Map<String, Object> paramMap) {
        return (int) delete("deleteCommonCode", paramMap);
    }

}

