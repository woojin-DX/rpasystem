package com.woojin.commercial.batchjob.scheduling;

import lombok.Data;

@Data
public class ElectricStaticVO {
	private String ACTUAL_OUTPUT_DT;
	private String PROC_CD;
	private String WC_GRP_CD;
	private String WC_CD;		
	private String MACH_CD;		
	private String MACH_NM;		
	private Long GOOD_QTY;
	private Long DEFECT_QTY;
	private Long SUM_QTY;
	private double WORK_TM;
	private double MACH_ON_TM;
	private double MACH_LOAD_TM;
	private double MACH_REST;
	private double MACH_RUN_TM;
	private double MACH_VALIDOPERATION_TM;
	private double MACH_READY_TM;
	private double MACH_IDLE_CNT;
	private double MACH_IDLE_TM;

}
