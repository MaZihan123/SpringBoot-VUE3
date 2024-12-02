package com.kob.backend.contorller.user.account;

import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.user.account.GetUserListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//@GetMapping("/user/account/getuserlist/")
public class GetUserListContronller
{
    @Autowired
    private GetUserListServiceImpl getUserListServiceImpl;

    @GetMapping("/user/account/getuserlist/")
    public List<User> getUserList()
    {
        System.out.println("getuserlist进行......");
        return getUserListServiceImpl.getUserList();
    }
}
