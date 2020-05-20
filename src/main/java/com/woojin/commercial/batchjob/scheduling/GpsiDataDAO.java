package com.woojin.commercial.batchjob.scheduling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.woojin.commercial.common.AbstractDAO;

@Repository("gpsiDataDAO")
public class GpsiDataDAO extends AbstractDAO {
	/* *******************************************************************************************
     * 함수  제목 : 자재코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listPsix0Data() {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ExcelDataVO> lsListTable = new ArrayList<ExcelDataVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listPsix0Data", null);
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
	public Map<String, Object> listPsix1Data() {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ExcelDataVO> lsListTable = new ArrayList<ExcelDataVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listPsix1Data", null);
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
	public Map<String, Object> listPsix3Data() {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ExcelDataVO> lsListTable = new ArrayList<ExcelDataVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listPsix3Data", null);
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
	public Map<String, Object> listPsix4Data() {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ExcelDataVO> lsListTable = new ArrayList<ExcelDataVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listPsix4Data", null);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }

}
