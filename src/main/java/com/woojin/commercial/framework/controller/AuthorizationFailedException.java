/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : AuthorizationFailedException
 *   @Description   :
 *   @Author        : GACHINOEL
 *   @Version       : v1.0
 *   Copyright(c) 2019 WOOJIN All rights reserved
 *   ------------------------------------------------------------------------------
 *                    변         경         사         항
 *   ------------------------------------------------------------------------------
 *      DATE           AUTHOR                       DESCRIPTION
 *   ---------------  ----------    ------------------------------------------------
 *   2019.10.28       gachinoel     신규생성
 *   ------------------------------------------------------------------------------
 */

package com.woojin.commercial.framework.controller;

public class AuthorizationFailedException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 비 정상적인 접근 오류
     * @param message
     */
    public AuthorizationFailedException(String message) {
        super(message);
    }
}
