/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 출하정보 서비스에 대한 인터페이스 
 * 소  스  명 : ShippingVO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.shipping;

import java.math.BigInteger;

import com.woojin.commercial.admin.commoncode.CommonCodeVO;
import com.woojin.commercial.admin.commoncode.PackingCodeVO;
import com.woojin.commercial.shipping.shippingmtm.ShippingMtmVO;
import com.woojin.commercial.supply.PlaceVO;
import lombok.Data;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Map;

@Data
public class ShippingVO {

	/* *******************************************************************************************
	* 데이타 필드
	* ******************************************************************************************* */
	private String shipping_key;                    /* 업체코드 */
	private String orderfor_key;                    /* 업체코드 */

	private String company_cd;                    /* 업체코드 */
	private String company_nm;                    /* 업체코드 */

	private String accept_dt;                    /* 접수일 */
	private String firstaccept_dt;                    /* 접수일 */
	

	private String material_num;                    /* 품번 */
	private String item_num;                    /* 품번 */

	private int shipping_seq;                    /* 출하순번 */

	private String order_dt;                    /* 발주일자 */

	private String process_cd;                    /* 진행상태 */
	private String processsub_cd;                    /* 상태코드 */
	private String process_nm;                    /* 상태명 */

	private String supply_place;                    /* 납품처 */
	private String place_nm;                    /* 납품처 */
	private String place_addr;                    /* 납품처 */
	private String supply_req_qty;                    /* 납품요청수량 */
	private String supply_req_dt;                    /* 납품요청일 */
	private String confirm_qty;                    /* 확정수량 */
	private String shipping_dt;                    /* 출하예정일자 */
	private double unit_price;                    /* 단가 */
	private int total_price;                    /* 금액 */

	private String packing_cd;                    /* 포장방법 */
	private String packingsub_cd;                    /* 포장방법 */
	private String packing_nm;                    /* 포장방법 */
	private String packing_flag;                    /* 포장여부 */
	private String packing_flag_nm;                    /* 포장여부명 */

	private String shipping_method;                    /* 출하방법 */

	private String supply_qty;                    /* 납품수량 */
	private int mtmreg_qty;                    /* MTM가용재고 */

	private String remain_qty;                    /* 잔여수량 */

	private String supply_dt;                    /* 출하일자 */

	private String receive_dt;                    /* 수령일자 */

	private int bstrf;                    /* 포장단위 */
	private String mtart;                    /* 자재유형 */
	private String mtart_nm;                    /* 자재유형명 */
	private int inven_use_qty;                    /* 가용재고 */
	private int mtm_use_qty;                    /* MTM가용재고 */
	private int prod_order_qty;                    /* 가용수량 */

	private String process_id;                    /* 처리자 */

	private String modify_id;                    /* 수정자아이디 */

	private Timestamp modify_dt;                    /* 수정일자 */

	private String modi_flag;                    /* 수정가능여부 */

	private  String first_use_flag;

	private String trbgcolor;                    /* tr배경색 */

	private int allsupply_qty;                   /*총잔량*/
	private int allconfirm_qty;                   /*동일품번 납품수량*/

	private String pre_storage_loc;                                      /* 저장위치(변경전) */
	private String modi_meterial_num;                                   /* 수정품번 */
	private String modi_qty;                                           /* 수정수량 */
	private String storage_loc ;                                         /* 저장위치(변경후) */

	private int remainallqty;
	private int remaininvenqty;
	private int remainmtmqty;
	private int sum_qty;

	private int verification_qty;

	private Map<String,Object> mtminfo;

	private PlaceVO splace;
	private CommonCodeVO process;
	private PackingCodeVO packing;
}
