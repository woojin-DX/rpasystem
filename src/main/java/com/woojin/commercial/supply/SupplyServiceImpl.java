/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 발주정보 Data를 가공하거나 특별한 서비스 역할을 만듦 
 * 소  스  명 : SupplyDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.supply;

import com.woojin.commercial.admin.commoncode.CommonCodeDAO;
import com.woojin.commercial.admin.commoncode.CommonCodeVO;
import com.woojin.commercial.shipping.ShippingDAO;
import com.woojin.commercial.util.PageNavigater;
import com.woojin.commercial.util.StringUtil;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.woojin.commercial.common.CommandMap;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("supplyService")
public class SupplyServiceImpl implements SupplyService {

    // 쿼리로그 추출
    Logger log = Logger.getLogger(this.getClass());

    @Resource(name="supplyDAO")
    private SupplyDAO supplyDAO;

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
    @Override
    public Map<String, Object> listSupply(CommandMap commandMap)  throws Exception {
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
                mon.add(Calendar.MONTH , -4);
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
            process += shippingDAO.updateShippingPross();
            process += shippingDAO.updateShippingSupplyPross();
            process += shippingDAO.updateShippingOrderPross();

            CommandMap newCommandMap = new CommandMap();
            newCommandMap.put("division_cd","ST");
            newCommandMap.put("role","SUPPLY");
            Map<String, Object> dataCommonMap =  commonCodeDAO.listCommonCode(newCommandMap.getMap());
            List<CommonCodeVO> listCommonParam = (List<CommonCodeVO>) dataCommonMap.get("datalist");

            commandMap.put("order_dt", order_dt);
            Map<String, Object> dataMaterialMap =  supplyDAO.listMaterialNum(commandMap.getMap());
            List<MeterialNumVO> listMaterialParam = (List<MeterialNumVO>) dataMaterialMap.get("datalist");

            CommandMap placeCommandMap = new CommandMap();
            placeCommandMap.put("company_cd",commandMap.get("company_cd"));
            placeCommandMap.put("schuser_fl","1");
            Map<String, Object> dataPlaceMap =  placeDAO.listPlace(placeCommandMap.getMap());

            List<PlaceVO> placeList = (List<PlaceVO>) dataPlaceMap.get("datalist");

            dataMap =  supplyDAO.listSupply(commandMap.getMap());
            List<SupplyVO> listParam = (List<SupplyVO>) dataMap.get("datalist");

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
            resultMap.put("commonList", listCommonParam); //목록
            resultMap.put("materialList", listMaterialParam); //목록
            resultMap.put("placeList", placeList); //목록
            resultMap.put("supplyList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
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
    @Override
    public Map<String, Object> listSupplyConfirm(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM", Locale.KOREA);
            Calendar cal = Calendar.getInstance();

            Map<String, Object> dataMap = new HashMap<String,Object>();
            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }
            if (commandMap.get("supply_month") == null) {
                String firstDate = formatter.format(cal.getTime());
                commandMap.put("supply_month", firstDate);
            }
            Map<String, Object> dataMaterialMap =  supplyDAO.listMaterialOrderAll(commandMap.getMap());
            List<MeterialNumVO> listMaterialParam = (List<MeterialNumVO>) dataMaterialMap.get("materiallist");

            dataMap =  supplyDAO.listSupplyConfirm(commandMap.getMap());
            List<SupplyVO> listParam = (List<SupplyVO>) dataMap.get("datalist");

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("materialList", listMaterialParam); //목록
            resultMap.put("supplyList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
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
    @Override
    public Map<String, Object> listSupplyResheet(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM", Locale.KOREA);
            Calendar cal = Calendar.getInstance();

            Map<String, Object> dataMap = new HashMap<String,Object>();
            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }
            if (commandMap.get("supply_month") == null) {
                String firstDate = formatter.format(cal.getTime());
                commandMap.put("supply_month", firstDate);
            }

            dataMap =  supplyDAO.listSupplyResheet(commandMap.getMap());
            List<SupplyVO> listParam = (List<SupplyVO>) dataMap.get("datalist");

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("supplyList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
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
    @Override
    public Map<String, Object> listSupplyResheetDetail(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM", Locale.KOREA);
            Calendar cal = Calendar.getInstance();

            Map<String, Object> dataMap = new HashMap<String,Object>();

            dataMap =  supplyDAO.listSupplyResheetDetail(commandMap.getMap());
            List<SupplyVO> listParam = (List<SupplyVO>) dataMap.get("datalist");

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("supplyList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
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
    public Map<String, Object> detailSupply(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SupplyVO detailParam = new SupplyVO();
            detailParam = supplyDAO.detailSupply(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("supplyDetail", detailParam); //내용
        }
        catch(Exception e){
            log.error(e);
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
    public int totalCountSupply(CommandMap commandMap) throws Exception {
        return supplyDAO.totalCountSupply(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public int overlabCountSupply(CommandMap commandMap) throws Exception {
        return supplyDAO.overlabCountSupply(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 중복데이타 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> overlabListSupply(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            List<SupplyVO> listParam = supplyDAO.overlabListSupply(commandMap.getMap());

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("supplyList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
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
    public Map<String, Object> insertSupply(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            int overlab = supplyDAO.overlabCountSupply(commandMap.getMap());
            if (overlab == 0) {
                int process = supplyDAO.insertSupply(commandMap.getMap());
                commandMap.put("shipping_seq", "0");
                commandMap.put("orderfor_key", commandMap.get("new_cd"));
                process += supplyDAO.insertShippingFirst(commandMap.getMap());
                if (process > 0) {
                    resultMap.put("status", 0);
                    resultMap.put("msg", "정상적으로 발주정보 등록이 되었습니다.");
                } else {
                    resultMap.put("status", 1);
                    resultMap.put("msg", "발주정보 등록에 실패했습니다.\r\n관리자에게 문의해주세요.");
                }
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "발주정보 등록에 실패했습니다.\r\n동일한 발주건이 존재합니다.");
            }
        }
        catch(Exception e){
            log.error(e);
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
    public Map<String, Object> updateSupply(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            int overlab = supplyDAO.overlabCountSupply(commandMap.getMap());
            if (overlab == 0) {
                int process = 0;
                process += supplyDAO.updateSupply(commandMap.getMap());

                if (process > 0) {
                    resultMap.put("status", 0);
                    resultMap.put("msg", "정상적으로 발주정보 수정을 완료하였습니다.");
                } else {
                    resultMap.put("status", 1);
                    resultMap.put("msg", "발주정보 수정에 실패했습니다.\r\n관리자에게 문의해 주세요.");
                }
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "발주정보 수정에 실패했습니다.\r\n동일한 발주건이 존재합니다.");
            }
        }
        catch(Exception e){
            log.error(e);
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
    public Map<String, Object> updateSupplyRecive(CommandMap commandMap) throws Exception {
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

            process += supplyDAO.multiUpdateShippingRecive(commandMap.getMap());
            process += supplyDAO.updateSupplyRecive(commandMap.getMap());

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
            log.error(e);
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
    public Map<String, Object> deleteSupply(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            commandMap.put("nCurrpage", "1");
            int process = 0;
            process = supplyDAO.deleteSupplyShipping(commandMap.getMap());
            process = supplyDAO.deleteSupply(commandMap.getMap());
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
            log.error(e);
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
            detailParam = supplyDAO.detailMaterialNum(commandMap.getMap());
            //jsp 에서 보여줄 정보 추출
            resultMap.put("detailMaterialNum", detailParam); //내용
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;

    }


}

