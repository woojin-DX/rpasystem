/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 납품장소 DB에서 Data를 가져와서 Model에 생성 
 * 소  스  명 : PlaceDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
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

@Repository("placeDAO")
public class PlaceDAO extends AbstractDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listPlace(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<PlaceVO> lsListTable = new ArrayList<PlaceVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("listPlace", paramMap);
            //데이타 총갯수를 추출.
            int noOfRecords = localSqlSession.selectOne("listCountPlace", paramMap);
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
     * 함수  제목 : 납품장소 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
     * 내      용 : 입력시 PK중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int overlabCountPlace(Map<String, Object> paramMap) {
        return (int) selectOne("overlabCntPlace", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
     * 내      용 : 데이타 입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int insertPlace(Map<String, Object> paramMap) {
        return (int) update("insertPlace", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updatePlace(Map<String, Object> paramMap) {
        return (int) update("updatePlace", paramMap);
    }

}

