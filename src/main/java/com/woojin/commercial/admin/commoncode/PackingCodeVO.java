package com.woojin.commercial.admin.commoncode;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PackingCodeVO {
    /* *******************************************************************************************
     * 데이타 필드
     * ******************************************************************************************* */
    private String common_cd;                    /* 공통코드 */
    private String division_cd;                    /* 구분코드 */
    private String division_nm;                    /* 구분명 */
    private String status_cd;                    /* 상태코드 */
    private String status_nm;                    /* 상태명 */
    private int status_seq;                    /* 정렬순서 */
    private String register_id;                    /* 등록자아이디 */
    private String modify_id;                    /* 수정자아이디 */
    private Timestamp modify_dt;                    /* 수정일자 */
}
