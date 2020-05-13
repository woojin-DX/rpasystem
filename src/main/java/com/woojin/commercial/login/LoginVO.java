/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : LoginVO
 *   @Description   :
 *   @Author        : GACHINOEL
 *   @Version       : v1.0
 *   Copyright(c) 2019 WOOJIN All rights reserved
 *   ------------------------------------------------------------------------------
 *                    변         경         사         항
 *   ------------------------------------------------------------------------------
 *      DATE           AUTHOR                       DESCRIPTION
 *   ---------------  ----------    ------------------------------------------------
 *   2019.11.14       gachinoel     신규생성
 *   ------------------------------------------------------------------------------
 */

package com.woojin.commercial.login;

import lombok.Data;


@Data
public class  LoginVO {
    private String user_id;
    private String auth_cd;
    private String company_cd;
    private String company_nm;
    private String sessionkey;
    private String sessionlimit;
    private String lastjoin_dt;
}
