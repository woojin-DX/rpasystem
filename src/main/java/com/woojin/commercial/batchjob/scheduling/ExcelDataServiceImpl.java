package com.woojin.commercial.batchjob.scheduling;

import com.woojin.commercial.shipping.ShippingDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("excelDataService")
public class ExcelDataServiceImpl implements ExcelDataService{
    // 쿼리로그 추출
    Logger log = Logger.getLogger(this.getClass());

    @Resource(name="excelDataDAO")
    private ExcelDataDAO excelDataDAO;

    @Resource(name="shippingDAO")
    private ShippingDAO shippingDAO;

    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> listExcelData() throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  excelDataDAO.listExcelData();

            List<ExcelDataVO> listParam = (List<ExcelDataVO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("listExcelData", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> listExcelData1() throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  excelDataDAO.listExcelData1();

            List<ExcelDataVO> listParam = (List<ExcelDataVO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("listExcelData", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> listExcelDataSum() throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  excelDataDAO.listExcelDataSum();

            List<ExcelDataVO> listParam = (List<ExcelDataVO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("listExcelDataSum", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    @Override
    public int autoSupplyCheck() throws Exception {
        int process = 0;
        try {
        	process += shippingDAO.execureProcedureProcess();
            //process += shippingDAO.updateShippingPross();
            //process += shippingDAO.updateShippingSupplyPross();
            //process += shippingDAO.updateShippingOrderPross();
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return process;
    }


}
