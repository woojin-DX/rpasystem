package com.woojin.commercial.batchjob.scheduling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix0VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix1VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix3VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix4VO;

@Controller
public class GpsiDataController {
	
	@Autowired
	GpsiDataService gpsiDataService;

	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/admin/listPsix0Data", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listPsix0Data(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/gpisdata");
        try {

            Map<String, Object> csvMap = gpsiDataService.listPsix0Data();
            List<GpsiPsix0VO> lstResult = (List<GpsiPsix0VO>) csvMap.get("listPsix0Data");

            /**
             * csv 파일을 쓰기위한 설정
             * 설명
             * D:\\test.csv : csv 파일저장할 위치+파일명
             * EUC-KR : 한글깨짐설정을 방지하기위한 인코딩설정(UTF-8로 지정해줄경우 한글깨짐)
             * ',' : 배열을 나눌 문자열
             * '"' : 값을 감싸주기위한 문자
             **/
            
            String path = "Z:\\A1. RPA\\02. g-psi"; //폴더 경로

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());
            
        	File file = new File(path);
            //file을 생성할 폴더가 없으면 생성합니다.
            file.mkdirs(); //폴더 생성합니다.

            String fullPath = path + "\\psix0_" + strToday + ".csv";
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		                    new FileOutputStream(fullPath), "utf-8"))) {
            	StringBuilder strBufOri = new StringBuilder();
            	strBufOri.append("데이터 종류별").append("	");
            	strBufOri.append("거점코드").append("	");
            	strBufOri.append("품목코드").append("	");
            	strBufOri.append("재고상태").append("	");
            	strBufOri.append("로트").append("	");
            	strBufOri.append("수급대상구분").append("	");
            	strBufOri.append("재고수량").append("	");
            	strBufOri.append("단위").append("	");
            	strBufOri.append("데이터 날짜").append("	");
            	strBufOri.append("예비항목1").append("	");
            	strBufOri.append("예비항목2").append("	");
            	strBufOri.append("예비항목3").append("	");
            	strBufOri.append("예비항목4").append("	");
            	strBufOri.append("예비항목5");
            	writer.write(strBufOri.toString());
            	
                for(GpsiPsix0VO m : lstResult) {
                    //배열을 이용하여 row를 CSVWriter 객체에 write
                	strBufOri = new StringBuilder();
                	strBufOri.append("\n");
                	strBufOri.append(m.getData_type()).append("	");
                	strBufOri.append(m.getBase_code()).append("	");
                	strBufOri.append(m.getMatnr()).append("	");
                	strBufOri.append(m.getInven_status()).append("	");
                	strBufOri.append(m.getLot()).append("	");
                	strBufOri.append(m.getInven_status()).append("	");
                	strBufOri.append(m.getSupply_div()).append("	");
                	strBufOri.append(m.getInven_qty()).append("	");
                	strBufOri.append(m.getMeins()).append("	");
                	strBufOri.append(m.getData_dt()).append("	");
                	strBufOri.append(m.getReserve1()).append("	");
                	strBufOri.append(m.getReserve2()).append("	");
                	strBufOri.append(m.getReserve3()).append("	");
                	strBufOri.append(m.getReserve4()).append("	");
                	strBufOri.append(m.getReserve5());
                	
                	writer.write(strBufOri.toString());
                }   
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
                mv.setViewName("/common/error");
            } 

        } catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("/common/error");
        }
        return mv;
    }
	
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/admin/listPsix1Data", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listPsix1Data(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/gpisdata");
        try {

            Map<String, Object> csvMap = gpsiDataService.listPsix1Data();
            List<GpsiPsix1VO> lstResult = (List<GpsiPsix1VO>) csvMap.get("listPsix1Data");

            /**
             * csv 파일을 쓰기위한 설정
             * 설명
             * D:\\test.csv : csv 파일저장할 위치+파일명
             * EUC-KR : 한글깨짐설정을 방지하기위한 인코딩설정(UTF-8로 지정해줄경우 한글깨짐)
             * ',' : 배열을 나눌 문자열
             * '"' : 값을 감싸주기위한 문자
             **/
            
            String path = "Z:\\A1. RPA\\02. g-psi"; //폴더 경로

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());
            
        	File file = new File(path);
            //file을 생성할 폴더가 없으면 생성합니다.
            file.mkdirs(); //폴더 생성합니다.

            String fullPath = path + "\\psix1_" + strToday + ".csv";
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		                    new FileOutputStream(fullPath), "utf-8"))) {
            	StringBuilder strBufOri = new StringBuilder();
            	strBufOri.append("데이터 종류별").append("	");
            	strBufOri.append("출하원 거점코드").append("	");
            	strBufOri.append("출하처 거점코드").append("	");
            	strBufOri.append("품목코드").append("	");
            	strBufOri.append("기간개시날짜").append("	");
            	strBufOri.append("기간종료날짜").append("	");
            	strBufOri.append("판매계획수량").append("	");
            	strBufOri.append("단위").append("	");
            	strBufOri.append("데이터 날짜").append("	");
            	writer.write(strBufOri.toString());
            	
                for(GpsiPsix1VO m : lstResult) {
                    //배열을 이용하여 row를 CSVWriter 객체에 write
                	strBufOri = new StringBuilder();
                	strBufOri.append("\n");
                	strBufOri.append(m.getData_type()).append("	");
                	strBufOri.append(m.getBase_code()).append("	");
                	strBufOri.append(m.getBase_code1()).append("	");
                	strBufOri.append(m.getMatnr()).append("	");
                	strBufOri.append(m.getPsttr()).append("	");
                	strBufOri.append(m.getPedtr()).append("	");
                	strBufOri.append(m.getGsmng()).append("	");
                	strBufOri.append(m.getMeins()).append("	");
                	strBufOri.append(m.getData_dt());
                	
                	writer.write(strBufOri.toString());
                }   
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
                mv.setViewName("/common/error");
            } 

        } catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("/common/error");
        }
        return mv;

    }
	
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/admin/listPsix3Data", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listPsix3Data(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/gpisdata");
        try {

            Map<String, Object> csvMap = gpsiDataService.listPsix3Data();
            List<GpsiPsix3VO> lstResult = (List<GpsiPsix3VO>) csvMap.get("listPsix3Data");

            /**
             * csv 파일을 쓰기위한 설정
             * 설명
             * D:\\test.csv : csv 파일저장할 위치+파일명
             * EUC-KR : 한글깨짐설정을 방지하기위한 인코딩설정(UTF-8로 지정해줄경우 한글깨짐)
             * ',' : 배열을 나눌 문자열
             * '"' : 값을 감싸주기위한 문자
             **/
            
            String path = "Z:\\A1. RPA\\02. g-psi"; //폴더 경로

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());
            
        	File file = new File(path);
            //file을 생성할 폴더가 없으면 생성합니다.
            file.mkdirs(); //폴더 생성합니다.

            String fullPath = path + "\\psix3_" + strToday + ".csv";
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		                    new FileOutputStream(fullPath), "utf-8"))) {
            	StringBuilder strBufOri = new StringBuilder();
            	strBufOri.append("데이터 종류별").append("	");
            	strBufOri.append("거점코드").append("	");
            	strBufOri.append("품목코드").append("	");
            	strBufOri.append("기간개시날짜").append("	");
            	strBufOri.append("기간종료날짜").append("	");
            	strBufOri.append("판매계획수량").append("	");
            	strBufOri.append("단위").append("	");
            	strBufOri.append("데이터 날짜").append("	");
            	writer.write(strBufOri.toString());
            	
                for(GpsiPsix3VO m : lstResult) {
                    //배열을 이용하여 row를 CSVWriter 객체에 write
                	strBufOri = new StringBuilder();
                	strBufOri.append("\n");
                	strBufOri.append(m.getData_type()).append("	");
                	strBufOri.append(m.getBase_code()).append("	");
                	strBufOri.append(m.getMatnr()).append("	");
                	strBufOri.append(m.getPsttr()).append("	");
                	strBufOri.append(m.getPedtr()).append("	");
                	strBufOri.append(m.getGsmng()).append("	");
                	strBufOri.append(m.getMeins()).append("	");
                	strBufOri.append(m.getData_dt());
                	
                	writer.write(strBufOri.toString());
                }   
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
                mv.setViewName("/common/error");
            } 

        } catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("/common/error");
        }
        return mv;

    }
	
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/admin/listPsix4Data", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listPsix4Data(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/gpisdata");
        try {

            Map<String, Object> csvMap = gpsiDataService.listPsix4Data();
            List<GpsiPsix4VO> lstResult = (List<GpsiPsix4VO>) csvMap.get("listPsix4Data");

            /**
             * csv 파일을 쓰기위한 설정
             * 설명
             * D:\\test.csv : csv 파일저장할 위치+파일명
             * EUC-KR : 한글깨짐설정을 방지하기위한 인코딩설정(UTF-8로 지정해줄경우 한글깨짐)
             * ',' : 배열을 나눌 문자열
             * '"' : 값을 감싸주기위한 문자
             **/
            
            String path = "Z:\\A1. RPA\\02. g-psi"; //폴더 경로

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());
            
        	File file = new File(path);
            //file을 생성할 폴더가 없으면 생성합니다.
            file.mkdirs(); //폴더 생성합니다.

            String fullPath = path + "\\psix4_" + strToday + ".csv";
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		                    new FileOutputStream(fullPath), "utf-8"))) {
            	StringBuilder strBufOri = new StringBuilder();
            	strBufOri.append("데이터 종류별").append("	");
            	strBufOri.append("사용실적키").append("	");
            	strBufOri.append("거점코드").append("	");
            	strBufOri.append("품목코드").append("	");
            	strBufOri.append("날짜").append("	");
            	strBufOri.append("사용실적수").append("	");
            	strBufOri.append("단위").append("	");
            	strBufOri.append("로트").append("	");
            	strBufOri.append("데이터 날짜").append("	");
            	strBufOri.append("예비항목1").append("	");
            	strBufOri.append("예비항목2").append("	");
            	strBufOri.append("예비항목3").append("	");
            	strBufOri.append("예비항목4").append("	");
            	strBufOri.append("예비항목5");
            	writer.write(strBufOri.toString());
            	
                for(GpsiPsix4VO m : lstResult) {
                    //배열을 이용하여 row를 CSVWriter 객체에 write
                	strBufOri = new StringBuilder();
                	strBufOri.append("\n");
                	strBufOri.append(m.getData_type()).append("	");
                	strBufOri.append(m.getMatnr()).append("	");
                	strBufOri.append(m.getBase_code()).append("	");
                	strBufOri.append(m.getMatnr()).append("	");
                	strBufOri.append(m.getBudat()).append("	");
                	strBufOri.append(m.getErfmg()).append("	");
                	strBufOri.append(m.getErfme()).append("	");
                	strBufOri.append(m.getLot()).append("	");
                	strBufOri.append(m.getData_dt()).append("	");
                	strBufOri.append(m.getReserve1()).append("	");
                	strBufOri.append(m.getReserve2()).append("	");
                	strBufOri.append(m.getReserve3()).append("	");
                	strBufOri.append(m.getReserve4()).append("	");
                	strBufOri.append(m.getReserve5());
                	
                	writer.write(strBufOri.toString());
                }   
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
                mv.setViewName("/common/error");
            } 

        } catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("/common/error");
        }
        return mv;

    }
}
