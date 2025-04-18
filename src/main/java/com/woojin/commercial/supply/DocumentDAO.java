/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 :  DB에서 Data를 가져와서 Model에 생성 
 * 소  스  명 : DocumentDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.supply;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.woojin.commercial.common.AbstractDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("documentDAO")
public class DocumentDAO extends AbstractDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /* *******************************************************************************************
     * 함수  제목 :  목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listDocument(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<DocumentVO> lsListTable = new ArrayList<DocumentVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("listDocument", paramMap);
            //데이타 총갯수를 추출.
            int noOfRecords = localSqlSession.selectOne("listCountDocument", paramMap);
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
     * 함수  제목 :  상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 해당 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public DocumentVO detailDocument(Map<String, Object> paramMap) {
        return (DocumentVO) selectOne("detailDocument", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 :  전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 전체 목록에 대한 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int totalCountDocument(Map<String, Object> paramMap) {
        return (int) selectOne("totalCountDocument", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 :  중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 입력시 PK중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int overlabCountDocument(Map<String, Object> paramMap) {
        return (int) selectOne("overlabCntDocument", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 :  중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 입력시 PK중복 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public List<DocumentVO> overlabListDocument(Map<String, Object> paramMap) {
        return (List<DocumentVO>) selectList("overlabListDocument", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 :  입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 데이타 입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int insertDocument(Map<String, Object> paramMap) {
        return (int) update("insertDocument", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 :  수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateDocument(Map<String, Object> paramMap) {
        return (int) update("updateDocument", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 :  완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
     * 내      용 : 조건에 따른 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int deleteDocument(Map<String, Object> paramMap) {
        return (int) delete("deleteDocument", paramMap);
    }

}

