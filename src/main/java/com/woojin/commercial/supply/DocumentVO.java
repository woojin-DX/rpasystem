/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 :  서비스에 대한 인터페이스 
 * 소  스  명 : DocumentVO
 * 작  성  자 : 가치노을      작  성  일 : 2020-04-28
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.supply;

import java.math.BigInteger;
import lombok.Data;

import java.sql.Timestamp;

@Data

public class DocumentVO {

	/* *******************************************************************************************
	* 데이타 필드
	* ******************************************************************************************* */
	private BigInteger rownum;                    /*순서*/

	private String doc_num;                    /* 문서번호 */

	private String company_cd;                    /* 업체코드 */

	private String confirm_dt;                    /* 확정일 */

	private String place_key;                    /* 납품처키 */

	private String publish_dt;                    /* 발행일 */

	private String company_nm;                    /* 업체명 */

	private String place_nm;                    /* 납품처 */

	private BigInteger supply_seq;                    /* 수량 */

	private String file_name;                    /* 파일명 */

	private String file_path;                    /* 파일경로 */

	private Timestamp modify_dt;                    /* 최종수정일 */

}	
