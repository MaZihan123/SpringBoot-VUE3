package com.kob.backend.contorller.user.merchandise;

import com.kob.backend.service.user.merchandise.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BuyController
{
    @Autowired
    private BuyService buyService;

    //购买商品
    @PostMapping("/user/merchandise/buy/")
    public Map<String,String> buy(@RequestParam Map<String, String> data)
    {
        System.out.println("***购买的Contronller开始进行***");

       return buyService.buy(data);
    }

}
