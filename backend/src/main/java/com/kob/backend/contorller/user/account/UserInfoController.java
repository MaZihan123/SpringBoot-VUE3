package com.kob.backend.contorller.user.account;


import com.kob.backend.service.impl.user.account.UserInfoImpl;
import com.kob.backend.service.user.account.GetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//用户个人信息返回给前端
@RestController
public class UserInfoController
{
    @Autowired
    private GetInfoService getInfo;
    @GetMapping("/user/")
    public Map<String,String>getInfo()
    {

        System.out.println("Contronller电话和邮箱***********");
        Map<String, String> phoneAndEmail = getInfo.getPhoneAndEmail();
        System.out.println(phoneAndEmail);
        return phoneAndEmail;


    }
}
