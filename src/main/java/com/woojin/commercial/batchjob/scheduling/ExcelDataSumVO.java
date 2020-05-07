package com.woojin.commercial.batchjob.scheduling;

import lombok.Data;

@Data
public class ExcelDataSumVO {
    private String company_cd;
    private String mtart;
    private String prdgrp_nm;
    private String ordertype;
    private String ordercorp;
    private String orderpath;
    private String ordergroup;
    private int srow;
    private int cnt;
    private String supply_dt;
    private String ppno;
    private String docnum;
    private String supply_num;
}