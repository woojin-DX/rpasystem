package com.woojin.commercial.batchjob.scheduling;

import lombok.Data;

@Data
public class ExcelDataVO {
    private String company_cd;
    private String mtart;
    private String prdgrp_nm;
    private String material_num;
    private String supply_qty;
    private String supply_place;
    private String supply_dt;
    private String company_nm;
    private String place_nm;
}
