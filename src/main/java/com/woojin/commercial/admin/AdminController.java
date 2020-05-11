package com.woojin.commercial.admin;

import com.woojin.commercial.common.CommandMap;
import com.woojin.commercial.common.SearchVO;
import com.woojin.commercial.login.LoginVO;
import com.woojin.commercial.util.ExcelBuilder;
import com.woojin.commercial.util.StringUtil;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class AdminController {
    Logger log = Logger.getLogger(this.getClass());

    //상용자정보 서비스 클래스 호출
    @Autowired
    AdminService adminService;

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView adminList(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO != null) {
	            if (userVO.getAuth_cd().equals("ADMIN")) {
	                commandMap.put("company_cd",userVO.getCompany_cd().toString());
	                commandMap.put("role","ADMIN");
	                /*
	                long start = System.currentTimeMillis();
	                System.out.println("####ehCache is Running...");
	                Map<String, Object> resultMap = adminService.listShipping(commandMap);
	                long end = System.currentTimeMillis();
	                System.out.println("시간측정"+"  :  " +Long.toString(end-start));
	                */
	                Map<String, Object> resultMap = adminService.listShipping(commandMap);
	
	                //jsp 에서 보여줄 정보 추출
	                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
	                mv.addObject("infoParam", userVO); //변수값
	                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
	                mv.addObject("commonList", resultMap.get("commonList")); //검색
	                mv.addObject("packingList", resultMap.get("packingList")); //검색
	                mv.addObject("materialList", resultMap.get("materialList")); //검색
	                mv.addObject("companyList", resultMap.get("companyList")); //검색
	                mv.addObject("shippingList", resultMap.get("shippingList")); //검색
	                mv.setViewName("/admin/admin_shipping");
	            }
	            else{
	                mv.setViewName("/login/denied");
	            }
	            mv.addObject("userRole", userVO.getAuth_cd());//변수값
	            mv.addObject("pagecng", "LIST");//변수값
            }
            else {
            	mv.setViewName("/login/login");
            }
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/prelist", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView adminPreList(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                commandMap.put("role","ADMIN");
                commandMap.put("common_cd","ST_LST");
                Map<String, Object> resultMap = adminService.listShipping(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("infoParam", userVO); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("commonList", resultMap.get("commonList")); //검색
                mv.addObject("packingList", resultMap.get("packingList")); //검색
                mv.addObject("materialList", resultMap.get("materialList")); //검색
                mv.addObject("companyList", resultMap.get("companyList")); //검색
                mv.addObject("shippingList", resultMap.get("shippingList")); //검색
                mv.setViewName("/admin/admin_shipping");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
            mv.addObject("pagecng", "PRELIST");//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/boslist", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView adminBosList(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                commandMap.put("role","ADMIN");
                commandMap.put("common_cd","ST_BOS");
                Map<String, Object> resultMap = adminService.listShipping(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("infoParam", userVO); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("commonList", resultMap.get("commonList")); //검색
                mv.addObject("packingList", resultMap.get("packingList")); //검색
                mv.addObject("materialList", resultMap.get("materialList")); //검색
                mv.addObject("companyList", resultMap.get("companyList")); //검색
                mv.addObject("shippingList", resultMap.get("shippingList")); //검색
                mv.setViewName("/admin/admin_shipping");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
            mv.addObject("pagecng", "BOSLIST");//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/cfmlist", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView cfmlist(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                commandMap.put("role","ADMIN");
                commandMap.put("common_cd","ST_CFM");
                Map<String, Object> resultMap = adminService.listShippingCfm(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("infoParam", userVO); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("commonList", resultMap.get("commonList")); //검색
                mv.addObject("packingList", resultMap.get("packingList")); //검색
                mv.addObject("materialList", resultMap.get("materialList")); //검색
                mv.addObject("companyList", resultMap.get("companyList")); //검색
                mv.addObject("shippingList", resultMap.get("shippingList")); //검색
                mv.setViewName("/shipping/shipping");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
            mv.addObject("pagecng", "CFMLIST");//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }
    
    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/psvlist", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView psvlist(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                commandMap.put("role","ADMIN");
                commandMap.put("common_cd","ST_CFM");
                Map<String, Object> resultMap = adminService.listShippingPsv(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("infoParam", userVO); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("commonList", resultMap.get("commonList")); //검색
                mv.addObject("packingList", resultMap.get("packingList")); //검색
                mv.addObject("materialList", resultMap.get("materialList")); //검색
                mv.addObject("companyList", resultMap.get("companyList")); //검색
                mv.addObject("shippingList", resultMap.get("shippingList")); //검색
                mv.setViewName("/admin/admin_sapshipping");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
            mv.addObject("pagecng", "CFMLIST");//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/spglist", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView spglist(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                commandMap.put("role","ADMIN");
                commandMap.put("common_cd","ST_SPG");
                Map<String, Object> resultMap = adminService.listShippingCfm(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("infoParam", userVO); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("commonList", resultMap.get("commonList")); //검색
                mv.addObject("packingList", resultMap.get("packingList")); //검색
                mv.addObject("materialList", resultMap.get("materialList")); //검색
                mv.addObject("companyList", resultMap.get("companyList")); //검색
                mv.addObject("shippingList", resultMap.get("shippingList")); //검색
                mv.setViewName("/shipping/shipping");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
            mv.addObject("pagecng", "SPGLIST");//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/sumlist", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView adminSumList(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                Map<String, Object> resultMap = adminService.listShippingSum(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("materialList", resultMap.get("materialList")); //검색
                mv.addObject("shippingList", resultMap.get("shippingList")); //검색
                mv.setViewName("/admin/admin_shippingsum");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }


    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView adminDataList(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                commandMap.put("company_cd",userVO.getCompany_cd().toString());
                commandMap.put("role","ADMIN");
                Map<String, Object> resultMap = adminService.listShipping(commandMap);

                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("infoParam", userVO); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("shippingList", resultMap.get("shippingList")); //검색
                mv.setViewName("/admin/admin_shippinglist");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 출하정보 상세내역
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 상세내역
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/detailShipping", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> detailShipping(CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> cngMap = new HashMap<String,Object>();
        try {
            Map<String, Object> msgMap = adminService.detailShipping(commandMap);
            cngMap = StringUtil.convertObjectToMap(msgMap.get("shippingDetail"));

            JSONObject pageParam = StringUtil.getJsonStringFromMap(cngMap);
            resultMap.put("status", "0");
            resultMap.put("msg", "데이타를 정상적으로 호출하였습니다");
            resultMap.put("shippingDetail", pageParam);
        }
        catch(Exception e){
            log.error(e);
            resultMap.put("status", "1");
            resultMap.put("msg", "데이타 호출에 실패하였습니다");
            //throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 등록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일 내용 등록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/processShipping", method = {RequestMethod.GET, RequestMethod.POST})
    public String processShipping(SearchVO searchVO, HttpSession httpSession,
                                  CommandMap commandMap, RedirectAttributes rttr) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        Calendar cal = Calendar.getInstance();

        try {
            String status = "0";
            String msg = "";
            Object object = httpSession.getAttribute("loginInfo");
            if (object != null) {
                LoginVO userVO = (LoginVO) object;
                commandMap.put("modify_id", userVO.getUser_id());
                commandMap.put("process_id", userVO.getUser_id());
            }
            else{
                commandMap.put("modify_id", "");
                commandMap.put("process_id", "");
            }

            String firstDate = commandMap.get("accept_dt_start").toString();
            if (commandMap.get("accept_dt_start") == null) {
                Calendar mon = Calendar.getInstance();
                mon.add(Calendar.MONTH , -1);
                firstDate = formatter.format(mon.getTime());
            }
            String lastDate = commandMap.get("accept_dt_end").toString();
            if (commandMap.get("accept_dt_end") == null) {
                Calendar time = Calendar.getInstance();
                lastDate = formatter.format(time.getTime());
            }

            String process_cd = commandMap.get("process_cd").toString();
            String nextprocess_cd = commandMap.get("nextprocess_cd").toString();
            String processFlag = commandMap.get("processFlag").toString();

            if (processFlag.equals("insert")) {
                Map<String, Object> msgMap = adminService.insertShipping(commandMap);
                rttr.addFlashAttribute("resultMsg", msgMap.get("msg"));
                rttr.addFlashAttribute("resultFlag", "registerOK");
            }
            else if (processFlag.equals("update")) {
                Map<String, Object> msgMap = adminService.updateShipping(commandMap);
                rttr.addFlashAttribute("resultMsg", msgMap.get("msg"));
                rttr.addFlashAttribute("resultFlag", "modifyOK");
            }
            else if (processFlag.equals("delete")) {
                Map<String, Object> msgMap = adminService.deleteShipping(commandMap);
                rttr.addFlashAttribute("resultMsg", msgMap.get("msg"));
                rttr.addFlashAttribute("resultFlag", "deleteOK");
                searchVO.setNCurrpage(1);
            }
            else if (processFlag.equals("finish")) {
                Map<String, Object> msgMap = adminService.deleteFinishShipping(commandMap);
                rttr.addFlashAttribute("resultMsg", msgMap.get("msg"));
                rttr.addFlashAttribute("resultFlag", "finishOK");
                searchVO.setNCurrpage(1);
            }
            rttr.addFlashAttribute("nCurrpage", searchVO.getNCurrpage());
            rttr.addFlashAttribute("pagemode", searchVO.getPagemode());
            rttr.addFlashAttribute("accept_dt_start", searchVO.getAccept_dt_start());
            rttr.addFlashAttribute("accept_dt_end", searchVO.getAccept_dt_end());
            rttr.addFlashAttribute("coperation_cd", searchVO.getCoperation_cd());
            rttr.addFlashAttribute("common_cd", searchVO.getCommon_cd());
            rttr.addFlashAttribute("material_list", searchVO.getMaterial_list());
            rttr.addFlashAttribute("schword", searchVO.getSchword());
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return "redirect:/admin";
    }

    /* *******************************************************************************************
     * 함수  제목 : 발주정보 등록
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일 내용 등록
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/processShippingInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> processShippingInfo(HttpServletRequest request,
                                            HttpServletResponse response,
                                            HttpSession httpSession,
                                            CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        Calendar cal = Calendar.getInstance();

        try {
            String status = "0";
            String msg = "";
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                commandMap.put("modify_id", userVO.getUser_id());
                commandMap.put("process_id", userVO.getUser_id());

                String processFlag = commandMap.get("processFlag").toString();
                Map<String, Object> msgMap = new HashMap<String, Object>();

                if (processFlag.equals("insert")) {
                    msgMap = adminService.insertShipping(commandMap);
                } else if (processFlag.equals("update")) {
                    msgMap = adminService.updateShipping(commandMap);
                } else if (processFlag.equals("delete")) {
                    msgMap = adminService.deleteShipping(commandMap);
                } else if (processFlag.equals("finish")) {
                    msgMap = adminService.deleteFinishShipping(commandMap);
                }
                resultMap.put("status", msgMap.get("status"));
                resultMap.put("msg", msgMap.get("msg"));
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "권한이 업습니다.\r\n관리자에게 문의해주세요");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
        return resultMap;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/mtmlist", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mtmList(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {

                Map<String, Object> resultMap = adminService.listShippingMtmPage(commandMap);
                //jsp 에서 보여줄 정보 추출
                mv.addObject("pageParam", resultMap.get("pageParam")); //변수값
                mv.addObject("pageNavigater", resultMap.get("pageNavigater")); //페이징 폼
                mv.addObject("shippingMtmList", resultMap.get("shippingMtmList")); //검색

                mv.setViewName("/shipping/mtmlist");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/mtmpop", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mtmPop(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                commandMap.put("modify_id", userVO.getUser_id());
                commandMap.put("process_id", userVO.getUser_id());

                //jsp 에서 보여줄 정보 추출
                //Map<String, Object> resultMap = adminService.detailShipping(commandMap);
                Map<String, Object> matrialMap = adminService.listShippingMtm(commandMap);

                //mv.addObject("shippingDetail", resultMap.get("shippingDetail")); //변수값
                mv.addObject("shippingMtmList", matrialMap.get("shippingMtmList")); //변수값
                mv.setViewName("/admin/admin_mtmpop");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/mtmreg", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mtmReg(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                Map<String, Object> resultMap = adminService.detailShipping(commandMap);
                Map<String, Object> matrialMap = adminService.listMaterialMtm(commandMap);
                String sConfirm_Qty = "0";
                String supply_dt = "";
                if (commandMap.get("confirm_qty") != null) {
                    sConfirm_Qty = commandMap.get("confirm_qty").toString();
                }
                if (commandMap.get("supply_dt") != null) {
                    supply_dt = commandMap.get("supply_dt").toString();
                }
                mv.addObject("confirm_qty", sConfirm_Qty); //변수값
                mv.addObject("supply_dt", supply_dt); //변수값
                mv.addObject("shippingDetail", resultMap.get("shippingDetail")); //변수값
                mv.addObject("mtmMatrialList", matrialMap.get("mtmMatrialList")); //변수값
                mv.setViewName("/admin/admin_mtmpopReg");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value = "/admin/materialpop", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView materialpop(CommandMap commandMap, HttpSession httpSession) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN")) {
                commandMap.put("modify_id", userVO.getUser_id());
                commandMap.put("process_id", userVO.getUser_id());

                //jsp 에서 보여줄 정보 추출
                //Map<String, Object> resultMap = adminService.detailShipping(commandMap);
                Map<String, Object> matrialMap = adminService.listShippingMatloc(commandMap);

                //mv.addObject("shippingDetail", resultMap.get("shippingDetail")); //변수값
                mv.addObject("shippingMaterialList", matrialMap.get("shippingMaterialList")); //변수값
                mv.setViewName("/admin/admin_materialpop");
            }
            else{
                mv.setViewName("/login/denied");
            }
            mv.addObject("userRole", userVO.getAuth_cd());//변수값
        }
        catch(Exception e){
            log.error(e);
            mv.setViewName("/login/login");
        }
        return mv;
    }

    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 목록
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping(value = "/admin/shippingexcel", method = {RequestMethod.GET, RequestMethod.POST})
    public void adminShippingExcel(CommandMap commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = new ModelAndView();

        try {
            String excelTitle = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());

            Map<String, Object> tempMap = new HashMap<String, Object>();

            Map<String, Object> titleStyleMap = new HashMap<String, Object>();
            List<Map<String, Object>> fieldInfoList = new ArrayList<Map<String, Object>>();
            Map<String, Object> excelInfpMap = new HashMap<String, Object>();

            Map<String, Object> resultMap = adminService.listShippingExcel(commandMap);
            List<Object> objResult = (List<Object>) resultMap.get("shippingList");

            excelTitle = "출하정보";

            //첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
            Map<String, Object> headerMap = new HashMap<String, Object>();

            headerMap.put("sRow", "1");
            headerMap.put("eRow", "1");
            headerMap.put("sCol", "0");
            headerMap.put("eCol", "16");
            headerMap.put("fontType", "titleLine");
            headerMap.put("fontColor", "000000");
            headerMap.put("styleColor", "FFFFFF");
            headerMap.put("textAlign", "center");
            headerMap.put("textVAlign", "center");
            headerMap.put("line", "none");
            headerMap.put("title", excelTitle);

            titleStyleMap.put("sRow", "3");
            titleStyleMap.put("eRow", "3");
            titleStyleMap.put("sCol", "0");
            titleStyleMap.put("eCol", "16");
            titleStyleMap.put("fontType", "subtitle");
            titleStyleMap.put("fontColor", "000000");
            titleStyleMap.put("styleColor", "ECF5FC");
            titleStyleMap.put("textAlign", "center");
            titleStyleMap.put("textVAlign", "center");
            titleStyleMap.put("line", "line");

            fieldInfoList = new ArrayList<Map<String, Object>>();

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "process_nm");
            tempMap.put("cellTitle", "상태");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 10*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "order_dt");
            tempMap.put("cellTitle", "발주일자");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "company_nm");
            tempMap.put("cellTitle", "업체명");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 20*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "material_num");
            tempMap.put("cellTitle", "자재코드");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 16*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "mtart_nm");
            tempMap.put("cellTitle", "자재유형");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "bstrf");
            tempMap.put("cellTitle", "포장단위");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "place_nm");
            tempMap.put("cellTitle", "납품처");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);
            
            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "supply_req_qty");
            tempMap.put("cellTitle", "납품요청수량");
            tempMap.put("fileType", "Int");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "right");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);
            
            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "confirm_qty");
            tempMap.put("cellTitle", "납품확정수량");
            tempMap.put("fileType", "Int");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "right");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);


            
            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "supply_dt");
            tempMap.put("cellTitle", "출하일");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "packing_nm");
            tempMap.put("cellTitle", "포장방법");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 23*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "shipping_method");
            tempMap.put("cellTitle", "출하방법");
            tempMap.put("fileType", "String");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "center");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);
            
            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "supply_qty");
            tempMap.put("cellTitle", "출하수량");
            tempMap.put("fileType", "Int");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "right");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);
            
            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "remain_qty");
            tempMap.put("cellTitle", "잔여수량");
            tempMap.put("fileType", "Int");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "right");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);            

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "inven_use_qty");
            tempMap.put("cellTitle", "가용재고");
            tempMap.put("fileType", "Int");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "right");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);            

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "mtm_use_qty");
            tempMap.put("cellTitle", "MTM가용재고");
            tempMap.put("fileType", "Int");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "right");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);            

            tempMap = new HashMap<String, Object>();
            tempMap.put("field", "prod_order_qty");
            tempMap.put("cellTitle", "생산오더수량");
            tempMap.put("fileType", "Int");
            tempMap.put("cellWidth", 13*256);
            tempMap.put("fontType", "content");
            tempMap.put("fontColor", "000000");
            tempMap.put("styleColor", "FFFFFF");
            tempMap.put("textAlign", "right");
            tempMap.put("textVAlign", "center");
            tempMap.put("line", "dot");
            tempMap.put("fomule", "");
            fieldInfoList.add(tempMap);            
            
            excelInfpMap = new HashMap<String, Object>();
            excelInfpMap.put("headerMap", headerMap);
            excelInfpMap.put("titleStyleMap", titleStyleMap);
            excelInfpMap.put("fieldInfoList", fieldInfoList);

            SXSSFWorkbook workbook = ExcelBuilder.buildExcelXSSNon(excelTitle, excelInfpMap, objResult, false);


            String realExcelFilename = null;
            realExcelFilename = URLEncoder.encode(excelTitle + "_"  + strToday, "UTF-8");
            realExcelFilename = realExcelFilename.replaceAll("\\+", " ");

            /*
             * HTTP Header 설정.
             */
            response.setContentType("application/vnd.ms-excel; name=\"" + realExcelFilename + ".xlsx\"");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + realExcelFilename + ".xlsx\"");
            response.setHeader("Content-Transfer-Encoding", "binary");
//            response.setHeader("Content-Length", Long.toString(fileDownLoadInputVO.getlFileSize()));
            response.setHeader("Cache-Control", "no-cahe, no-store, must-revalidate\r\n");
            response.setHeader("Connection", "close");

            //FileOutputStream fileOut = new FileOutputStream(realExcelFilename + ".xlsx");
            //OutputStream out = response.getOutputStream();
            OutputStream fileOut = response.getOutputStream();

            workbook.write(fileOut);
            fileOut.close();
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }
    }
    
    
    /* *******************************************************************************************
     * 함수  제목 : 발주정보 수정
     * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
     * 내      용 : 단일내용 수정
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    @RequestMapping(value="/admin/multiUpdateShippingSupply", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateSupplyRecive(HttpSession httpSession,
                                            CommandMap commandMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Object object = httpSession.getAttribute("loginInfo");
            LoginVO userVO = (LoginVO) object;
            if (userVO.getAuth_cd().equals("ADMIN") || userVO.getAuth_cd().equals("SHIPPING")) {
                Map<String, Object> msgMap = adminService.multiUpdateShippingSupply(commandMap);

                resultMap.put("status", msgMap.get("status"));
                resultMap.put("msg", msgMap.get("msg"));
            }
            else{
                resultMap.put("status", 1);
                resultMap.put("msg", "권한이 업습니다.\r\n관리자에게 문의해주세요");
            }
        }
        catch(Exception e){
            log.error(e);
            throw e;
        }

        return resultMap;
    }
}
