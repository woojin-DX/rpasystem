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

public @Data class webVO {

	/* *******************************************************************************************
	* 데이타 필드
	* ******************************************************************************************* */

	private String MANDT;				/*클라이언트*/
	private String EBELN;				/*구매오더번호*/
	private String EBELP;				/*구매오더품목*/
	private String EKGRP;				/*구매그룹*/
	private String LIFNR;				/*공급업체코드*/
	private String NAME1;				/*공급업체명*/
	private String BEDAT;				/*증빙일*/
	private String MATNR;				/*자재코드*/
	private String TXZ01;				/*자재내역*/
	private String MFRPN;				/*제조자부품번호*/
	private String LGORT;				/*저장위치*/
	private String LGOBE;				/*저장위치내역*/
	private String MENGE;				/*수량*/
	private String MEINS;				/*단위*/
	private String EINDT;				/*납품요청일*/
	private String NAPTM;				/*납품요청시간*/
	private String NETPR;				/*단가*/
	private String WAERS;				/*통화단위*/
	private String PEINH;				/*가격단위*/
	private String BPRME;				/*오더가격단위*/
	private String TXZ02;				/*품목텍스트*/
	private String AUFNR;				/*생산오더번호*/
	private String LOEKZ;				/*구매문서삭제지시자*/
	private String STATU;				/*납품상태지지사*/
	private String SUPP_JUPDT;			/*공급업체접수일자*/
	private String SUPP_JUPTM;			/*공급업체접수시간*/
	private String EOLIKZ;				/*우진납품완료지시자*/
	private String SUPP_JUP_MODDT;		/*공급업체 접수변경일자*/
	private String SUPP_JUP_MODTM;		/*공급업체 접수변경시간*/
	private String F_IN_DT;				/*최초생성일자*/
	private String F_IN_TM;				/*최초생성시간*/
	private String F_IN_UNAME;			/*최초생성자*/
	private String E_MOD_DT;			/*최종변경일자*/
	private String E_MOD_TM;			/*최종변경시간*/
	private String E_MOD_UNAME;			/*최총변경사용자*/
	private String SUPP_F_IN_UNAME;		/*공급업체최종입력사용자*/
	private String SUPP_E_MOD_UNAME;	/*공급업체최종변경인*/
	private String SUPP_E_MOD_ID;		/*공급업체최종변경ID*/
	private String SUPP_NAP_END;		/*공급업체납품완료지시자*/
	private String END_MENGE;			/*생산완료수량*/
	private String END_MENGE_MODDT;		/*생산량최종변경일*/
	private String END_MENGE_MODTM;		/*생산량최종변경시간*/
	private String FNAPDT;				/*최초납품예정일*/
	private String ENAPDT;				/*최종납품예정일*/
	private String DEL;					/*Web발주구매오더 삭제지시자*/
	private String MSG;					/*최종메세지라인*/
	private String MENGE_TOT;			/*납품문서생성수량 합계*/
	private String ibgo_menge;			/*우진입고완료수량*/

	
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
