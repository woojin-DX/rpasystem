package com.woojin.commercial.batchjob.scheduling;

import com.woojin.commercial.common.AbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("excelDataDAO")
public class ExcelDataDAO extends AbstractDAO {
    /* *******************************************************************************************
     * 함수  제목 : 자재코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public Map<String, Object> listExcelData() {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ExcelDataVO> lsListTable = new ArrayList<ExcelDataVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listExcelData", null);
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
    public Map<String, Object> listExcelDataSum() {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        List<ExcelDataVO> lsListTable = new ArrayList<ExcelDataVO>();

        try {
            //데이타 리스트를 추출.
            lsListTable = selectList("listExcelDataSum", null);
            resultMap.put("datalist", lsListTable);

        }
        catch(Exception e){
            resultMap.put("datalist", lsListTable);
        }

        return resultMap;
    }
}
