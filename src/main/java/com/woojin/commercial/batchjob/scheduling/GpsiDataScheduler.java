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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
	@Scheduled(cron = "0 14 13 * * ?")
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

            String fullPath = path + "\\psix0_" + strToday + ".txt";
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fullPath), "euc-kr"))) {
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
	
}
