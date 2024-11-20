package com.kob.backend.contorller.user.account;


import com.kob.backend.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
        String username=map.get("user_name");
        String password = map.get("password");
        return loginService.getToken(username,password);
    }
}
