package com.woojin.commercial.common;

import lombok.Data;

@Data
public class SearchVO {
    private int nCurrpage;  //현재페이지
    private String pagemode;

    private String schword;
    private String coperation_cd;
    private String material_list;
    private String order_dt_start;                    /* 발주일자 */
    private String order_dt_end;                    /* 발주일자 */
    private String accept_dt_start;                    /* 발주일자 */
    private String accept_dt_end;                    /* 발주일자 */
    private String knumh;                      /* CondReqNo */
    private String common_cd;                    /* 상태 */
}
