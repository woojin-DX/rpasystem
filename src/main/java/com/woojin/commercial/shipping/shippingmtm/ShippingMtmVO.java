/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : mtm정보 서비스에 대한 인터페이스 
 * 소  스  명 : ShippingMtmVO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.shipping.shippingmtm;

import java.math.BigInteger;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ShippingMtmVO {

	/* *******************************************************************************************
	* 데이타 필드
	* ******************************************************************************************* */
	private BigInteger rownum;                    /*순서*/

	private int mtm_seq;                    /* mtm일련번호 */

	private String process_nm;                    /* 업체코드 */

	private String material_num;                    /* 품번 */

	private String pre_storage_loc;                    /* 저장위치(변경전) */

	private String modi_meterial_num;                    /* 수정품번 */

	private int original_qty;                    /* 수량 */
	private int lastmmt_qty;

	private int modi_qty;                    /* 수정수량 */

	private String storage_loc;                    /* 저장위치(변경후) */

	private String process_id;                    /* 처리자아이디 */

	private String modify_id;                    /* 수정자아이디 */

	private Timestamp modify_dt;                    /* 수정일자 */

	private String accept_dt;                    /* 접수일 */

	private String shipping_method;                    /* 출하방법 */

	private int supply_qty;                    /* 납품수량 */

	private String supply_dt;                    /* 남품일자 */
	private String company_cd;                    /* 남품일자 */
	private String company_nm;                    /* 남품일자 */

}	
