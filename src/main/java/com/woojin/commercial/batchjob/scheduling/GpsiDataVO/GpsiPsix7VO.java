package com.woojin.commercial.batchjob.scheduling.GpsiDataVO;

import lombok.Data;

@Data
public class GpsiPsix7VO {
	private String data_type;
	private String mblnr;
	private String base_code;
	private String place_code;
	private String matnr;
	private String budat;
	private long menge;
	private String meins;
	private String ebeln;
	private String ebelp;
	private String lot;
	private String data_dt;
	private String reserve1;
	private String reserve2;
	private String reserve3;
	private String reserve4;
	private String reserve5;
	private String zeile; //20201027 추가 
}
