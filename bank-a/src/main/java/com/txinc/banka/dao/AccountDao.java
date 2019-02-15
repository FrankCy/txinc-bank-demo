package com.txinc.banka.dao;

import com.txinc.banka.bean.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: txinc-bank-demo
 * @package: com.txinc.banka.dao、
 * @email: cy880708@163.com
 * @date: 2019/2/15 下午3:49
 * @mofified By:
 */
@Mapper
public interface AccountDao {

    @Update("update t_bank set money = money - #{money} where user = '${user}' ")
    int update(Account account);

}
