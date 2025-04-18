/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 공통코드 서비스에 대한 인터페이스 
 * 소  스  명 : CommonCodeVO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.admin.commoncode;

import java.math.BigInteger;
import lombok.Data;
import org.springframework.cache.annotation.Cacheable;

import java.sql.Timestamp;

@Data
@Cacheable(value = "commoncodes")
public class CommonCodeVO {

	/* *******************************************************************************************
	* 데이타 필드
	* ******************************************************************************************* */
	private BigInteger rownum;                    /*순서*/

	private String common_cd;                    /* 공통코드 */

	private String division_cd;                    /* 구분코드 */

	private String division_nm;                    /* 구분명 */

	private String status_cd;                    /* 상태코드 */

	private String status_nm;                    /* 상태명 */

	private int status_seq;                    /* 정렬순서 */

	private String register_id;                    /* 등록자아이디 */

	private String modify_id;                    /* 수정자아이디 */

	private Timestamp modify_dt;                    /* 수정일자 */

}	
