package com.kob.backend.contorller.user.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kob.backend.service.user.account.InfoService;

import javax.sound.sampled.Line;
import java.util.Map;

//标识服务接口
@RestController
public class InfoServiceImpl
{
    @Autowired
    private Line.InfoService infoService;

    @GetMapping
    public Map<String,String> getInfo(String token)
    {
        return infoService.getInfo(token);
    }
}
