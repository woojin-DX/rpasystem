/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 발주정보 서비스에 대한 인터페이스 
 * 소  스  명 : SupplyVO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.supply;

import com.woojin.commercial.admin.commoncode.CommonCodeVO;
import com.woojin.commercial.shipping.ShippingVO;
import lombok.Data;

import java.sql.Timestamp;

public @Data class SupplyVO {

	/* *******************************************************************************************
	* 데이타 필드
	* ******************************************************************************************* */
	private String orderfor_key;
	private String shipping_key;

	private String company_cd;                    /* 업체코드 */
	private String company_nm;                    /* 업체코드 */

	private String order_dt;                    /* 발주일자 */

	private String material_num;                    /* 품번 */

	private String knumh;                      /* CondReqNo */

	private String user_id;                    /* 사용자아이디 */

	private String process_cd;                    /* 상태 */

	private String supply_place;                    /* 납품처 */
	private String place_key;                    /* 납품처 */
	private String place_nm;                    /* 납품처 */

	private int supply_req_qty;                    /* 납품요청수량 */

	private String supply_req_dt;                    /* 납품요청일 */

	private int confirm_qty;                    /* 확정수량 */

	private String shipping_dt;                    /* 출하예정일자 */

	private double unit_price;                    /* 단가 */

	private int total_price;                    /* 금액 */
	private int tax_price;

	private String last_receive_dt;                    /* 최종수령일 */

	private String use_fl;                    /* 취소여부 */

	private String modify_id;                    /* 수정자아이디 */

	private Timestamp modify_dt;                    /* 수정일자 */

	private String processsub_cd;                    /* 상태코드 */

	private String process_nm;                    /* 상태명 */

	private int shipping_seq;                    /* 출하순번 */

	private int supply_qty;                    /* 납품수량 */

	private String supply_dt;                    /* 남품일자 */

	private String receive_dt;                    /* 수령일자 */

	private int remain_qty;                     /*잔량*/

	private String trbgcolor;                    /* tr배경색 */

	private int rownum;
	private String company_addr;
	private String regster_no;
	private String j_1kfrepre;
	private String j_uptae;
	private String j_jongmok;

	private	PlaceVO splace;
	private CommonCodeVO process;
	private ShippingVO shpping;
	
	private long supply_price;
}
