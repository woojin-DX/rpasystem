package com.woojin.commercial.admin;

import com.googlecode.ehcache.annotations.Cacheable;
import com.woojin.commercial.admin.commoncode.CommonCodeDAO;
import com.woojin.commercial.admin.commoncode.CommonCodeVO;
import com.woojin.commercial.admin.commoncode.PackingCodeVO;
import com.woojin.commercial.admin.userinfo.CompanyVO;
import com.woojin.commercial.admin.userinfo.UserInfoDAO;
import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.shipping.ShippingDAO;
import com.woojin.commercial.shipping.ShippingVO;
import com.woojin.commercial.shipping.shippingmtm.ShippingMtmDAO;
import com.woojin.commercial.shipping.shippingmtm.ShippingMtmVO;
import com.woojin.commercial.supply.MeterialNumVO;
import com.woojin.commercial.util.CommonUtils;
import com.woojin.commercial.util.PageNavigater;
import com.woojin.commercial.util.StringUtil;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

    // 쿼리로그 추출
    Logger log = Logger.getLogger(this.getClass());

    @Resource(name="shippingDAO")
    private ShippingDAO shippingDAO;

    @Resource(name="commonCodeDAO")
    private CommonCodeDAO commonCodeDAO;

    @Resource(name="userInfoDAO")
    private UserInfoDAO userInfoDAO;

    @Resource(name="shippingMtmDAO")
    private ShippingMtmDAO shippingMtmDAO;

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
            Calendar cal = Calendar.getInstance();

            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }
            if (commandMap.get("accept_dt_start") == null) {
                Calendar mon = Calendar.getInstance();
                mon.add(Calendar.MONTH , -5);
                String firstDate = formatter.format(mon.getTime());
                commandMap.put("accept_dt_start", firstDate);
            }
            if (commandMap.get("accept_dt_end") == null) {
                Calendar time = Calendar.getInstance();
                String lastDate = formatter.format(time.getTime());
                commandMap.put("accept_dt_end", lastDate);
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

            int process = 0;
            process += shippingDAO.updateShippingPross();
            process += shippingDAO.updateShippingSupplyPross();
            process += shippingDAO.updateShippingOrderPross();

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
            dataMap =  shippingDAO.listShipping(commandMap.getMap());

            List<ShippingVO> listParam = (List<ShippingVO>) dataMap.get("datalist");

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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
            String ip = CommonUtils.getClientIP();
            int process = 0;
            String msg = "";
            String status = "0";

            String process_cd = commandMap.get("process_cd").toString();
            String nextprocess_cd = commandMap.get("nextprocess_cd").toString();

            Calendar time = Calendar.getInstance();
            String accept_dt = formatter.format(time.getTime());

            String supply_qty = commandMap.get("supply_qty").toString().replace(",","");
            if (supply_qty.equals("") ){
                supply_qty = "0";
            }
            String supplymtm_qty ="0";
            if (commandMap.get("supplymtm_qty") != null){
                supplymtm_qty = commandMap.get("supplymtm_qty").toString().replace(",","");
                if (supplymtm_qty.equals("") ){
                    supplymtm_qty = "0";
                }
            }

            int nSupply_qty = Integer.parseInt(supply_qty) + Integer.parseInt(supplymtm_qty);

            commandMap.put("supply_qty", Integer.toString(nSupply_qty));

            String packing_method = commandMap.get("packing_method").toString();
            Boolean mtmFlag = false;

            String modi_mtm_item = commandMap.get("modi_mtm_item").toString();
            String modi_mtm_loc = commandMap.get("modi_mtm_loc").toString();
            String modi_mtm_qty = commandMap.get("modi_mtm_qty").toString();
            String modi_mtm_ploc = commandMap.get("modi_mtm_ploc").toString();

            List<Map<String, Object>> listMtm = new ArrayList<Map<String, Object>>();
            Map<String, Object> mtmMap = new HashMap<String, Object>();

            if (packing_method.equals("OM_MTM")){
                mtmFlag = true;
                String[] arrMtmQty = modi_mtm_qty.split(",");
                String[] arrMtmLoc = modi_mtm_loc.split(",");
                String[] arrMtmItem = modi_mtm_item.split(",");
                String[] arrMtmPloc = modi_mtm_ploc.split(",");
                Map<String, Object> mapSender = new HashMap<String, Object>();
                if (arrMtmQty.length == 0){
                    mtmFlag = false;
                }
                else {
                    for (int i = 0; i < arrMtmQty.length; i++) {
                        mapSender = new HashMap<String, Object>();
                        mapSender.put("modi_qty", arrMtmQty[i]);
                        mapSender.put("storage_loc", arrMtmLoc[i]);
                        mapSender.put("modi_meterial_num", arrMtmItem[i]);
                        mapSender.put("pre_storage_loc", arrMtmPloc[i]);
                        listMtm.add(mapSender);
                    }
                    mtmMap.put("company_cd", commandMap.get("company_cd"));
                    mtmMap.put("material_num", commandMap.get("material_num"));
                    mtmMap.put("order_dt", commandMap.get("order_dt"));
                    mtmMap.put("modify_id", commandMap.get("modify_id"));
                    mtmMap.put("process_id", commandMap.get("process_id"));
                    mtmMap.put("list", listMtm);
                }
            }
            int remainAllQty = shippingDAO.selectExcReaminAllQty(commandMap.getMap());
            int realReaminInvenQty = shippingDAO.selectExcRealReaminInvenQty(commandMap.getMap());
            int realReaminMtmQty = shippingDAO.selectExcRealReaminMtmQty(commandMap.getMap());

            boolean insertFlag = true;

            if (process_cd.equals("ST_BOS")) {
                if (nSupply_qty > realReaminInvenQty+realReaminMtmQty) {
                    status = "0";
                    msg = "납품수량은 가용재고를 초과할 수 없습니다.";
                    resultMap.put("status", status);
                    resultMap.put("msg", msg);
                    insertFlag = false;
                }
            }
            else{
                if (nSupply_qty > remainAllQty) {
                    status = "0";
                    msg = "출하수량은 잔여 수량보다 작아야 합니다.";
                    resultMap.put("status", status);
                    resultMap.put("msg", msg);
                    insertFlag = false;
                }

                if (Integer.parseInt(supply_qty) > realReaminInvenQty) {
                    status = "0";
                    msg = "출하수량은 가용재고보다 작아야 합니다.";
                    resultMap.put("status", status);
                    resultMap.put("msg", msg);
                    insertFlag = false;
                }

                if (Integer.parseInt(supplymtm_qty) > realReaminMtmQty) {
                    status = "0";
                    msg = "출하수량은 MTM가용재고보다 작아야 합니다.";
                    resultMap.put("status", status);
                    resultMap.put("msg", msg);
                    insertFlag = false;
                }
            }
            if (insertFlag){
                status = "1";
                msg = "정상적으로 처리되었습니다.";
                if (!nextprocess_cd.equals("ST_OFR") ) {
                    int shipping_seq = shippingDAO.getShippingMaxSeq(commandMap.getMap());
                    commandMap.put("process_cd", nextprocess_cd);
                    commandMap.put("shipping_seq", shipping_seq);
                    commandMap.put("use_fl", "SUB");
                    commandMap.put("remain_qty", "0");
                    process += shippingDAO.insertShipping(commandMap.getMap());
                    String shipping_key = commandMap.get("new_cd").toString();
                    commandMap.put("shipping_key", commandMap.get("new_cd"));
                    process += shippingMtmDAO.deleteShippingMtm(commandMap.getMap());
                    if (mtmFlag) {
                        //delete 후 insert
                        mtmMap.put("material_num", commandMap.get("material_num"));
                        mtmMap.put("shipping_key", shipping_key);
                        process += shippingMtmDAO.multiInsertUpdateShippingMtm(mtmMap);
                    }
                }

                String confirm_qty = commandMap.get("confirm_qty").toString().replace(",","");
                if (confirm_qty.equals("")) {
                    confirm_qty = "0";
                }
                int nConfirm_qty = Integer.parseInt(confirm_qty.replace(",", ""));

                int allSypplySum = shippingDAO.getShippingSum(commandMap.getMap());

                CommandMap commandMapSh = new CommandMap();
                commandMapSh.put("accept_dt",accept_dt);
                commandMapSh.put("remain_qty",Integer.toString(nConfirm_qty - allSypplySum));
                commandMapSh.put("supply_qty",Integer.toString(allSypplySum));
                commandMapSh.put("process_id",commandMap.get("process_id"));
                commandMapSh.put("modify_id",commandMap.get("modify_id"));
                commandMapSh.put("shipping_seq", "0");
                commandMapSh.put("shipping_key", commandMap.get("shipping_key"));
                commandMapSh.put("orderfor_key",commandMap.get("orderfor_key"));
                if (nConfirm_qty > allSypplySum){
                    commandMapSh.put("process_cd", "ST_OFR");
                }
                else {
                    commandMapSh.put("process_cd", nextprocess_cd);
                }
                commandMap.put("accept_dt", accept_dt);
                if (nConfirm_qty == allSypplySum){
                    commandMapSh.put("use_fl", "END");
                }
                else {
                    commandMapSh.put("use_fl", "SUB");
                }

                process += shippingDAO.updateShippingOffer(commandMapSh.getMap());

                CommandMap commandMapOr = new CommandMap();
                if (nextprocess_cd.equals("ST_ING")) {
                    commandMapOr.put("process_cd", "ST_OFR");
                }
                else{
                    commandMapOr.put("process_cd", nextprocess_cd);
                }
                commandMapOr.put("confirm_qty", commandMap.get("confirm_qty"));
                commandMapOr.put("shipping_dt", commandMap.get("shipping_dt"));
                commandMapOr.put("modify_id",commandMap.get("modify_id"));
                commandMapOr.put("orderfor_key",commandMap.get("orderfor_key"));

                process += shippingDAO.updateSupplyReceipt(commandMapOr.getMap());

                if (process > 0) {
                    resultMap.put("status", status);
                    resultMap.put("msg", msg);
                } else {
                    resultMap.put("status", 0);
                    resultMap.put("msg", "요청하신 처리에 실패했습니다.\r\n관리자에게 문의해주세요.");
                }
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
            String status = "0";
            String msg = "";
            String process_cd = commandMap.get("process_cd").toString();
            String nextprocess_cd = commandMap.get("nextprocess_cd").toString();

            String supply_qty = commandMap.get("supply_qty").toString().replace(",","");
            if (supply_qty.equals("") ){
                supply_qty = "0";
            }

            String supplymtm_qty ="0";
            if (commandMap.get("supplymtm_qty") != null){
                supplymtm_qty = commandMap.get("supplymtm_qty").toString().replace(",","");
                if (supplymtm_qty.equals("") ){
                    supplymtm_qty = "0";
                }
            }

            int nSupply_qty = Integer.parseInt(supply_qty) + Integer.parseInt(supplymtm_qty);

            commandMap.put("supply_qty", Integer.toString(nSupply_qty));

            String packing_method = commandMap.get("packing_method").toString();

            Boolean mtmFlag = false;

            String modi_mtm_item = commandMap.get("modi_mtm_item").toString();
            String modi_mtm_loc = commandMap.get("modi_mtm_loc").toString();
            String modi_mtm_qty = commandMap.get("modi_mtm_qty").toString();
            String modi_mtm_ploc = commandMap.get("modi_mtm_ploc").toString();

            List<Map<String, Object>> listMtm = new ArrayList<Map<String, Object>>();
            Map<String, Object> mtmMap = new HashMap<String, Object>();

            if (packing_method.equals("OM_MTM")){
                mtmFlag = true;
                String[] arrMtmQty = modi_mtm_qty.split(",");
                String[] arrMtmLoc = modi_mtm_loc.split(",");
                String[] arrMtmItem = modi_mtm_item.split(",");
                String[] arrMtmPloc = modi_mtm_ploc.split(",");
                Map<String, Object> mapSender = new HashMap<String, Object>();
                if (arrMtmQty.length == 0){
                    mtmFlag = false;
                }
                else {
                    for (int i = 0; i < arrMtmQty.length; i++) {
                        mapSender = new HashMap<String, Object>();
                        mapSender.put("modi_qty", arrMtmQty[i]);
                        mapSender.put("storage_loc", arrMtmLoc[i]);
                        mapSender.put("modi_meterial_num", arrMtmItem[i]);
                        mapSender.put("pre_storage_loc", arrMtmPloc[i]);
                        listMtm.add(mapSender);
                    }
                    mtmMap.put("company_cd", commandMap.get("company_cd"));
                    mtmMap.put("material_num", commandMap.get("material_num"));
                    mtmMap.put("order_dt", commandMap.get("order_dt"));
                    mtmMap.put("modify_id", commandMap.get("modify_id"));
                    mtmMap.put("process_id", commandMap.get("process_id"));
                    mtmMap.put("list", listMtm);
                }
            }

            int remainAllQty = shippingDAO.selectExcReaminAllQty(commandMap.getMap());
            int realReaminInvenQty = shippingDAO.selectExcRealReaminInvenQty(commandMap.getMap());
            int realReaminMtmQty = shippingDAO.selectExcRealReaminMtmQty(commandMap.getMap());

            boolean insertFlag = true;

            if (nSupply_qty > remainAllQty) {
                status = "0";
                msg = "출하수량은 잔여 수량보다 작아야 합니다.";
                resultMap.put("status", status);
                resultMap.put("msg", msg);
                insertFlag = false;
            }

            if (Integer.parseInt(supply_qty) > realReaminInvenQty) {
                status = "0";
                msg = "출하수량은 가용재고보다 작아야 합니다.";
                resultMap.put("status", status);
                resultMap.put("msg", msg);
                insertFlag = false;
            }

            if (Integer.parseInt(supplymtm_qty) > realReaminMtmQty) {
                status = "0";
                msg = "출하수량은 MTM가용재고보다 작아야 합니다.";
                resultMap.put("status", status);
                resultMap.put("msg", msg);
                insertFlag = false;
            }

            if (insertFlag){
                status = "1";
                msg = "정상적으로 처리되었습니다.";
                if ((process_cd.equals("ST_BOS") || process_cd.equals("ST_OFR")) &&!nextprocess_cd.equals("ST_OFR") ) {
                    int shipping_seq = shippingDAO.getShippingMaxSeq(commandMap.getMap());
                    commandMap.put("process_cd", nextprocess_cd);
                    commandMap.put("shipping_seq", shipping_seq);
                    commandMap.put("use_fl", "SUB");
                    commandMap.put("remain_qty", "0");
                    process += shippingDAO.insertShipping(commandMap.getMap());
                    String shipping_key = commandMap.get("new_cd").toString();
                    commandMap.put("shipping_key", commandMap.get("new_cd"));
                    process += shippingMtmDAO.deleteShippingMtm(commandMap.getMap());
                    if (mtmFlag) {
                        //delete 후 insert
                        mtmMap.put("material_num", commandMap.get("material_num"));
                        mtmMap.put("shipping_key", shipping_key);
                        process += shippingMtmDAO.multiInsertUpdateShippingMtm(mtmMap);
                    }
                }
                else{
                    commandMap.put("process_cd", nextprocess_cd);
                    commandMap.put("use_fl", "SUB");
                    commandMap.put("remain_qty", "0");
                    process += shippingDAO.updateShipping(commandMap.getMap());
                    process += shippingMtmDAO.deleteShippingMtm(commandMap.getMap());
                    if (mtmFlag) {
                        //delete 후 insert
                        mtmMap.put("shipping_key", commandMap.get("shipping_key"));
                        mtmMap.put("material_num", commandMap.get("material_num"));
                        process += shippingMtmDAO.multiInsertUpdateShippingMtm(mtmMap);
                    }
                }

                String confirm_qty = commandMap.get("confirm_qty").toString().replace(",","");
                if (confirm_qty.equals("")) {
                    confirm_qty = "0";
                }
                int nConfirm_qty = Integer.parseInt(confirm_qty.replace(",", ""));

                int allSypplySum = shippingDAO.getShippingSum(commandMap.getMap());

                CommandMap commandMapSh = new CommandMap();
                commandMapSh.put("remain_qty",Integer.toString(nConfirm_qty - allSypplySum));
                commandMapSh.put("supply_qty",Integer.toString(allSypplySum));
                commandMapSh.put("process_id",commandMap.get("process_id"));
                commandMapSh.put("modify_id",commandMap.get("modify_id"));
                commandMapSh.put("shipping_seq", "0");
                commandMapSh.put("shipping_key", commandMap.get("shipping_key"));
                commandMapSh.put("orderfor_key",commandMap.get("orderfor_key"));
                if (nConfirm_qty > allSypplySum){
                    commandMapSh.put("process_cd", "ST_OFR");
                }
                else {
                    commandMapSh.put("process_cd", nextprocess_cd);
                }
                if (nConfirm_qty == allSypplySum){
                    commandMapSh.put("use_fl", "END");
                }
                else {
                    commandMapSh.put("use_fl", "SUB");
                }

                process += shippingDAO.updateShippingOffer(commandMapSh.getMap());

                CommandMap commandMapOr = new CommandMap();
                if (nextprocess_cd.equals("ST_ING")) {
                    commandMapOr.put("process_cd", "ST_OFR");
                }
                else{
                    commandMapOr.put("process_cd", nextprocess_cd);
                }
                commandMapOr.put("confirm_qty", commandMap.get("confirm_qty"));
                commandMapOr.put("shipping_dt", commandMap.get("shipping_dt"));
                commandMapOr.put("modify_id",commandMap.get("modify_id"));
                commandMapOr.put("orderfor_key",commandMap.get("orderfor_key"));

                process += shippingDAO.updateSupplyReceipt(commandMapOr.getMap());

                if (process > 0) {
                    resultMap.put("status", status);
                    resultMap.put("msg", msg);
                } else {
                    resultMap.put("status", 0);
                    resultMap.put("msg", "요청하신 처리에 실패했습니다.\r\n관리자에게 문의해주세요.");
                }
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
            String msg = "";
            int process = 0;

            String process_cd = commandMap.get("process_cd").toString();

            if (process_cd.equals("ST_OFR")) {
                commandMap.put("accept_dt", "");
                commandMap.put("process_cd", "ST_BOS");
                commandMap.put("shipping_seq", "0");
                commandMap.put("remain_qty", "0");
                commandMap.put("confirm_qty", "0");
                commandMap.put("supply_qty", "0");
                commandMap.put("shipping_dt", "");

                process += shippingDAO.updateSupplyReceipt(commandMap.getMap());
                process += shippingDAO.updateShippingOffer(commandMap.getMap());
                msg = "발주접수가 정상적으로 삭제되어 대기로 되었습니다.";
            }
            else if (process_cd.equals("ST_ING")) {
                commandMap.put("process_cd", "ST_OFR");
                String confirm_qty = commandMap.get("confirm_qty").toString().replace(",","");
                int nConfirm_qty = Integer.parseInt(confirm_qty.replace(",",""));

                process += shippingMtmDAO.deleteShippingMtm(commandMap.getMap());
                process = shippingDAO.deleteShipping(commandMap.getMap());
                //MTM delete

                int allSypplySum = shippingDAO.getShippingSum(commandMap.getMap());

                commandMap.put("process_cd", "ST_OFR");
                commandMap.put("use_fl", "SUB");

                commandMap.put("shipping_seq", "0");
                commandMap.put("remain_qty", Integer.toString(nConfirm_qty - allSypplySum));
                commandMap.put("supply_qty", Integer.toString(allSypplySum));
                commandMap.remove("accept_dt");
                process += shippingDAO.updateSupplyReceipt(commandMap.getMap());
                process += shippingDAO.updateShippingOffer(commandMap.getMap());

                msg = "확정대기가 정상적으로 삭제되어 발주접수로 처리되었습니다.";
            }
            else if (process_cd.equals("ST_CFM")) {
                commandMap.put("process_cd", "ST_OFR");
                String confirm_qty = commandMap.get("confirm_qty").toString().replace(",","");
                int nConfirm_qty = Integer.parseInt(confirm_qty.replace(",",""));

                process += shippingMtmDAO.deleteShippingMtm(commandMap.getMap());
                process = shippingDAO.deleteShipping(commandMap.getMap());
                //MTM delete

                int allSypplySum = shippingDAO.getShippingSum(commandMap.getMap());
                commandMap.put("process_cd", "ST_OFR");
                commandMap.put("use_fl", "SUB");

                commandMap.put("shipping_seq", "0");
                commandMap.put("remain_qty", Integer.toString(nConfirm_qty - allSypplySum));
                commandMap.put("supply_qty", Integer.toString(allSypplySum));
                commandMap.remove("accept_dt");
                process += shippingDAO.updateSupplyReceipt(commandMap.getMap());
                process += shippingDAO.updateShippingOffer(commandMap.getMap());

                msg = "확정이 정상적으로 삭제되어 발주접수로 처리되었습니다.";
            }

            if (process > 0) {
                resultMap.put("status", 1);
                resultMap.put("msg", msg);
            }
            else{
                resultMap.put("status", 0);
                resultMap.put("msg", "요청하신 처리에 실패했습니다.\r\n관리자에게 문의해 주세요.");
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
    public Map<String, Object> deleteFinishShipping(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            String msg = "";
            int process = 0;

            int allSypplySum = shippingDAO.getShippingSum(commandMap.getMap());

            commandMap.put("process_cd", "ST_CFM");
            commandMap.put("shipping_seq", "0");
            commandMap.put("remain_qty", "0");
            commandMap.put("confirm_qty", Integer.toString(allSypplySum));
            commandMap.put("supply_qty", Integer.toString(allSypplySum));
            commandMap.put("use_fl", "END");
            commandMap.remove("accept_dt");
            commandMap.remove("shipping_dt");
            process += shippingDAO.updateSupplyReceipt(commandMap.getMap());
            process += shippingDAO.updateShippingOffer(commandMap.getMap());
            msg = "정상적으로 완료처리 되었습니다.";

            if (process > 0) {
                resultMap.put("status", 1);
                resultMap.put("msg", msg);
            }
            else{
                resultMap.put("status", 0);
                resultMap.put("msg", "요청하신 처리에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> listMaterialMtm(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  shippingMtmDAO.listMaterialMtm(commandMap.getMap());

            List<ShippingMtmVO> listParam = (List<ShippingMtmVO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("mtmMatrialList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> listShippingMtm(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();

            dataMap =  shippingMtmDAO.listShippingMtm(commandMap.getMap());

            List<ShippingMtmVO> listParam = (List<ShippingMtmVO>) dataMap.get("datalist");

            resultMap.put("shippingMtmList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : mtm정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> listShippingMtmPage(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
            Calendar cal = Calendar.getInstance();

            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }
            if (commandMap.get("supply_dt_start") == null) {
                Calendar mon = Calendar.getInstance();
                String firstDate = formatter.format(mon.getTime());
                commandMap.put("supply_dt_start", firstDate);
            }
            if (commandMap.get("supply_dt_end") == null) {
                Calendar time = Calendar.getInstance();
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
            commandMap.remove("mtm_seq");

            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  shippingMtmDAO.listShippingMtm(commandMap.getMap());

            List<ShippingMtmVO> listParam = (List<ShippingMtmVO>) dataMap.get("datalist");

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
//            resultMap.put("pageNavigater", pageNavigater); //페이징 폼
            resultMap.put("shippingMtmList", listParam); //목록
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
    public Map<String, Object> listShippingSum(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }
            Map<String, Object> dataMaterialMap =  shippingDAO.listMaterialAll(commandMap.getMap());
            List<MeterialNumVO> listMaterialParam = (List<MeterialNumVO>) dataMaterialMap.get("datalist");

            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  shippingDAO.listShippingSum(commandMap.getMap());

            List<ShippingVO> listParam = (List<ShippingVO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("materialList", listMaterialParam); //목록
            resultMap.put("shippingList", listParam); //목록
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
    public Map<String, Object> listShippingCfm(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
            Calendar time = Calendar.getInstance();

            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }
            String common_cd = commandMap.get("common_cd").toString();
            if (common_cd.equals("ST_CFM")) {
                if (commandMap.get("supply_dt_start") == null) {
                    String firstDate = formatter.format(time.getTime());
                    commandMap.put("supply_dt_start", firstDate);
                }
                if (commandMap.get("supply_dt_end") == null) {
                    time.add(Calendar.DATE , 8);
                    String lastDate = formatter.format(time.getTime());
                    commandMap.put("supply_dt_end", lastDate);
                }
            }
            else{
                if (commandMap.get("supply_dt_start") == null) {
                    String firstDate = formatter.format(time.getTime());
                    commandMap.put("supply_dt_start", firstDate);
                }
                if (commandMap.get("supply_dt_end") == null) {
                    String lastDate = formatter.format(time.getTime());
                    commandMap.put("supply_dt_end", lastDate);
                }
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
     * 함수  제목 : mtm정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> listShippingMatloc(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();

            dataMap =  shippingMtmDAO.listMaterialLoc(commandMap.getMap());

            List<ShippingMtmVO> listParam = (List<ShippingMtmVO>) dataMap.get("datalist");

            resultMap.put("shippingMaterialList", listParam); //목록
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
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> listShippingExcel(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
            Calendar cal = Calendar.getInstance();

            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }
            if (commandMap.get("accept_dt_start") == null) {
                Calendar mon = Calendar.getInstance();
                mon.add(Calendar.MONTH , -5);
                String firstDate = formatter.format(mon.getTime());
                commandMap.put("accept_dt_start", firstDate);
            }
            if (commandMap.get("accept_dt_end") == null) {
                Calendar time = Calendar.getInstance();
                String lastDate = formatter.format(time.getTime());
                commandMap.put("accept_dt_end", lastDate);
            }

            Map<String, Object> dataMap = new HashMap<String,Object>();
            dataMap =  shippingDAO.listShippingExcel(commandMap.getMap());

            List<ShippingVO> listParam = (List<ShippingVO>) dataMap.get("datalist");

            //jsp 에서 보여줄 정보 추출
            resultMap.put("shippingList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

}
