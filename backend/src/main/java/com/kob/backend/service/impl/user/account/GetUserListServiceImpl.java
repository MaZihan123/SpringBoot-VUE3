package com.kob.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserLogin;
import com.kob.backend.service.user.account.GetUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetUserListServiceImpl implements GetUserListService
{
    @Autowired
    private UserMapper userMapper;
    @Override

    public List<User> getUserList()
    {
//        User user= UserLogin.getUser();
//
//        QueryWrapper<Merchandise> merchandiseQueryWrapper=new QueryWrapper<>();
//
//        merchandiseQueryWrapper.eq("user_id",user.getId());
//
//        return merchandiseMapper.selectList(merchandiseQueryWrapper);
        System.out.println("***UserList:"+userMapper.selectList(null));
        return userMapper.selectList(null);
    }
}