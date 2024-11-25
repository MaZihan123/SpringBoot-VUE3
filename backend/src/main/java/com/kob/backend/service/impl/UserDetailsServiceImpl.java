package com.kob.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;//
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;//
import org.springframework.security.core.userdetails.UserDetailsService;//
import org.springframework.security.core.userdetails.UsernameNotFoundException;//
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserMapper userMapper;

    @Override
    //传入username，返回username对应的一个信息
    //UserDetails是SpringBoot的一个接口，获取用户信息
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        System.out.println("匹配用户");
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);//查询条件
        //backend.pojo中的user
        User user=userMapper.selectOne(queryWrapper);
        if(user==null)
        {
            throw new RuntimeException("用户不存在");
        }
        System.out.println(user);
        return new UserDetailsImpl(user);
    }
}
