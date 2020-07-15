/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : CommonController
 *   @Description   :
 *   @Author        : GACHINOEL
 *   @Version       : v1.0
 *   Copyright(c) 2019 WOOJIN All rights reserved
 *   ------------------------------------------------------------------------------
 *                    변         경         사         항
 *   ------------------------------------------------------------------------------
 *      DATE           AUTHOR                       DESCRIPTION
 *   ---------------  ----------    ------------------------------------------------
 *   2019.11.8       gachinoel     신규생성
 *   ------------------------------------------------------------------------------
 */

package com.woojin.commercial.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woojin.commercial.login.LoginVO;
import com.woojin.commercial.util.PageNavigater;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

@Controller
public class CommonController {
	Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("#{envir['post.key']}")
    private String POST_KEY;

    @RequestMapping(value="/")
    public String home(Locale locale, HttpSession httpSession) throws Exception {
        String reUrl = "";
        Object object = httpSession.getAttribute("loginInfo");
        LoginVO userVO = (LoginVO) object;
        if (userVO != null) {
	        if (userVO.getAuth_cd().equals("ADMIN")) {
	            reUrl = "redirect:/admin";
	        }
	        else if (userVO.getAuth_cd().equals("SHIPPING")) {
	            reUrl = "redirect:/shipping";
	        }
	        else if (userVO.getAuth_cd().equals("SUPPLY")) {
	            reUrl = "redirect:/supply";
	        }
	        else if (userVO.getAuth_cd().equals("GPSI")) {
	            reUrl = "redirect:/pgsi";
	        }
	        else{
	            reUrl = "redirect:/denied";
	        }
        }
        else {
        	reUrl = "redirect:/login";
        }
        return reUrl;
    }

    @RequestMapping(value="/denied")
    public ModelAndView denied(Locale locale, HttpSession httpSession) throws Exception {
        ModelAndView mv = new ModelAndView("/login/denied");
        return mv;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value="/common/getAddrApi", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listAddrApi(CommandMap commandMap) throws Exception{
        //파라메터 구하기
        //파라메터 구하기
        JSONObject jsonObject = new JSONObject();
        if (commandMap.getMap().size() > 0){
            for( Map.Entry<String, Object> entry : commandMap.getMap().entrySet() ) {
                String key = entry.getKey().toString();
                Object value = entry.getValue();
                if (!(key.equals("nCurrpage")) && !(key.equals("pageRecordCount"))&& !(key.equals("x"))&& !(key.equals("y"))) {
                    jsonObject.put(key, value);
                }
            }
        }

        //조회 하려는 페이지
        int nCurrpage = (commandMap.get("nCurrpage")!=null?Integer.parseInt(commandMap.get("nCurrpage").toString()):1);
        //한페이지에 보여줄 리스트 수
        int pageRecordCount = (commandMap.get("pageRecordCount")!=null?Integer.parseInt(commandMap.get("pageRecordCount").toString()):20);

        String resultType = "json";
        String keyword = commandMap.get("keyword").toString();

        // API 호출 URL 정보 설정
        String apiUrl = "http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+nCurrpage+"&countPerPage="+pageRecordCount+"&keyword="+ URLEncoder.encode(keyword,"UTF-8")+"&confmKey="+POST_KEY+"&resultType="+resultType;

        URL url = new URL(apiUrl);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        url.openStream(),"UTF-8"));
        StringBuffer sb = new StringBuffer();
        String tempStr = null;
        while(true){
            tempStr = br.readLine();
            if(tempStr == null) break;
            sb.append(tempStr); // 응답결과 JSON 저장
        }
        br.close();

        int nTotCnt = 0;

        List<Map<String, Object>> listParam = new ArrayList<Map<String, Object>>();

        if (sb.length() > 0){
            ObjectMapper api = new ObjectMapper();
            JsonNode apiroot = api.readTree(sb.toString());

            JsonNode results = apiroot.path("results");
            JsonNode common = results.path("common");
            JsonNode juso = results.path("juso");

            String errorCode = common.path("errorCode").asText();

            if ("0".equals(errorCode)){
                nTotCnt = common.path("totalCount").asInt();

                listParam = new ObjectMapper().readValue(juso.toString(), List.class) ;
            }
        }

        HashMap<String,Object> pageMap = new HashMap<String, Object>();
        pageMap.put("iTotRecord", nTotCnt);
        pageMap.put("iCurPage", nCurrpage);
        pageMap.put("bFirstLast", true);

        String pageNavigater = PageNavigater.getPageForm(pageMap);

        //jsp 에서 보여줄 정보 추출
        ModelAndView mv = new ModelAndView("/common/postAddr.do");
        mv.addObject("pageParam", jsonObject.toString());//현재 페이지
        mv.addObject("pageNavigater", pageNavigater);//페이징 폼
        mv.addObject("userinfoList", listParam);//검색

        return mv;
    }

    @RequestMapping(value="/common/error{error_code}.do")
    public ModelAndView error(HttpServletRequest request, @PathVariable String error_code) {
        ModelAndView mv = new ModelAndView("/common/error");
        String msg = (String) request.getAttribute("javax.servlet.error.message");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("STATUS_CODE", request.getAttribute("javax.servlet.error.status_code"));
        map.put("REQUEST_URI", request.getAttribute("javax.servlet.error.request_uri"));
        map.put("EXCEPTION_TYPE", request.getAttribute("javax.servlet.error.exception_type"));
        map.put("EXCEPTION", request.getAttribute("javax.servlet.error.exception"));
        map.put("SERVLET_NAME", request.getAttribute("javax.servlet.error.servlet_name"));

        try {
            int status_code = Integer.parseInt(request.getAttribute("javax.servlet.error.status_code").toString());
            switch (status_code) {
                case 400: msg = "잘못된 요청입니다."; break;
                case 403: msg = "접근이 금지되었습니다."; break;
                case 404: msg = "페이지를 찾을 수 없습니다."; break;
                case 405: msg = "요청된 메소드가 허용되지 않습니다."; break;
                case 500: msg = "서버에 오류가 발생하였습니다."; break;
                case 503: msg = "서비스를 사용할 수 없습니다."; break;
                default: msg = "알 수 없는 오류가 발생하였습니다."; break;
            }
        } catch(Exception e) {
            msg = "기타 오류가 발생하였습니다.";
        } finally {
            map.put("MESSAGE", msg);
        }

        //logging
        if(map.isEmpty() == false ) {
            Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
            Map.Entry<String,Object> entry = null;
            while(iterator.hasNext()) {
                entry = iterator.next();
                log.info("key : "+entry.getKey()+", value : "+entry.getValue());
            }
        }

        mv.addObject("error", map);
        return mv;
    }

}
