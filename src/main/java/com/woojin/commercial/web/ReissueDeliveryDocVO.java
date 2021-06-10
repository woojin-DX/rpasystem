/**
 * Copyright (c) 2020
 * *******************************************************************************************
 * 단위업무명 : 발주정보 서비스에 대한 인터페이스 
 * 소  스  명 : webVO
 * 작  성  자 : 가치노을      작  성  일 : 2020-03-26
 * 수  정  자 :             수  정  일 :
 * 내      용 :
 * 주의  사항 :
 * *******************************************************************************************
 *
*/


package com.woojin.commercial.web;

import java.sql.Timestamp;

import com.woojin.commercial.admin.commoncode.CommonCodeVO;
import com.woojin.commercial.shipping.ShippingVO;
import com.woojin.commercial.supply.PlaceVO;

import lombok.Data;

public @Data class ReissueDeliveryDocVO {

	/* *******************************************************************************************
	* 데이타 필드
	* ******************************************************************************************* */

	private String MANDT;		/*클라이언트*/
	private String EBELN;		/*구매 문서 번호*/
	private String EBELP;		/*구매 문서 품목 번호*/
	private String EKGRP;		/*구매그룹*/
	private String DELVN;		/*납품문서번호*/
	private String DELVS;		/*납품문서품목*/
	private String MATNR;		/*자재코드*/
	private String TXZ01;		/*자재내역*/
	private String LIFNR;		/*공급업체*/
	private String MENGE;		/*납품문서수량*/
	private String ebeln_menge;	/*구매오더수량*/
	private String ibgo_menge;	/*우진입고완료수량*/
	private String menge_tot;	/*납품문서생성수량 합계*/
	private String MEINS;		/*단위*/
	private String NAP_PRE_DT;	/*납품예정일*/
	private String NAP_PRE_TM;	/*납품예정시간*/
	private String DELVN_DT;	/*납품문서번호생성일*/
	private String DELVN_TM;	/*납품문서번호생성시간*/
	private String DEL;			/*삭제지시자*/
	private String DEL_DT;		/*삭제일자*/
	private String DEL_TM;		/*삭제시간*/
	private String UNAME;		/*생성인*/
	private String FIN_ID;		/*생성인ID*/
	private String E_MOD_UNAME;	/*최종수정인*/
	private String E_MOD_ID;	/*죄종수정아이디*/
	private String E_MOD_DT;	/*최종수정일자*/
	private String E_MOD_TM;	/*최종수정시간*/
	private String TEMP1;		/*제조자부품번호*/
	private String EINDT;		/*납품요청일*/
	
	
	private String  LFA_STCD2; 			/*등록번호*/
	private String  LFA_NAME1; 			/*상      호*/
	private String  LFA_J_1KFREPRE; 	/*대표자*/
	private String  LFA_MCOD3; 			/*주    소1*/
	private String  LFA_STRAS; 			/*주    소2*/
	private String  LFA_J_1KFTBUS; 		/*업태*/
	private String  LFA_J_1KFTIND;		/*종목*/
	
	private String NETPR;		/* 단가 */
	private String PEINH;		/* 가격단위 */
	private String BPRME;		/* 단위 */
	private String PRICE;		/*금액 */
	private String NAME1; 		/*업체 명*/
	 

	private String orderfor_key;
	private String shipping_key;
	private String company_cd;                    /* 업체코드 */
	private String company_nm;                    /* 업체코드 */
	private String order_dt;                    /* 발주일자 */
	private String material_num;                    /* 품번 */
	private String knumh;                      /* CondReqNo */
	private String user_id;                    /* 사용자아이디 */
	private String process_cd;                    /* 상태 */
	private String web_place;                    /* 납품처 */
	private String place_key;                    /* 납품처 */
	private String place_nm;                    /* 납품처 */
	private int web_req_qty;                    /* 납품요청수량 */
	private String web_req_dt;                    /* 납품요청일 */
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
	private int web_qty;                    /* 납품수량 */
	private String web_dt;                    /* 남품일자 */
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
	
	private long web_price;
}
