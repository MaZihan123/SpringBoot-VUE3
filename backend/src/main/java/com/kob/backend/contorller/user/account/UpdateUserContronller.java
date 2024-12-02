package com.kob.backend.contorller.user.account;

import com.kob.backend.service.user.account.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateUserContronller
{

    @Autowired
    private UpdateUserService updateUserService;

    @PostMapping("/user/update/")
    Map<String,String> updataUser(@RequestParam Map<String, String>data)
    {
        System.out.println("***执行UpdataUser***");
        System.out.println("获取前端传来的数据"+data);
        return updateUserService.updateUser(data);
    }
}
