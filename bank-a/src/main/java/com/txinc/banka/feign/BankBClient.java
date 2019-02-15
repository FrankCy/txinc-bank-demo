package com.txinc.banka.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: txinc-bank-demo
 * @package: com.txinc.banka.feign、
 * @email: cy880708@163.com
 * @date: 2019/2/15 下午3:51
 * @mofified By:
 */
@FeignClient(value = "bank-b")
public interface BankBClient {

    @GetMapping("/add-money")
    String addMoney(@RequestParam("money") int money, @RequestParam("user") String user);

}
