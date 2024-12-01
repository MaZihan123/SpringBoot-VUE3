package com.kob.backend.contorller.user.merchandise;

import com.kob.backend.service.user.merchandise.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RomveController
{
    @Autowired
    private RemoveService removeService;
    @PostMapping("/user/merchandise/remove/")
    public Map<String,String> remove(@RequestParam Map<String,String>data)
    {
        System.out.println("执行删除");
        return removeService.remove(data);
    }

}
