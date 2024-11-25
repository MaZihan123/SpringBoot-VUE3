package com.kob.backend.contorller.user.account;


import com.kob.backend.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
public class LoginController
{
    //Spring自动注入，mapper、service、controller、config，方法被@bean注解的方法的返回值对象会被Spring IOC容器自动管理
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/account/token/")//网址走这个函数
    public Map<String,String >getToken(@RequestParam Map<String ,String >map)
    {

        System.out.println("后端运行LoginController模块,然后输出map");
        System.out.println(map);
        String username=map.get("username");
        String password = map.get("password");

        System.out.println(map);
        return loginService.getToken(username,password);
    }
}
