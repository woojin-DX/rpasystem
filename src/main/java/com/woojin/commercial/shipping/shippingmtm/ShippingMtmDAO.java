/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : mtm정보 DB에서 Data를 가져와서 Model에 생성 
 * 소  스  명 : ShippingMtmDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.shipping.shippingmtm;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.woojin.commercial.common.AbstractDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("shippingMtmDAO")
public class ShippingMtmDAO extends AbstractDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listShippingMtm(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ShippingMtmVO> lsListTable = new ArrayList<ShippingMtmVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("listShippingMtm", paramMap);
            //데이타 총갯수를 추출.
            int noOfRecords = localSqlSession.selectOne("listCountShippingMtm", paramMap);
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
     * 함수  제목 : mtm정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public ShippingMtmVO detailShippingMtm(Map<String, Object> paramMap) {
        return (ShippingMtmVO) selectOne("detailShippingMtm", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록에 대한 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int totalCountShippingMtm(Map<String, Object> paramMap) {
        return (int) selectOne("totalCountShippingMtm", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int overlabCountShippingMtm(Map<String, Object> paramMap) {
        return (int) selectOne("overlabCntShippingMtm", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public List<ShippingMtmVO> overlabListShippingMtm(Map<String, Object> paramMap) {
        return (List<ShippingMtmVO>) selectList("overlabListShippingMtm", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int insertShippingMtm(Map<String, Object> paramMap) {
        return (int) update("insertShippingMtm", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateShippingMtm(Map<String, Object> paramMap) {
        return (int) update("updateShippingMtm", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int deleteShippingMtm(Map<String, Object> paramMap) {
        return (int) delete("deleteShippingMtm", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listMaterialMtm(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ShippingMtmVO> lsListTable = new ArrayList<ShippingMtmVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listMaterialMtm", paramMap);
            resultMap.put("datalist", lsListTable);
        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int multiInsertUpdateShippingMtm(Map<String, Object> paramMap) {
        return (int) update("multiInsertUpdateShippingMtm", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listShippingMtmCfm(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ShippingMtmVO> lsListTable = new ArrayList<ShippingMtmVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("listShippingMtmCfm", paramMap);
            //데이타 총갯수를 추출.
            int noOfRecords = localSqlSession.selectOne("listCountShippingMtmCfm", paramMap);
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
     * 함수  제목 : mtm정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listMaterialLoc(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ShippingMtmVO> lsListTable = new ArrayList<ShippingMtmVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listMaterialLoc", paramMap);
            resultMap.put("datalist", lsListTable);
        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }
}

