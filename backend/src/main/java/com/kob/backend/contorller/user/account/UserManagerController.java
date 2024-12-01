package com.kob.backend.contorller.user.account;

import com.kob.backend.service.user.account.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserManagerController
{
   @Autowired
    private ManageService manageService;
   @GetMapping("/user/manage/")
    public Map<String,String> manageInfo()
    {
        System.out.println("后端运行UserManagerContronller模块");

        return manageService.manageInfo();
    }
}
