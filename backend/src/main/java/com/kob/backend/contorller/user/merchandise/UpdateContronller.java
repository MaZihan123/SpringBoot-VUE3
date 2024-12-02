package com.kob.backend.contorller.user.merchandise;

import com.kob.backend.service.user.merchandise.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateContronller
{
    @Autowired
    private UpdateService updateService;
    @PostMapping("/user/merchandise/update/")
    Map<String,String> updata(@RequestParam Map<String, String>data)
    {
        System.out.println("***执行UpdataMerchandise***");
        System.out.println("获取前端传来的数据"+data);
        return updateService.update(data);
    }

}
