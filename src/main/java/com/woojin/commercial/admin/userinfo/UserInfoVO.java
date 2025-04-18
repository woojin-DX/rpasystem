/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 사용자정보 서비스에 대한 인터페이스 
 * 소  스  명 : UserInfoVO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.admin.userinfo;

import java.math.BigInteger;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserInfoVO {

	/* *******************************************************************************************
	* 데이타 필드
	* ******************************************************************************************* */
	private BigInteger rownum;                    /*순서*/

	private String user_id;                    /* 사용자아이디 */

	private String auth_cd;                    /* 권한코드 */

	private String sessionkey;                    /* 세션키 */

	private Timestamp sessionlimit;                    /* 세션보존시간 */

	private String del_fl;                    /* 삭제여부 */

	private Timestamp lastjoin_dt;                    /* 최종접속일자 */

	private String auth_nm;                    /* 권한명 */

	private String company_cd;                    /* 업체코드 */

	private String company_nm;                    /* 업체명 */

}
