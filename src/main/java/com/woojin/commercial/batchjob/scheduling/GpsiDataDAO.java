package com.woojin.commercial.batchjob.scheduling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix0VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix1VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix2VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix3VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix4VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix5VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix6VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix7VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix9VO;
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
        List<GpsiPsix0VO> lsListTable = new ArrayList<GpsiPsix0VO>();

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
        List<GpsiPsix1VO> lsListTable = new ArrayList<GpsiPsix1VO>();

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
	public Map<String, Object> listPsix2Data() {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<GpsiPsix2VO> lsListTable = new ArrayList<GpsiPsix2VO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listPsix2Data", null);
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
        List<GpsiPsix3VO> lsListTable = new ArrayList<GpsiPsix3VO>();

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
        List<GpsiPsix4VO> lsListTable = new ArrayList<GpsiPsix4VO>();

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
    
    /* *******************************************************************************************
     * 함수  제목 : 자재코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	public Map<String, Object> listPsix5Data() {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<GpsiPsix5VO> lsListTable = new ArrayList<GpsiPsix5VO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listPsix5Data", null);
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
	public Map<String, Object> listPsix6Data() {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<GpsiPsix6VO> lsListTable = new ArrayList<GpsiPsix6VO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listPsix6Data", null);
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
	public Map<String, Object> listPsix7Data() {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<GpsiPsix7VO> lsListTable = new ArrayList<GpsiPsix7VO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listPsix7Data", null);
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
	public Map<String, Object> listPsix9Data() {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<GpsiPsix9VO> lsListTable = new ArrayList<GpsiPsix9VO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listPsix9Data", null);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }

}
