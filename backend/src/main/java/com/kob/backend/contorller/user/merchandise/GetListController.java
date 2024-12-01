package com.kob.backend.contorller.user.merchandise;


import com.kob.backend.pojo.Merchandise;
import com.kob.backend.service.user.merchandise.GetListService;
import com.kob.backend.service.user.merchandise.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetListController
{
    @Autowired
    private GetListService getListService;
    @GetMapping("/user/merchandise/getlist/")
    public List<Merchandise> getList()
    {
        System.out.println("getlist进行......");
        return getListService.getList();
    }
}
