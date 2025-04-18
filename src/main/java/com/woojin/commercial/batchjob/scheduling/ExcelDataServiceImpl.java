package com.woojin.commercial.batchjob.scheduling;

import com.woojin.commercial.shipping.ShippingDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service("excelDataService")
public class ExcelDataServiceImpl implements ExcelDataService{
    // 쿼리로그 추출
	Logger log = LoggerFactory.getLogger(this.getClass());

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
            log.error(e.toString());
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
            log.error(e.toString());
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
            log.error(e.toString());
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
            log.error(e.toString());
            throw e;
        }
        return process;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> listElectricStatic() throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
        	Map<String, Object> paramMap = new HashMap<String,Object>();
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            Calendar cal  = Calendar.getInstance();
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

            if (dayOfWeek > 4) {
            	cal.add(Calendar.DATE , -1);
            }
            else {
            	cal.add(Calendar.DATE, -7);
            }
            cal.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
            
            paramMap.put("start_dt", formatter.format(cal.getTime()));
            
            Calendar day = Calendar.getInstance();
            day.add(Calendar.DATE , -1);
            paramMap.put("end_dt", formatter.format(day.getTime()));
            
            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  excelDataDAO.listElectricStatic(paramMap);

            List<ElectricStaticVO> listParam = (List<ElectricStaticVO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("listElectricStatic", listParam); //목록
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }
    

}
