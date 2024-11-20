package com.kob.backend.contorller.user.account;


import com.kob.backend.service.impl.user.account.InfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kob.backend.service.user.account.InfoService;

import java.util.Map;

//标识服务接口
@RestController
public class InfoController
{
    @Autowired
    private InfoService infoService;
    @Autowired
    private InfoServiceImpl infoServiceImpl;

//获取信息get，修改添加删除post
    @GetMapping("/user/account/info/")
    public Map<String,String> getInfo(String token)
    {
        return infoServiceImpl.getinfo();
    }
}
