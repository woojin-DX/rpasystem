package com.woojin.commercial.batchjob.scheduling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix0VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix1VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix3VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix4VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix5VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix6VO;

@Service("gpsiDataService")
public class GpsiDataServiceImpl implements GpsiDataService{
	// 쿼리로그 추출
    Logger log = Logger.getLogger(this.getClass());

    @Resource(name="gpsiDataDAO")
    private GpsiDataDAO gpsiDataDAO;
    
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> listPsix0Data() throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  gpsiDataDAO.listPsix0Data();

            List<GpsiPsix0VO> listParam = (List<GpsiPsix0VO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("listPsix0Data", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> listPsix1Data() throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  gpsiDataDAO.listPsix1Data();

            List<GpsiPsix1VO> listParam = (List<GpsiPsix1VO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("listPsix1Data", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> listPsix3Data() throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  gpsiDataDAO.listPsix3Data();

            List<GpsiPsix3VO> listParam = (List<GpsiPsix3VO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("listPsix3Data", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> listPsix4Data() throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  gpsiDataDAO.listPsix4Data();

            List<GpsiPsix4VO> listParam = (List<GpsiPsix4VO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("listPsix4Data", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> listPsix5Data() throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  gpsiDataDAO.listPsix5Data();

            List<GpsiPsix5VO> listParam = (List<GpsiPsix5VO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("listPsix5Data", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> listPsix6Data() throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  gpsiDataDAO.listPsix6Data();

            List<GpsiPsix6VO> listParam = (List<GpsiPsix6VO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("listPsix6Data", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
	}

}
