/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 발주정보 Data를 가공하거나 특별한 서비스 역할을 만듦 
 * 소  스  명 : DeliveryDocDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.woojin.commercial.admin.commoncode.CommonCodeDAO;
import com.woojin.commercial.admin.commoncode.CommonCodeVO;
import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.shipping.ShippingDAO;
import com.woojin.commercial.supply.MeterialNumVO;
import com.woojin.commercial.supply.PlaceDAO;
import com.woojin.commercial.supply.PlaceVO;
import com.woojin.commercial.util.PageNavigater;
import com.woojin.commercial.util.StringUtil;

@Service("DeliveryDocService")
public class DeliveryDocServiceImpl implements DeliveryDocService {

    // 쿼리로그 추출
	Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource(name="DeliveryDocDAO")
    private DeliveryDocDAO DeliveryDocDAO;

    @Resource(name="commonCodeDAO")
    private CommonCodeDAO commonCodeDAO;

    @Resource(name="placeDAO")
    private PlaceDAO placeDAO;

    @Resource(name="shippingDAO")
    private ShippingDAO shippingDAO;

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings({ "unchecked", "unused" })
	@Override
    public Map<String, Object> listDeliveryDoc(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
            Calendar cal = Calendar.getInstance();
            String order_dt = formatter.format(cal.getTime());

            Map<String, Object> dataMap = new HashMap<String,Object>();
            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }
            if (commandMap.get("order_dt_start") == null) {
                Calendar mon = Calendar.getInstance();
                mon.add(Calendar.MONTH , -5);
                String firstDate = formatter.format(mon.getTime());
                commandMap.put("order_dt_start", firstDate);
            }
            if (commandMap.get("order_dt_end") == null) {
                Calendar time = Calendar.getInstance();
                String lastDate = formatter.format(time.getTime());
                commandMap.put("order_dt_end", lastDate);
            }


            //조회 하려는 페이지
            int nCurrpage = (commandMap.get("nCurrpage") != null ? Integer.parseInt(commandMap.get("nCurrpage").toString()) : 1);
            //한페이지에 보여줄 리스트 수
            int pageRecordCount = (commandMap.get("pageRecordCount") != null ? Integer.parseInt(commandMap.get("pageRecordCount").toString()) : 20);

            int nStartRecord = 0;
            //페이지 시작 레코드 위치 구하기
            if (nCurrpage == 1) {
                nStartRecord = 0;
            }
            else {
                nStartRecord = (nCurrpage - 1) * pageRecordCount;
            }

            commandMap.put("nCurrpage", nCurrpage);
            commandMap.put("nStartRecord", nStartRecord);
            commandMap.put("pageRecordCount", pageRecordCount);
            commandMap.remove("pageaction");
            commandMap.remove("company_cd,order_dt,material_num");

            int process = 0;
            process += shippingDAO.execureProcedureProcess();
            //process += shippingDAO.updateShippingPross();
            //process += shippingDAO.updateShippingDeliveryDocPross();
            //process += shippingDAO.updateShippingOrderPross();

            //CommandMap newCommandMap = new CommandMap();
            //newCommandMap.put("division_cd","ST");
            //newCommandMap.put("role","DeliveryDoc");
           // Map<String, Object> dataCommonMap =  commonCodeDAO.listCommonCode(newCommandMap.getMap());
            //List<CommonCodeVO> listCommonParam = (List<CommonCodeVO>) dataCommonMap.get("datalist");

           // commandMap.put("order_dt", order_dt);
           // Map<String, Object> dataMaterialMap =  DeliveryDocDAO.listMaterialNum(commandMap.getMap());
            //List<MeterialNumVO> listMaterialParam = (List<MeterialNumVO>) dataMaterialMap.get("datalist");

           // CommandMap placeCommandMap = new CommandMap();
           // placeCommandMap.put("company_cd",commandMap.get("company_cd"));
           // placeCommandMap.put("schuser_fl","1");
            //Map<String, Object> dataPlaceMap =  placeDAO.listPlace(placeCommandMap.getMap());

           // List<PlaceVO> placeList = (List<PlaceVO>) dataPlaceMap.get("datalist");

            dataMap =  DeliveryDocDAO.listDeliveryDoc(commandMap.getMap());
            List<DeliveryDocVO> listParam = (List<DeliveryDocVO>) dataMap.get("datalist");

            int nTotCnt = (int)dataMap.get("totalcnt");

            HashMap<String, Object> pageMap = new HashMap<String, Object>();
            pageMap.put("iPageRecord", pageRecordCount);
            pageMap.put("iTotRecord", nTotCnt);
            pageMap.put("iCurPage", nCurrpage);
            pageMap.put("bFirstLast", true);

            String pageNavigater = PageNavigater.getPageForm(pageMap);
            
            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("pageNavigater", pageNavigater); //페이징 폼
            //resultMap.put("commonList", listCommonParam); //목록
           // resultMap.put("materialList", listMaterialParam); //목록
           // resultMap.put("placeList", placeList); //목록
            resultMap.put("DeliveryDocList", listParam); //목록
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> listDeliveryDocConfirm(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM", Locale.KOREA);
            Calendar cal = Calendar.getInstance();

            Map<String, Object> dataMap = new HashMap<String,Object>();
            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }
            if (commandMap.get("DeliveryDoc_month") == null) {
                String firstDate = formatter.format(cal.getTime());
                commandMap.put("DeliveryDoc_month", firstDate);
            }
            Map<String, Object> dataMaterialMap =  DeliveryDocDAO.listMaterialOrderAll(commandMap.getMap());
            List<MeterialNumVO> listMaterialParam = (List<MeterialNumVO>) dataMaterialMap.get("materiallist");

            dataMap =  DeliveryDocDAO.listDeliveryDocConfirm(commandMap.getMap());
            List<DeliveryDocVO> listParam = (List<DeliveryDocVO>) dataMap.get("datalist");

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("materialList", listMaterialParam); //목록
            resultMap.put("DeliveryDocList", listParam); //목록
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> listDeliveryDocResheet(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM", Locale.KOREA);
            Calendar cal = Calendar.getInstance();

            Map<String, Object> dataMap = new HashMap<String,Object>();
            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }
            if (commandMap.get("DeliveryDoc_month") == null) {
                String firstDate = formatter.format(cal.getTime());
                commandMap.put("DeliveryDoc_month", firstDate);
            }

            dataMap =  DeliveryDocDAO.listDeliveryDocResheet(commandMap.getMap());
            List<DeliveryDocVO> listParam = (List<DeliveryDocVO>) dataMap.get("datalist");

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("DeliveryDocList", listParam); //목록
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings({ "unchecked", "unused" })
	@Override
    public Map<String, Object> listDeliveryDocResheetDetail(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM", Locale.KOREA);
            Calendar cal = Calendar.getInstance();

            Map<String, Object> dataMap = new HashMap<String,Object>();

            dataMap =  DeliveryDocDAO.listDeliveryDocResheetDetail(commandMap.getMap());
            List<DeliveryDocVO> listParam = (List<DeliveryDocVO>) dataMap.get("datalist");

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("DeliveryDocList", listParam); //목록
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> detailDeliveryDoc(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            DeliveryDocVO detailParam = new DeliveryDocVO();
            detailParam = DeliveryDocDAO.detailDeliveryDoc(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("DeliveryDocDetail", detailParam); //내용
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;

    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 레코드갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public int totalCountDeliveryDoc(CommandMap commandMap) throws Exception {
        return DeliveryDocDAO.totalCountDeliveryDoc(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public int overlabCountDeliveryDoc(CommandMap commandMap) throws Exception {
        return DeliveryDocDAO.overlabCountDeliveryDoc(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 중복데이타 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> overlabListDeliveryDoc(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            List<DeliveryDocVO> listParam = DeliveryDocDAO.overlabListDeliveryDoc(commandMap.getMap());

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("DeliveryDocList", listParam); //목록
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 단일입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> insertDeliveryDoc(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
		try {

			int process = DeliveryDocDAO.insertDeliveryDoc(commandMap.getMap());
			if (process > 0) {
				resultMap.put("status", 0);
				resultMap.put("msg", "정상적으로 발주정보 등록이 되었습니다.");

			} else {
				resultMap.put("status", 1);
				resultMap.put("msg", "발주정보 등록에 실패했습니다.\r\n동일한 발주건이 존재합니다.");
			}
		}
		
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> updateDeliveryDoc(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
        	
			int process = 0;

			String arrDeliveryDoc = commandMap.get("deliverydoc_arr").toString();
			String[] DeliveryDocArray = arrDeliveryDoc.split(",");

			List<Map<String, Object>> DeliveryDocList = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();

			
			DeliveryDocDAO.listDeliveryDocDelvn(commandMap.getMap());
			
			for (int n = 0; n < DeliveryDocArray.length; n++) {
				map = new HashMap<String, Object>();	
				
				String[] array = DeliveryDocArray[n].split(":");
				map.put("doc_delvn", array[0]);	
				map.put("doc_ebeln", array[2]);	
				map.put("doc_ebelp", array[3]);	
				map.put("doc_menge", array[4]);	
				map.put("MengeToStatu", array[5]);	
				map.put("doc_delvs", String.format("%04d", n+1));
				map.put("doc_num", commandMap.get("DOC_NUM").toString());
				
				if(DeliveryDocList.size() > 0) {
					DeliveryDocList.remove(0);
				}
				DeliveryDocList.add(map);
				commandMap.remove("list");
				commandMap.put("list", DeliveryDocList);
				 
				if(array[1].equals("A")) {
					process += DeliveryDocDAO.multiUpdateDeliveryDoc(commandMap.getMap());
				} else {
					process += DeliveryDocDAO.multiUpdateDeliveryDoc_Cancel(commandMap.getMap());
				}
				
			}
			

			if (process > 0) {
				resultMap.put("status", 0);
				resultMap.put("msg", "정상적으로 발주정보 수정을 완료하였습니다.");
			} else {
				resultMap.put("status", 1);
				resultMap.put("msg", "발주정보 수정에 실패했습니다.\r\n관리자에게 문의해 주세요.");
			}
       
            
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    
    
    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> updateDeliveryDocRecive(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            int process = 0;

            String arrShipping = commandMap.get("shipping_arr").toString();
            String[] shippingArray = arrShipping.split(",");

            List<Map<String, Object>> shippingList = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String,Object>();
            for (int n = 0; n < shippingArray.length; n ++){
                map = new HashMap<String,Object>();
                map.put("shipping_key", shippingArray[n]);
                shippingList.add(map);
            }
            commandMap.put("list", shippingList);

            process += DeliveryDocDAO.multiUpdateShippingRecive(commandMap.getMap());
            process += DeliveryDocDAO.updateDeliveryDocRecive(commandMap.getMap());

            if (process > 0) {
                resultMap.put("status", 0);
                resultMap.put("msg", "정상적으로 물품 수령완료를 등록하였습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "물품 수령정보 등록에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> deleteDeliveryDoc(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            commandMap.put("nCurrpage", "1");
            int process = 0;
            process = DeliveryDocDAO.deleteDeliveryDocShipping(commandMap.getMap());
            process = DeliveryDocDAO.deleteDeliveryDoc(commandMap.getMap());
            if (process == 1) {
                resultMap.put("status", 0);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "정상적으로 발주정보 삭제가 완료되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "발주정보 삭제에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> detailMaterialNum(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            MeterialNumVO detailParam = new MeterialNumVO();
            detailParam = DeliveryDocDAO.detailMaterialNum(commandMap.getMap());
            //jsp 에서 보여줄 정보 추출
            resultMap.put("detailMaterialNum", detailParam); //내용
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;

    }
    
    /* *******************************************************************************************
     * 함수  제목 : 발주정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
	@SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> listDeliveryDocHistory(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
        	Map<String, Object> dataMap = new HashMap<String,Object>();
        	
            dataMap =  DeliveryDocDAO.listDeliveryDocHistory(commandMap.getMap());
            List<DeliveryDocVO> listParam = (List<DeliveryDocVO>) dataMap.get("datalist");
            resultMap.put("DeliveryDocList", listParam); //목록
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    
}

