package com.woojin.commercial.batchjob.scheduling.GpsiDataVO;

import lombok.Data;

@Data
public class GpsiPsixGVO {
	private String data_type;
	private String po_key;
	private String from_loc_code;
	private String status_val;
	private String item_code;
	private String delivery_dt;
	private long open_po_qty;
	private String base_po_unit;
	private String po_created_dt;
	private String data_dt;
	private String to_loc_code;
}
