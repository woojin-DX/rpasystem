/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 출하정보 DB에서 Data를 가져와서 Model에 생성 
 * 소  스  명 : ShippingDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.shipping;

import com.googlecode.ehcache.annotations.Cacheable;
import com.woojin.commercial.supply.MeterialNumVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.woojin.commercial.common.AbstractDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("shippingDAO")
public class ShippingDAO extends AbstractDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listShipping(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ShippingVO> lsListTable = new ArrayList<ShippingVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("listShipping", paramMap);
            //데이타 총갯수를 추출.
            int noOfRecords = localSqlSession.selectOne("listCountShipping", paramMap);
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
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listShippingExcel(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ShippingVO> lsListTable = new ArrayList<ShippingVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listShippingExcel", paramMap);
            resultMap.put("datalist", lsListTable);
        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listShippingCfm(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ShippingVO> lsListTable = new ArrayList<ShippingVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("listShippingCfm", paramMap);
            //데이타 총갯수를 추출.
            int noOfRecords = localSqlSession.selectOne("listCountShippingCfm", paramMap);
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
     * 함수  제목 : 출하정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public ShippingVO detailShipping(Map<String, Object> paramMap) {
        return (ShippingVO) selectOne("detailShipping", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록에 대한 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int totalCountShipping(Map<String, Object> paramMap) {
        return (int) selectOne("totalCountShipping", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int overlabCountShipping(Map<String, Object> paramMap) {
        return (int) selectOne("overlabCntShipping", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public List<ShippingVO> overlabListShipping(Map<String, Object> paramMap) {
        return (List<ShippingVO>) selectList("overlabListShipping", paramMap);
    }


    /* *******************************************************************************************
     * 함수  제목 : 출하정보 순차번호 최고값
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int getShippingSum(Map<String, Object> paramMap) {
        return (int) selectOne("getShippingSum", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 순차번호 최고값
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int getShippingMaxSeq(Map<String, Object> paramMap) {
        return (int) selectOne("getShippingMaxSeq", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int insertShipping(Map<String, Object> paramMap) {
        return (int) update("insertShipping", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateShipping(Map<String, Object> paramMap) {
        return (int) update("updateShipping", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateShippingOffer(Map<String, Object> paramMap) {
        return (int) update("updateShippingOffer", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int deleteShipping(Map<String, Object> paramMap) {
        return (int) delete("deleteShipping", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateSupplyReceipt(Map<String, Object> paramMap) {
        return (int) update("updateSupplyReceipt", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 자재코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Cacheable(cacheName="budgetCache")
    public Map<String, Object> listMaterialAll(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listMaterialAll", paramMap);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int remainQty(Map<String, Object> paramMap) {
        return (int) selectOne("remainQty", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int remainMTMQty(Map<String, Object> paramMap) {
        return (int) selectOne("remainMTMQty", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int selectExcReaminAllQty(Map<String, Object> paramMap) {
        return (int) selectOne("selectExcReaminAllQty", paramMap);
    }
    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int selectExcRealReaminInvenQty(Map<String, Object> paramMap) {
        return (int) selectOne("selectExcRealReaminInvenQty", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int selectExcRealReaminMtmQty(Map<String, Object> paramMap) {
        return (int) selectOne("selectExcRealReaminMtmQty", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listShippingSum(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ShippingVO> lsListTable = new ArrayList<ShippingVO>();
        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listShippingSum", paramMap);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateShippingPross() {
        return (int) update("updateShippingPross", "");
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateShippingSupplyPross() {
        return (int) update("updateShippingPross", "");
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateShippingOrderPross() {
        return (int) update("updateShippingPross", "");
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listShippingCfmAddr(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ShippingVO> lsListTable = new ArrayList<ShippingVO>();
        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listShippingCfmAddr", paramMap);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }
    
    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateShipping_method(Map<String, Object> paramMap) {
        return (int) update("updateShipping_method", paramMap);
    }
    
    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listShippingPsv(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ShippingVO> lsListTable = new ArrayList<ShippingVO>();
        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listShippingPsv", paramMap);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }
    
    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int multiUpdateShippingSupply(Map<String, Object> paramMap) {
        return (int) update("multiUpdateShippingSupply", paramMap);
    }
    
    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int execureProcedureProcess() {
    	return (int)update("execureProcedureProcess","");
    }
    
    
}

