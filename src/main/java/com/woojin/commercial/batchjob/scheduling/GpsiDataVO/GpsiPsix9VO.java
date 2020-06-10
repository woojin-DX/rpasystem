package com.woojin.commercial.batchjob.scheduling.GpsiDataVO;

import lombok.Data;

@Data
public class GpsiPsix9VO {
	private String data_type;
	private String orderresult_key;
	private String base_code;
	private String status_val;
	private String matnr;
	private String eindt;
	private long menge;
	private String lmein;
	private String aedat;
	private String data_dt;
}
