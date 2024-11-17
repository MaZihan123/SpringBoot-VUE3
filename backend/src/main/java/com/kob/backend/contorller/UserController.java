package com.kob.backend.contorller;

import com.kob.backend.pojo.User;
import com.kob.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController
{
    @Autowired
    UserMapper userMapper;//接口userMapper，继承了BaseMapper，mybits-plus提供crud


    @GetMapping("/user/all")
    public List<User> getAllUser()//获取pojo里的对象
    {
        return userMapper.selectList(null);
    }

}
