/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 권한정보 서비스에 대한 인터페이스 
 * 소  스  명 : AuthorityVO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.admin.authority;

import java.math.BigInteger;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.Timestamp;

@Data
public class AuthorityVO {

	/* *******************************************************************************************
	* 데이타 필드
	* ******************************************************************************************* */
	private BigInteger rownum;                    /*순서*/

	private String auth_cd;                    /* 권한코드 */

	private String auth_nm;                    /* 권한명 */

}	
