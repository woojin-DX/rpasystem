/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 발주정보 DB에서 Data를 가져와서 Model에 생성 
 * 소  스  명 : webDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woojin.commercial.common.AbstractDAO;
import com.woojin.commercial.supply.MeterialNumVO;

@Repository("webDAO")
public class webDAO extends AbstractDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listweb(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<webVO> lsListTable = new ArrayList<webVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("selectweb", paramMap);
            //데이타 총갯수를 추출.
            int noOfRecords = localSqlSession.selectOne("listCountweb", paramMap);
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
    public webVO detailweb(Map<String, Object> paramMap) {
        return (webVO) selectOne("detailweb", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록에 대한 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int totalCountweb(Map<String, Object> paramMap) {
        return (int) selectOne("totalCountweb", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int overlabCountweb(Map<String, Object> paramMap) {
        return (int) selectOne("overlabCntweb", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public List<webVO> overlabListweb(Map<String, Object> paramMap) {
        return (List<webVO>) selectList("overlabListweb", paramMap);
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
    public int insertweb(Map<String, Object> paramMap) {
        return (int) update("insertweb", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int updateweb(Map<String, Object> paramMap) {
        return (int) update("updateweb", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int deleteweb(Map<String, Object> paramMap) {
        return (int) delete("deleteweb", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int deletewebShipping(Map<String, Object> paramMap) {
        return (int) delete("deletewebShipping", paramMap);
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
    public int updatewebRecive(Map<String, Object> paramMap) {
        return (int) update("updatewebRecive", paramMap);
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
	public Map<String, Object> listwebConfirm(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listwebConfirm", paramMap);
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
	public Map<String, Object> listwebResheet(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listwebResheet", paramMap);
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
	public Map<String, Object> listwebResheetDetail(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listwebResheetDetail", paramMap);
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
	public Map<String, Object> listwebHistory(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<webVO> lsListTable = new ArrayList<webVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listwebHistory", paramMap);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }
    
}

