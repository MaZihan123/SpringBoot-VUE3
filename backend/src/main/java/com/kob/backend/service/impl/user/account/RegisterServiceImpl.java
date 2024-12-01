package com.kob.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService
{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirmPassword, String email, String phone)
    {
        Map<String,String> map=new HashMap<>();
        if (username == null) {
            map.put("error_message", "用户名不能为空1");
            return map;
        }
        if (password == null || confirmPassword == null) {
            System.out.println("this is password:"+password+",this is confirm_password:"+confirmPassword);
            map.put("error_message", "密码不能为空2");
            return map;
        }

        username = username.trim();
        if (username.length() == 0) {
            map.put("error_message", "用户名不能为空3");
            return map;
        }

        if (password.length() == 0 || confirmPassword.length() == 0) {
            map.put("error_message", "密码不能为空4");
            return map;
        }

        if (username.length() > 100) {
            map.put("error_message", "用户名长度不能大于100  5");
            return map;
        }

        if (password.length() > 100 || confirmPassword.length() > 100) {
            map.put("error_message", "密码长度不能大于100   6");
            return map;
        }

        if (!password.equals(confirmPassword)) {
            map.put("error_message", "两次输入的密码不一致   7");
            return map;
        }



        //注册时遇见相同用户名
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<User> users=userMapper.selectList(queryWrapper);
        if(!users.isEmpty())
        {
            map.put("error_message","用户名已经存在");
            return map;
        }

        String encodedPassword=passwordEncoder.encode(password);
        String photo = "https://gw.alicdn.com/imgextra/i1/2216844425045/O1CN01qMdADZ1n8f7WSVCjQ_!!2216844425045.jpg_Q75.jpg_.webp";

        User user=new User(null,phone,username,password,email,photo,null);

        userMapper.insert(user);

        map.put("error_message","success");
        return map;
    }
}
