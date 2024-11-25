package com.kob.backend.contorller.user.merchandise;


import com.kob.backend.service.user.merchandise.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddController
{
    @Autowired
    private AddService addService;

    @PostMapping("/user/merchandise/add/")//修改数据库用post
    public Object add(@RequestParam Map<String,String> data)
    {
        return addService.add(data);
    }

}
