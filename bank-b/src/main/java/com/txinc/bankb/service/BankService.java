package com.txinc.bankb.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.txinc.bankb.bean.Account;
import com.txinc.bankb.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: txinc-bank-demo
 * @package: com.txinc.banka.service、
 * @email: cy880708@163.com
 * @date: 2019/2/15 下午3:44
 * @mofified By:
 */
@Component
public class BankService {

    @Autowired
    private AccountDao accountDao;

    @LcnTransaction
    @Transactional
    public String addMoney(int money, String user) {
        Account account = new Account();
        account.setMoney(money);
        account.setUser(user);
        int res = accountDao.update(account);
        return res > 0 ? "success" : "error";
    }

}
