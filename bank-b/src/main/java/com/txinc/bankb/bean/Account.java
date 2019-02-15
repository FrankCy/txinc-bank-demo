package com.txinc.bankb.bean;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: txinc-bank-demo
 * @package: com.txinc.banka.bean、
 * @email: cy880708@163.com
 * @date: 2019/2/15 下午3:46
 * @mofified By:
 */
public class Account {

    private Integer id;

    private Integer money;

    private String user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
