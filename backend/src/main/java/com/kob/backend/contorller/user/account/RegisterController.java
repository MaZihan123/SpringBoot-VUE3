package com.kob.backend.contorller.user.account;


import com.kob.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController//标识服务接口
public class RegisterController
{
    @Autowired
    private RegisterService registerService;
    @PostMapping("/user/account/register/")//网址走这个函数
    public Map<String,String> register(@RequestParam Map<String ,String> map)//@RequestParam是用来接收前端传递给后端的数据
    {
        System.out.println("后端运行RegisterController模块");
        String user_name=map.get("username");
        String password = map.get("password");
        String confirm_password = map.get("confirmPassword");
        String email = map.get("email");
        String phone = map.get("phone");
        System.out.println("注册信息："+map);
        return registerService.register(user_name,password,confirm_password,email,phone);

    }
}
