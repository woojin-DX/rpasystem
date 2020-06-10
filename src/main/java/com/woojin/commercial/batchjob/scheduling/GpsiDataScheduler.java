package com.woojin.commercial.batchjob.scheduling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix0VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix1VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix3VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix4VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix5VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix6VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix7VO;
import com.woojin.commercial.batchjob.scheduling.GpsiDataVO.GpsiPsix9VO;

@Component
public class GpsiDataScheduler {
	@Autowired
	GpsiDataService gpsiDataService;
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron = "0 0 10 * * ?")
	public void schedulerPsix0() throws Exception {
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
            
            String path = "Z:\\A1. RPA\\02. g-psi\\psix0"; //폴더 경로

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
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron = "0 5 10 * * ?")
	public void schedulerPsix1() throws Exception {
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
            
            String path = "Z:\\A1. RPA\\02. g-psi\\psix1"; //폴더 경로

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());
            
        	File file = new File(path);
            //file을 생성할 폴더가 없으면 생성합니다.
            file.mkdirs(); //폴더 생성합니다.

            String fullPath = path + "\\psix1_" + strToday + ".txt";
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		                    new FileOutputStream(fullPath), "euc-kr"))) {
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
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron = "0 15 10 * * ?")
	public void schedulerPsix3() throws Exception {
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
            
            String path = "Z:\\A1. RPA\\02. g-psi\\psix3"; //폴더 경로

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());
            
        	File file = new File(path);
            //file을 생성할 폴더가 없으면 생성합니다.
            file.mkdirs(); //폴더 생성합니다.

            String fullPath = path + "\\psix3_" + strToday + ".txt";
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		                    new FileOutputStream(fullPath), "euc-kr"))) {
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
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron = "0 20 10 * * ?")
	public void schedulerPsix4() throws Exception {
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
            
            String path = "Z:\\A1. RPA\\02. g-psi\\psix4"; //폴더 경로

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());
            
        	File file = new File(path);
            //file을 생성할 폴더가 없으면 생성합니다.
            file.mkdirs(); //폴더 생성합니다.

            String fullPath = path + "\\psix4_" + strToday + ".txt";
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		                    new FileOutputStream(fullPath), "euc-kr"))) {
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
                	strBufOri.append(m.getMblnr()).append("	");
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
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron = "0 25 10 * * ?")
	public void schedulerPsix5() throws Exception {
        try {

            Map<String, Object> csvMap = gpsiDataService.listPsix5Data();
            List<GpsiPsix5VO> lstResult = (List<GpsiPsix5VO>) csvMap.get("listPsix5Data");

            /**
             * csv 파일을 쓰기위한 설정
             * 설명
             * D:\\test.csv : csv 파일저장할 위치+파일명
             * EUC-KR : 한글깨짐설정을 방지하기위한 인코딩설정(UTF-8로 지정해줄경우 한글깨짐)
             * ',' : 배열을 나눌 문자열
             * '"' : 값을 감싸주기위한 문자
             **/
            
            String path = "Z:\\A1. RPA\\02. g-psi\\psix5"; //폴더 경로

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());
            
        	File file = new File(path);
            //file을 생성할 폴더가 없으면 생성합니다.
            file.mkdirs(); //폴더 생성합니다.

            String fullPath = path + "\\psix5_" + strToday + ".txt";
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		                    new FileOutputStream(fullPath), "euc-kr"))) {
            	StringBuilder strBufOri = new StringBuilder();
            	strBufOri.append("Data type").append("	");
            	strBufOri.append("Overseas stock site code").append("	");
            	strBufOri.append("Item code").append("	");
            	strBufOri.append("From date").append("	");
            	strBufOri.append("To Date").append("	");
            	strBufOri.append("Parts consumption plan quantity").append("	");
            	strBufOri.append("Unit Of measure").append("	");
            	strBufOri.append("Extract date").append("	");
            	writer.write(strBufOri.toString());
            	
                for(GpsiPsix5VO m : lstResult) {
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
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron = "0 30 10 * * ?")
	public void schedulerPsix6() throws Exception {
        try {

            Map<String, Object> csvMap = gpsiDataService.listPsix6Data();
            List<GpsiPsix6VO> lstResult = (List<GpsiPsix6VO>) csvMap.get("listPsix6Data");

            /**
             * csv 파일을 쓰기위한 설정
             * 설명
             * D:\\test.csv : csv 파일저장할 위치+파일명
             * EUC-KR : 한글깨짐설정을 방지하기위한 인코딩설정(UTF-8로 지정해줄경우 한글깨짐)
             * ',' : 배열을 나눌 문자열
             * '"' : 값을 감싸주기위한 문자
             **/
            
            String path = "Z:\\A1. RPA\\02. g-psi\\psix6"; //폴더 경로

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());
            
        	File file = new File(path);
            //file을 생성할 폴더가 없으면 생성합니다.
            file.mkdirs(); //폴더 생성합니다.

            String fullPath = path + "\\psix6_" + strToday + ".txt";
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		                    new FileOutputStream(fullPath), "euc-kr"))) {
            	StringBuilder strBufOri = new StringBuilder();
            	strBufOri.append("Data type").append("	");
            	strBufOri.append("Statement number").append("	");
            	strBufOri.append("Overseas stock site code").append("	");
            	strBufOri.append("Item code").append("	");
            	strBufOri.append("Date").append("	");
            	strBufOri.append("Actual production quantity").append("	");
            	strBufOri.append("Unit Of measure").append("	");
            	strBufOri.append("LOT").append("	");
            	strBufOri.append("Extract date").append("	");
            	strBufOri.append("Spare items1").append("	");
            	strBufOri.append("Spare items2").append("	");
            	strBufOri.append("Spare items3").append("	");
            	strBufOri.append("Spare items4").append("	");
            	strBufOri.append("Spare items5");
            	writer.write(strBufOri.toString());
            	
                for(GpsiPsix6VO m : lstResult) {
                    //배열을 이용하여 row를 CSVWriter 객체에 write
                	strBufOri = new StringBuilder();
                	strBufOri.append("\n");
                	strBufOri.append(m.getData_type()).append("	");
                	strBufOri.append(m.getMblnr()).append("	");
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
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron = "0 35 10 * * ?")
	public void schedulerPsix7() throws Exception {
        try {

            Map<String, Object> csvMap = gpsiDataService.listPsix7Data();
            List<GpsiPsix7VO> lstResult = (List<GpsiPsix7VO>) csvMap.get("listPsix7Data");

            /**
             * csv 파일을 쓰기위한 설정
             * 설명
             * D:\\test.csv : csv 파일저장할 위치+파일명
             * EUC-KR : 한글깨짐설정을 방지하기위한 인코딩설정(UTF-8로 지정해줄경우 한글깨짐)
             * ',' : 배열을 나눌 문자열
             * '"' : 값을 감싸주기위한 문자
             **/
            
            String path = "Z:\\A1. RPA\\02. g-psi\\psix7"; //폴더 경로

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());
            
        	File file = new File(path);
            //file을 생성할 폴더가 없으면 생성합니다.
            file.mkdirs(); //폴더 생성합니다.

            String fullPath = path + "\\psix7_" + strToday + ".txt";
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		                    new FileOutputStream(fullPath), "euc-kr"))) {
            	StringBuilder strBufOri = new StringBuilder();
            	strBufOri.append("Data type").append("	");
            	strBufOri.append("Statement number").append("	");
            	strBufOri.append("Overseas stock site code").append("	");
            	strBufOri.append("Shipping agent site code").append("	");
            	strBufOri.append("Item code").append("	");
            	strBufOri.append("Date").append("	");
            	strBufOri.append("Actual production quantity").append("	");
            	strBufOri.append("Unit Of measure").append("	");
            	strBufOri.append("P.O. number").append("	");
            	strBufOri.append("P.O. Detail number").append("	");
            	strBufOri.append("LOT").append("	");
            	strBufOri.append("Extract date").append("	");
            	strBufOri.append("Spare items1").append("	");
            	strBufOri.append("Spare items2").append("	");
            	strBufOri.append("Spare items3").append("	");
            	strBufOri.append("Spare items4").append("	");
            	strBufOri.append("Spare items5");
            	writer.write(strBufOri.toString());
            	
                for(GpsiPsix7VO m : lstResult) {
                    //배열을 이용하여 row를 CSVWriter 객체에 write
                	strBufOri = new StringBuilder();
                	strBufOri.append("\n");
                	strBufOri.append(m.getData_type()).append("	");
                	strBufOri.append(m.getMblnr()).append("	");
                	strBufOri.append(m.getBase_code()).append("	");
                	strBufOri.append(m.getPlace_code()).append("	");
                	strBufOri.append(m.getMatnr()).append("	");
                	strBufOri.append(m.getBudat()).append("	");
                	strBufOri.append(m.getMenge()).append("	");
                	strBufOri.append(m.getMeins()).append("	");
                	strBufOri.append(m.getEbeln()).append("	");
                	strBufOri.append(m.getEbelp()).append("	");
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
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	
	@SuppressWarnings("unchecked")
	@Scheduled(cron = "0 45 10 * * ?")
	public void schedulerPsix9() throws Exception {
        try {

            Map<String, Object> csvMap = gpsiDataService.listPsix9Data();
            List<GpsiPsix9VO> lstResult = (List<GpsiPsix9VO>) csvMap.get("listPsix9Data");

            /**
             * csv 파일을 쓰기위한 설정
             * 설명
             * D:\\test.csv : csv 파일저장할 위치+파일명
             * EUC-KR : 한글깨짐설정을 방지하기위한 인코딩설정(UTF-8로 지정해줄경우 한글깨짐)
             * ',' : 배열을 나눌 문자열
             * '"' : 값을 감싸주기위한 문자
             **/
            
            String path = "Z:\\A1. RPA\\02. g-psi\\psix9"; //폴더 경로

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());
            
        	File file = new File(path);
            //file을 생성할 폴더가 없으면 생성합니다.
            file.mkdirs(); //폴더 생성합니다.

            String fullPath = path + "\\psix9_" + strToday + ".txt";
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		                    new FileOutputStream(fullPath), "euc-kr"))) {
            	StringBuilder strBufOri = new StringBuilder();
            	strBufOri.append("Data type").append("	");
            	strBufOri.append("Order Result Key").append("	");
            	strBufOri.append("Overseas stock site code").append("	");
            	strBufOri.append("Status").append("	");
            	strBufOri.append("Item code").append("	");
            	strBufOri.append("Delivery Date").append("	");
            	strBufOri.append("Actual production quantity").append("	");
            	strBufOri.append("Unit Of measure").append("	");
            	strBufOri.append("Statement Register Date").append("	");
            	strBufOri.append("Extract date");
            	writer.write(strBufOri.toString());
            	
                for(GpsiPsix9VO m : lstResult) {
                    //배열을 이용하여 row를 CSVWriter 객체에 write
                	strBufOri = new StringBuilder();
                	strBufOri.append("\n");
                	strBufOri.append(m.getData_type()).append("	");
                	strBufOri.append(m.getOrderresult_key()).append("	");
                	strBufOri.append(m.getBase_code()).append("	");
                	strBufOri.append(m.getStatus_val()).append("	");
                	strBufOri.append(m.getMatnr()).append("	");
                	strBufOri.append(m.getEindt()).append("	");
                	strBufOri.append(m.getMenge()).append("	");
                	strBufOri.append(m.getLmein()).append("	");
                	strBufOri.append(m.getAedat()).append("	");
                	strBufOri.append(m.getData_dt());
                	
                	writer.write(strBufOri.toString());
                }   
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
