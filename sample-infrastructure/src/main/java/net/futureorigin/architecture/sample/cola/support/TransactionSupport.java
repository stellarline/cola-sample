package net.futureorigin.architecture.sample.cola.support;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * TransactionSupport
 * <p></p>
 *
 * @author Leander Lee on 2021/9/3.
 */
public class TransactionSupport {

    /**
     * 手动事务回滚
     */
    public static void setRollback() {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
