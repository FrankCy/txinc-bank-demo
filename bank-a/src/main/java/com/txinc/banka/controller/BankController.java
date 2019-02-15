package com.txinc.banka.controller;

import com.txinc.banka.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: txinc-bank-demo
 * @package: com.txinc.banka.controller、
 * @email: cy880708@163.com
 * @date: 2019/2/15 下午3:43
 * @mofified By:
 */
@RestController
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("/start")
    public String start(@RequestParam("money") int money) {
        return bankService.start(money);
    }

}
