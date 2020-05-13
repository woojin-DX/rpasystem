/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 사용자정보 Data를 가공하거나 특별한 서비스 역할을 만듦 
 * 소  스  명 : UserInfoDAO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.admin.userinfo;

import com.woojin.commercial.admin.authority.AuthorityDAO;
import com.woojin.commercial.admin.authority.AuthorityVO;
import com.woojin.commercial.common.SearchVO;
import com.woojin.commercial.util.PageNavigater;
import com.woojin.commercial.util.StringUtil;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.util.CommonUtils;
import com.woojin.commercial.admin.userinfo.UserInfoDAO;
import com.woojin.commercial.admin.userinfo.UserInfoVO;
import com.woojin.commercial.admin.userinfo.UserInfoService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    // 쿼리로그 추출
    Logger log = Logger.getLogger(this.getClass());

    @Resource(name="userInfoDAO")
    private UserInfoDAO userInfoDAO;

    @Resource(name="authorityDAO")
    private AuthorityDAO authorityDAO;

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> listUserInfo(SearchVO searchVO,CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String,Object>();
            Map<String, Object> dataAuthMap = new HashMap<String,Object>();
            Map<String, Object> dataCompanyMap = new HashMap<String,Object>();
            if (commandMap.get("pagemode") == null) {
                commandMap.put("pagemode", "list");
            }

            //조회 하려는 페이지
            int nCurrpage = (commandMap.get("nCurrpage") != null ? Integer.parseInt(commandMap.get("nCurrpage").toString()) : 1);
            int nMovePage = (commandMap.get("iMovePage") != null ? Integer.parseInt(commandMap.get("iMovePage").toString()) : 1);
            if (nMovePage != 1) nCurrpage = nMovePage;

            //한페이지에 보여줄 리스트 수
            int pageRecordCount = (commandMap.get("pageRecordCount") != null ? Integer.parseInt(commandMap.get("pageRecordCount").toString()) : 15);

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
            commandMap.remove("user_id");

            dataAuthMap =  authorityDAO.listAuthority(commandMap.getMap());
            List<AuthorityVO> listAuthParam = (List<AuthorityVO>) dataAuthMap.get("datalist");

            dataCompanyMap =  userInfoDAO.listCompany(commandMap.getMap());
            List<CompanyVO> listCompanyParam = (List<CompanyVO>) dataCompanyMap.get("companylist");

            dataMap =  userInfoDAO.listUserInfo(commandMap.getMap());
            List<UserInfoVO> listParam = (List<UserInfoVO>) dataMap.get("datalist");

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
            resultMap.put("authorityList", listAuthParam); //목록
            resultMap.put("companyList", listCompanyParam); //목록
            resultMap.put("userInfoList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> detailUserInfo(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            UserInfoVO detailParam = new UserInfoVO();

            String pageaction = commandMap.get("pageaction").toString();
            if (!pageaction.equals("write")) {
                detailParam = userInfoDAO.detailUserInfo(commandMap.getMap());
            }

            commandMap.remove("pageaction");

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("userInfoDetail", detailParam); //내용
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;

    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 전체레코드수
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 전체 레코드갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public int totalCountUserInfo(CommandMap commandMap) throws Exception {
        return userInfoDAO.totalCountUserInfo(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 중복여부
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 데이타 중복여부
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public int overlabCountUserInfo(CommandMap commandMap) throws Exception {
        return userInfoDAO.overlabCountUserInfo(commandMap.getMap());
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 중복목록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 중복데이타 목록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> overlabListUserInfo(CommandMap commandMap)  throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            List<UserInfoVO> listParam = userInfoDAO.overlabListUserInfo(commandMap.getMap());

            JSONObject pageParam = StringUtil.getJsonStringFromMap(commandMap.getMap());

            //jsp 에서 보여줄 정보 추출
            resultMap.put("pageParam", pageParam); //변수값
            resultMap.put("userInfoList", listParam); //목록
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 입력
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 데이타 단일입력
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> insertUserInfo(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            String ip = CommonUtils.getClientIP();
            commandMap.put("reg_id", commandMap.get("reg_id").toString());
            commandMap.put("reg_ip", ip);

            int process = userInfoDAO.insertUserInfo(commandMap.getMap());
            if (process > 0) {
                resultMap.put("status", 0);
                resultMap.put("msg", "정상적으로 사용자정보 등록이 되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "사용자정보 등록에 실패했습니다.\r\n관리자에게 문의해주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> updateUserInfo(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            String ip = CommonUtils.getClientIP();
            int process = 0;
            commandMap.put("mod_id", commandMap.get("mod_id").toString());
            commandMap.put("mod_ip", ip);

				process += userInfoDAO.updateUserInfo(commandMap.getMap());

            if (process > 0) {
                resultMap.put("status", 0);
                resultMap.put("msg", "정상적으로 사용자정보 수정이 완료되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "사용자정보 수정에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 완전삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> deleteUserInfo(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            commandMap.put("nCurrpage", "1");

            int process = userInfoDAO.deleteUserInfo(commandMap.getMap());
            if (process == 1) {
                resultMap.put("status", 0);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "정상적으로 사용자정보 삭제가 완료되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "사용자정보 삭제에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 다중입력/수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 다중입력 및 중복이 있을 경우 업데이트
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> multiInsertUpdateUserInfo(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            String ip = CommonUtils.getClientIP();
            commandMap.put("reg_id", commandMap.get("reg_id").toString());
            commandMap.put("reg_ip", ip);

            commandMap.put("mod_id", commandMap.get("reg_id").toString());
            commandMap.put("mod_ip", ip);

            commandMap.put("list", commandMap.getListMap());

            int process = userInfoDAO.multiInsertUpdateUserInfo(commandMap.getMap());
            commandMap.remove("list");
            if (process == 1) {
                resultMap.put("status", 0);
                resultMap.put("msg", "정상적으로 사용자정보 등록이 되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "사용자정보 등록에 실패했습니다.\r\n관리자에게 문의해주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 다중완전 삭제
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 다중 데이타 완전 삭제
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> multiIDeleteUserInfo(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            commandMap.put("nCurrpage", "1");

            commandMap.put("list", commandMap.getListMap());

            int process = userInfoDAO.multiIDeleteUserInfo(commandMap.getMap());
            commandMap.remove("list");
            if (process == 1) {
                resultMap.put("status", 0);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "정상적으로 사용자정보 삭제가 완료되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("pageParam", commandMap.getMap());
                resultMap.put("msg", "사용자정보 삭제에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 조건에 따른 단일 데이타 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @Override
    public Map<String, Object> insertUpdateCallUserInfo(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        try {
            int process = 0;

            process += userInfoDAO.insertUpdateCallUserInfo(commandMap.getMap());

            if (process > 0) {
                resultMap.put("status", 0);
                resultMap.put("msg", "정상적으로 사용자정보 호출이 완료되었습니다.");
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "사용자정보 호출에 실패했습니다.\r\n관리자에게 문의해 주세요.");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }


}

