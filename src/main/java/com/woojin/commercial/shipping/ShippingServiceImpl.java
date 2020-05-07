/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 출하정보 Data를 가공하거나 특별한 서비스 역할을 만듦 
 * 소  스  명 : ShippingDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.shipping;

import com.woojin.commercial.admin.commoncode.CommonCodeDAO;
import com.woojin.commercial.admin.commoncode.CommonCodeVO;
import com.woojin.commercial.admin.commoncode.PackingCodeVO;
import com.woojin.commercial.admin.userinfo.CompanyVO;
import com.woojin.commercial.admin.userinfo.UserInfoDAO;
import com.woojin.commercial.supply.MeterialNumVO;
import com.woojin.commercial.util.PageNavigater;
import com.woojin.commercial.util.StringUtil;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.util.CommonUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("shippingService")
public class ShippingServiceImpl implements ShippingService {

    // 쿼리로그 추출
    Logger log = Logger.getLogger(this.getClass());

    @Resource(name="shippingDAO")
    private ShippingDAO shippingDAO;

    @Resource(name="commonCodeDAO")
    private CommonCodeDAO commonCodeDAO;

    @Resource(name="userInfoDAO")
    private UserInfoDAO userInfoDAO;

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> listShipping(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
            Calendar time = Calendar.getInstance();

            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }
            if (commandMap.get("supply_dt_start") == null) {
                String firstDate = formatter.format(time.getTime());
                commandMap.put("supply_dt_start", firstDate);
            }
            if (commandMap.get("supply_dt_end") == null) {
                time.add(Calendar.DATE , 8);
                String lastDate = formatter.format(time.getTime());
                commandMap.put("supply_dt_end", lastDate);
            }
/*
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

 */
            commandMap.remove("pageaction");
            commandMap.remove("company_cd");
            commandMap.remove("accept_dt");
            commandMap.remove("material_num");
            commandMap.remove("shipping_seq");

            CommandMap newCommandMap = new CommandMap();
            newCommandMap.put("division_cd","ST");
            Map<String, Object> dataCommonMap =  commonCodeDAO.listCommonCode(newCommandMap.getMap());
            List<CommonCodeVO> listCommonParam = (List<CommonCodeVO>) dataCommonMap.get("datalist");

            newCommandMap.put("division_cd","OM");
            Map<String, Object> dataPackingMap =  commonCodeDAO.listCommonCode(newCommandMap.getMap());
            List<PackingCodeVO> packingCommonParam = (List<PackingCodeVO>) dataPackingMap.get("datalist");

            Map<String, Object> dataMaterialMap =  shippingDAO.listMaterialAll(commandMap.getMap());
            List<MeterialNumVO> listMaterialParam = (List<MeterialNumVO>) dataMaterialMap.get("datalist");

            Map<String, Object> dataCompanyMap =  userInfoDAO.listCompany(commandMap.getMap());
            List<CompanyVO> listCompanyParam = (List<CompanyVO>) dataCompanyMap.get("companylist");

            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  shippingDAO.listShippingCfm(commandMap.getMap());

            List<ShippingVO> listParam = (List<ShippingVO>) dataMap.get("datalist");

            int nTotCnt = (int)dataMap.get("totalcnt");
/*
            HashMap<String, Object> pageMap = new HashMap<String, Object>();
            pageMap.put("iPageRecord", pageRecordCount);
            pageMap.put("iTotRecord", nTotCnt);
            pageMap.put("iCurPage", nCurrpage);
            pageMap.put("bFirstLast", true);

            String pageNavigater = PageNavigater.getPageForm(pageMap);
*/
            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            //resultMap.put("pageNavigater", pageNavigater); //페이징 폼
            resultMap.put("commonList", listCommonParam); //목록
            resultMap.put("packingList", packingCommonParam); //목록
            resultMap.put("materialList", listMaterialParam); //목록
            resultMap.put("companyList", listCompanyParam); //목록
            resultMap.put("shippingList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> detailShipping(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();

        try {
            ShippingVO detailParam = new ShippingVO();

            detailParam = shippingDAO.detailShipping(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("shippingDetail", detailParam); //내용
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;

    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 레코드갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public int totalCountShipping(CommandMap commandMap) throws Exception {
        return shippingDAO.totalCountShipping(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public int overlabCountShipping(CommandMap commandMap) throws Exception {
        return shippingDAO.overlabCountShipping(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 중복데이타 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> overlabListShipping(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            List<ShippingVO> listParam = shippingDAO.overlabListShipping(commandMap.getMap());

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("shippingList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 단일입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> insertShipping(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            String ip = CommonUtils.getClientIP();
            int process = 0;

            int shipping_seq = shippingDAO.getShippingMaxSeq(commandMap.getMap());
            if (shipping_seq == 1){

                process += shippingDAO.updateShipping(commandMap.getMap());
            }
            else {
                commandMap.put("shipping_seq", shipping_seq);

                process += shippingDAO.insertShipping(commandMap.getMap());
            }
            if (process > 0) {
                resultMap.put("status", 0);
                resultMap.put("msg", "정상적으로 출하정보 등록이 되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "출하정보 등록에 실패했습니다.\r\n관리자에게 문의해주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> updateShipping(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            String ip = CommonUtils.getClientIP();
            int process = 0;

			process += shippingDAO.updateShipping(commandMap.getMap());

            if (process > 0) {
                resultMap.put("status", 0);
                resultMap.put("msg", "정상적으로 출하정보 수정이 완료되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "출하정보 수정에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> deleteShipping(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            commandMap.put("nCurrpage", "1");

            int process = shippingDAO.deleteShipping(commandMap.getMap());
            if (process == 1) {
                resultMap.put("status", 0);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "정상적으로 출하정보 삭제가 완료되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "출하정보 삭제에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> listShippingCfmAddr(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
            Calendar time = Calendar.getInstance();

            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }
            if (commandMap.get("supply_dt_start") == null) {
                String firstDate = formatter.format(time.getTime());
                commandMap.put("supply_dt_start", firstDate);
            }
            if (commandMap.get("supply_dt_end") == null) {
                time.add(Calendar.DATE , 8);
                String lastDate = formatter.format(time.getTime());
                commandMap.put("supply_dt_end", lastDate);
            }
            commandMap.remove("pageaction");
            commandMap.remove("company_cd");
            commandMap.remove("accept_dt");
            commandMap.remove("material_num");
            commandMap.remove("shipping_seq");

            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  shippingDAO.listShippingCfmAddr(commandMap.getMap());

            List<ShippingVO> listParam = (List<ShippingVO>) dataMap.get("datalist");

            resultMap.put("placeList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }
    
    /* *******************************************************************************************
     * 함수  제목 : 출하정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> updateShipping_method(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            String ip = CommonUtils.getClientIP();
            int process = 0;

			process += shippingDAO.updateShipping_method(commandMap.getMap());

            if (process > 0) {
                resultMap.put("status", 0);
                resultMap.put("msg", "정상적으로 처리되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "처리에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }


}

