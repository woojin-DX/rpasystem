/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 발주정보 DB에서 Data를 가져와서 Model에 생성 
 * 소  스  명 : SupplyDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
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

@Repository("supplyDAO")
public class SupplyDAO extends AbstractDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listSupply(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<SupplyVO> lsListTable = new ArrayList<SupplyVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("selectSupply", paramMap);
            //데이타 총갯수를 추출.
            int noOfRecords = localSqlSession.selectOne("listCountSupply", paramMap);
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
     * 함수  제목 : 발주정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public SupplyVO detailSupply(Map<String, Object> paramMap) {
        return (SupplyVO) selectOne("detailSupply", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록에 대한 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int totalCountSupply(Map<String, Object> paramMap) {
        return (int) selectOne("totalCountSupply", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int overlabCountSupply(Map<String, Object> paramMap) {
        return (int) selectOne("overlabCntSupply", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public List<SupplyVO> overlabListSupply(Map<String, Object> paramMap) {
        return (List<SupplyVO>) selectList("overlabListSupply", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int getShippingMaxSeq(Map<String, Object> paramMap) {
        return (int) selectOne("getShippingMaxSeq", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int insertSupply(Map<String, Object> paramMap) {
        return (int) update("insertSupply", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateSupply(Map<String, Object> paramMap) {
        return (int) update("updateSupply", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int deleteSupply(Map<String, Object> paramMap) {
        return (int) delete("deleteSupply", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int deleteSupplyShipping(Map<String, Object> paramMap) {
        return (int) delete("deleteSupplyShipping", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int insertShippingFirst(Map<String, Object> paramMap) {
        return (int) update("insertShippingFirst", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int multiUpdateShippingRecive(Map<String, Object> paramMap) {
        return (int) update("multiUpdateShippingRecive", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateSupplyRecive(Map<String, Object> paramMap) {
        return (int) update("updateSupplyRecive", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 자재코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listMaterialNum(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listMaterialNum", paramMap);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public MeterialNumVO detailMaterialNum(Map<String, Object> paramMap) {
        return (MeterialNumVO) selectOne("detailMaterialNum", paramMap);
    }


    /* *******************************************************************************************
     * 함수  제목 : 자재코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listSupplyConfirm(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listSupplyConfirm", paramMap);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 자재코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listSupplyResheet(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listSupplyResheet", paramMap);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 자재코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listSupplyResheetDetail(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listSupplyResheetDetail", paramMap);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 자재코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listMaterialOrderAll(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listMaterialOrderAll", paramMap);
            resultMap.put("materiallist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("materiallist", lsListTable);
        }

        return resultMap;
    }
    
    /* *******************************************************************************************
     * 함수  제목 : 자재코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listSupplyHistory(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<SupplyVO> lsListTable = new ArrayList<SupplyVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listSupplyHistory", paramMap);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }
    
    /* *******************************************************************************************
     * 함수  제목 : 발주업체,특정 품번에 대한 Material 리스트
     * 작  성  자  : 손채원        		작  성  일 : 2025-04-17
     * 내      용   : 발주업체, 품번으로 Material 정보 조회 (단가 및 효력 시작일/종료일 추출용)
     * 수  정  자  :             	수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listDateMaterial(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listDateMaterial", paramMap);
            resultMap.put("listDateMaterial", lsListTable);
            /*System.out.println("=========DAO : " + lsListTable.get(0).getDatab() + "============");	
            System.out.println("=========DAO : " + lsListTable.get(0).getKnumh() + "============");	
            System.out.println("=========DAO : " + lsListTable.get(0).getMaterial_num() + "============");	
            System.out.println("=========DAO : " + lsListTable.get(0).getUnit_price() + "============");*/	
        }
        catch(Exception e){
            resultMap.put("listDateMaterial", lsListTable);
        }

        return resultMap;
    }
    
}

