/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 공통코드 Data를 가공하거나 특별한 서비스 역할을 만듦 
 * 소  스  명 : CommonCodeDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.admin.commoncode;

import com.woojin.commercial.util.PageNavigater;
import com.woojin.commercial.util.StringUtil;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.admin.commoncode.CommonCodeService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commonCodeService")
public class CommonCodeServiceImpl implements CommonCodeService {

    // 쿼리로그 추출
	Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource(name="commonCodeDAO")
    private CommonCodeDAO commonCodeDAO;

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> listCommonCode(CommandMap commandMap)  throws Exception {
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
            commandMap.remove("common_cd");

            dataMap =  commonCodeDAO.listCommonCode(commandMap.getMap());

            List<CommonCodeVO> listParam = (List<CommonCodeVO>) dataMap.get("datalist");


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
            resultMap.put("commonCodeList", listParam); //목록
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> detailCommonCode(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            CommonCodeVO detailParam = new CommonCodeVO();

            String pageaction = commandMap.get("pageaction").toString();
            if (!pageaction.equals("write")) {
                detailParam = commonCodeDAO.detailCommonCode(commandMap.getMap());
            }

            commandMap.remove("pageaction");

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("commonCodeDetail", detailParam); //내용
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;

    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 레코드갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public int totalCountCommonCode(CommandMap commandMap) throws Exception {
        return commonCodeDAO.totalCountCommonCode(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public int overlabCountCommonCode(CommandMap commandMap) throws Exception {
        return commonCodeDAO.overlabCountCommonCode(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 중복데이타 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> overlabListCommonCode(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            List<CommonCodeVO> listParam = commonCodeDAO.overlabListCommonCode(commandMap.getMap());

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("commonCodeList", listParam); //목록
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 단일입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> insertCommonCode(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            //commandMap.put("common_cd", commandMap.get("division_cd").toString() + "_" + commandMap.get("status_cd").toString());
            int overlab = commonCodeDAO.overlabCountCommonCode(commandMap.getMap());
            if (overlab == 0) {
                int NewSeq = commonCodeDAO.getCommonCodeMaxSeq(commandMap.getMap());
                commandMap.put("status_seq", NewSeq);

                int process = commonCodeDAO.insertCommonCode(commandMap.getMap());
                if (process > 0) {
                    resultMap.put("status", 0);
                    resultMap.put("msg", "정상적으로 공통코드 등록이 되었습니다.");
                } else {
                    resultMap.put("status", 1);
                    resultMap.put("msg", "공통코드 등록에 실패했습니다.\r\n관리자에게 문의해주세요.");
                }
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "중복코드가 존재합니다.\r\n확인 후 다시 시도해주세요.");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> updateCommonCode(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            int process = 0;
            process += commonCodeDAO.setCommonCodeSeqModify(commandMap.getMap());
			process += commonCodeDAO.updateCommonCode(commandMap.getMap());

            process += commonCodeDAO.setCommonCodeSeqReset(commandMap.getMap());

            if (process > 0) {
                resultMap.put("status", 0);
                resultMap.put("msg", "정상적으로 공통코드 수정이 완료되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "공통코드 수정에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> deleteCommonCode(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            commandMap.put("nCurrpage", "1");

            int process = commonCodeDAO.deleteCommonCode(commandMap.getMap());
            process += commonCodeDAO.setCommonCodeSeqReset(commandMap.getMap());
            if (process == 1) {
                resultMap.put("status", 0);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "정상적으로 공통코드 삭제가 완료되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "공통코드 삭제에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 공통코드 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> multiDeleteCommonCode(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            commandMap.put("nCurrpage", "1");

            String[] commonArray = commandMap.get("common_cd").toString().split(",");

            List<Map<String, Object>> commonList = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < commonArray.length; i++) {
                map = new HashMap<String, Object>();
                map.put("common_cd",commonArray[i]);
                commonList.add(map);
            }

            commandMap.put("list", commonList);

            int process = commonCodeDAO.multiDeleteCommonCode(commandMap.getMap());
            if (process == 1) {
                resultMap.put("status", 0);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "정상적으로 공통코드 삭제가 완료되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "공통코드 삭제에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e.toString());
            throw e;
        }
        return resultMap;
    }


}

