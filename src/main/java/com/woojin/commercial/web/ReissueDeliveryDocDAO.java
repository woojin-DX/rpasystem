/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 발주정보 DB에서 Data를 가져와서 Model에 생성 
 * 소  스  명 : DeliveryDocDAO
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
import com.woojin.commercial.supply.DocumentVO;
import com.woojin.commercial.supply.MeterialNumVO;

@Repository("ReissueDeliveryDocDAO")
public class ReissueDeliveryDocDAO extends AbstractDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listReissueDeliveryDoc(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<DeliveryDocVO> lsListTable = new ArrayList<DeliveryDocVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("selectReissueDeliveryDoc", paramMap);
            //데이타 총갯수를 추출.
            int noOfRecords = localSqlSession.selectOne("listCountReissueDeliveryDoc", paramMap);
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
     * 함수  제목 : 
     * 작  성  자 :       작  성  일 : 
     * 내      용 : 
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    
    public Map<String, Object> listReissueDeliveryDocDelvn(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<DeliveryDocVO> lsListTable = new ArrayList<DeliveryDocVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("ReissueDeliveryDocDelvn", paramMap);
            resultMap.put("datalist", lsListTable);
            paramMap.put("DOC_NUM", lsListTable.get(0));

        }
        catch(Exception e){
        } finally {
            localSqlSession.close();
        }

        return resultMap;
    }
    
    
    /* *******************************************************************************************
     * 함수  제목 : 
     * 작  성  자 :       작  성  일 : 
     * 내      용 : 
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    
    public Map<String, Object> userinfoDeliveryDoc(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ReissueDeliveryDocVO> lsListTable = new ArrayList<ReissueDeliveryDocVO>();

        //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();

        try {
            //데이타 리스트를 추출.
            lsListTable = localSqlSession.selectList("ReissueDeliveryDocDelvn", paramMap);
            resultMap.put("datalist", lsListTable);
            paramMap.put("DOC_NUM", lsListTable.get(0));

        }
        catch(Exception e){
        } finally {
            localSqlSession.close();
        }

        return resultMap;
    }
    /* *******************************************************************************************
     * 함수  제목 : 
     * 작  성  자 :       작  성  일 :
     * 내      용 : 
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
   	public List<ReissueDeliveryDocVO> useInfoList(Map<String, Object> paramMap) {
           return (List<ReissueDeliveryDocVO>) selectList("userInfoList", paramMap);
       }
    
    /* *******************************************************************************************
     * 함수  제목 : 
     * 작  성  자 :       작  성  일 :
     * 내      용 : 
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
   	public List<ReissueDeliveryDocVO> DeliveryDocList(Map<String, Object> paramMap) {
           return (List<ReissueDeliveryDocVO>) selectList("DeliveryDocList", paramMap);
       }
    

    
    /* *******************************************************************************************
     * 함수  제목 : 
     * 작  성  자 :       작  성  일 :
     * 내      용 : 
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int multiUpdateReissueDeliveryDoc(Map<String, Object> paramMap) {
    	
    	 List<DeliveryDocVO> lsListTable = new ArrayList<DeliveryDocVO>();
    	 int result = 0;
    	 
    	 //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();
           
        try {
        	result = localSqlSession.update("ReissueDeliveryDocDelvnUpdate", paramMap);
			
        }
        catch(Exception e){
        	
            
        } finally {
            localSqlSession.close();
        }

        return result ;
        
    }
    
    
    /* *******************************************************************************************
     * 함수  제목 : 
     * 작  성  자 :       		작  성  일 :
     * 내      용 : 
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int multiUpdateReissueDeliveryDoc_Cancel(Map<String, Object> paramMap) {
    	
    	 List<DeliveryDocVO> lsListTable = new ArrayList<DeliveryDocVO>();
    	 int result = 0;
    	 
    	 //로컬 SQL세션을 생성한다.
        SqlSession localSqlSession = sqlSessionFactory.openSession();
           
        try {
        	result = localSqlSession.update("ReissueDeliveryDocStatuUpdate", paramMap);
        	result = localSqlSession.update("ReissueDeliveryDocDelvnUpdate_Cancel", paramMap);
			
        }
        catch(Exception e){
        	
            
        } finally {
            localSqlSession.close();
        }

        return result ;
        
    }
    
    /* *******************************************************************************************
     * 함수  제목 : 발주정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 해당 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public DeliveryDocVO detailDeliveryDoc(Map<String, Object> paramMap) {
        return (DeliveryDocVO) selectOne("detailReissueDeliveryDoc", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록에 대한 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int totalCountDeliveryDoc(Map<String, Object> paramMap) {
        return (int) selectOne("totalCountReissueDeliveryDoc", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int overlabCountDeliveryDoc(Map<String, Object> paramMap) {
        return (int) selectOne("overlabCntReissueDeliveryDoc", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 입력시 PK중복 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public List<DeliveryDocVO> overlabListDeliveryDoc(Map<String, Object> paramMap) {
        return (List<DeliveryDocVO>) selectList("overlabListReissueDeliveryDoc", paramMap);
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
    public int insertDeliveryDoc(Map<String, Object> paramMap) {
        return (int) update("insertReissueDeliveryDoc", paramMap);
    }

    

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int deleteDeliveryDoc(Map<String, Object> paramMap) {
        return (int) delete("deleteReissueDeliveryDoc", paramMap);
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int deleteDeliveryDocShipping(Map<String, Object> paramMap) {
        return (int) delete("deleteReissueDeliveryDocShipping", paramMap);
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
    public int updateDeliveryDocRecive(Map<String, Object> paramMap) {
        return (int) update("updateReissueDeliveryDocRecive", paramMap);
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
	public Map<String, Object> listReissueDeliveryDocConfirm(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listDeliveryDocConfirm", paramMap);
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
	public Map<String, Object> listReissueDeliveryDocResheet(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listDeliveryDocResheet", paramMap);
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
	public Map<String, Object> listReissueDeliveryDocResheetDetail(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<MeterialNumVO> lsListTable = new ArrayList<MeterialNumVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listDeliveryDocResheetDetail", paramMap);
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
	public Map<String, Object> listReissueDeliveryDocHistory(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<DeliveryDocVO> lsListTable = new ArrayList<DeliveryDocVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listDeliveryDocHistory", paramMap);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }
    
}

