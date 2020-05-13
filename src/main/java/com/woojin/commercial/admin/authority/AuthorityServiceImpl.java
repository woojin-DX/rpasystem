/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 권한정보 Data를 가공하거나 특별한 서비스 역할을 만듦 
 * 소  스  명 : AuthorityDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.admin.authority;

import com.woojin.commercial.util.PageNavigater;
import com.woojin.commercial.util.StringUtil;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.util.CommonUtils;
import com.woojin.commercial.admin.authority.AuthorityDAO;
import com.woojin.commercial.admin.authority.AuthorityVO;
import com.woojin.commercial.admin.authority.AuthorityService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {

    // 쿼리로그 추출
    Logger log = Logger.getLogger(this.getClass());

    @Resource(name="authorityDAO")
    private AuthorityDAO authorityDAO;

    /* *******************************************************************************************
     * 함수  제목 : 권한정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> listAuthority(CommandMap commandMap)  throws Exception {
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
            commandMap.remove("auth_cd");

            dataMap =  authorityDAO.listAuthority(commandMap.getMap());

            List<AuthorityVO> listParam = (List<AuthorityVO>) dataMap.get("datalist");

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
            resultMap.put("authorityList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 권한정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> detailAuthority(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            AuthorityVO detailParam = new AuthorityVO();

            String pageaction = commandMap.get("pageaction").toString();
            if (!pageaction.equals("write")) {
                detailParam = authorityDAO.detailAuthority(commandMap.getMap());
            }

            commandMap.remove("pageaction");

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("authorityDetail", detailParam); //내용
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;

    }

    /* *******************************************************************************************
     * 함수  제목 : 권한정보 전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 레코드갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public int totalCountAuthority(CommandMap commandMap) throws Exception {
        return authorityDAO.totalCountAuthority(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 권한정보 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 단일입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> insertAuthority(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            String ip = CommonUtils.getClientIP();
            commandMap.put("reg_id", commandMap.get("reg_id").toString());
            commandMap.put("reg_ip", ip);

            int process = authorityDAO.insertAuthority(commandMap.getMap());
            if (process > 0) {
                resultMap.put("status", 0);
                resultMap.put("msg", "정상적으로 권한정보 등록이 되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "권한정보 등록에 실패했습니다.\r\n관리자에게 문의해주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 권한정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> updateAuthority(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            String ip = CommonUtils.getClientIP();
            int process = 0;
            commandMap.put("mod_id", commandMap.get("mod_id").toString());
            commandMap.put("mod_ip", ip);

				process += authorityDAO.updateAuthority(commandMap.getMap());

            if (process > 0) {
                resultMap.put("status", 0);
                resultMap.put("msg", "정상적으로 권한정보 수정이 완료되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "권한정보 수정에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 권한정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> deleteAuthority(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            commandMap.put("nCurrpage", "1");

            int process = authorityDAO.deleteAuthority(commandMap.getMap());
            if (process == 1) {
                resultMap.put("status", 0);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "정상적으로 권한정보 삭제가 완료되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "권한정보 삭제에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

}

