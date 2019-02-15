package com.txinc.banka.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.codingapi.txlcn.tc.annotation.TxTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.txinc.banka.bean.Account;
import com.txinc.banka.dao.AccountDao;
import com.txinc.banka.feign.BankBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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

    @Autowired
    private BankBClient bankBClient;

    @TxcTransaction
    @TccTransaction
    @TxTransaction
    @LcnTransaction
    @Transactional
    public String start(int money) {

        String user = "xiaoming";
        //此处调用Feign
        String status = bankBClient.addMoney(money, user);
        if("success".equals(status)) {
            Account account = new Account();
            account.setMoney(money);
            account.setUser(user);
            //如果有错误，本地回滚，调用的feign无法回滚
            int res = accountDao.update(account);

            throw new RuntimeException("insert money error");

//            return res > 0 ? "success" : "error";
        }

        return "rpc error";
    }

}
