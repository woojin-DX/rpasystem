/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 납품장소 Data를 가공하거나 특별한 서비스 역할을 만듦 
 * 소  스  명 : PlaceDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.supply;

import com.woojin.commercial.util.PageNavigater;
import com.woojin.commercial.util.StringUtil;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.util.CommonUtils;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("placeService")
public class PlaceServiceImpl implements PlaceService {

    // 쿼리로그 추출
	Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource(name="placeDAO")
    private PlaceDAO placeDAO;

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> listPlace(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
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
            commandMap.remove("place_key");

            dataMap =  placeDAO.listPlace(commandMap.getMap());

            List<PlaceVO> listParam = (List<PlaceVO>) dataMap.get("datalist");

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
            resultMap.put("placeList", listParam); //목록
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
     * 내      용 : 데이타 단일입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> insertPlace(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {

            int overlab = placeDAO.overlabCountPlace(commandMap.getMap());
            if (overlab == 0) {
                int process = placeDAO.insertPlace(commandMap.getMap());
                if (process > 0) {
                    resultMap.put("status", 1);
                    resultMap.put("msg", "정상적으로 납품처 등록이 되었습니다.");
                }
                else{
                    resultMap.put("status", 0);
                    resultMap.put("msg", "납품처 등록에 실패했습니다.\r\n관리자에게 문의해주세요.");
                }
            }
            else{
                resultMap.put("status", 0);
                resultMap.put("msg", "중복납품처가 존재합니다.\r\n납품처명을 확인 후 다시 시도해주세요.");
            }

        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 납품장소 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-04-09
     * 내      용 : 조건에 따른 단일 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unused")
	@Override
    public Map<String, Object> updatePlace(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            String ip = CommonUtils.getClientIP();
            int process = 0;

			process += placeDAO.updatePlace(commandMap.getMap());

            if (process > 0) {
                resultMap.put("status", 1);
                resultMap.put("msg", "정상적으로 납품처 수정이 완료되었습니다.");
            }
            else{
                resultMap.put("status", 0);
                resultMap.put("msg", "납품처 수정에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

}

