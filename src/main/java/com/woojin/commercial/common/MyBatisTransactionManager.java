/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : MyBatisTransactionManager
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

package com.woojin.commercial.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
@Scope("prototype")
public class MyBatisTransactionManager extends DefaultTransactionDefinition {
    private static final long serialVersionUID = -1375151959664915520L;

    @Autowired
    PlatformTransactionManager transactionManager;

    TransactionStatus status;

    public void start() throws TransactionException {
        status = transactionManager.getTransaction(this);
    }

    public void commit() throws TransactionException {
        if (!status.isCompleted()) {
            transactionManager.commit(status);
        }
    }

    public void rollback() throws TransactionException {
        if (!status.isCompleted()) {
            transactionManager.rollback(status);
        }
    }

    public void end() throws TransactionException {
        rollback();
    }
}
