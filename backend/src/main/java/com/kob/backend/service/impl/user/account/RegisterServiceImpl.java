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
    public Map<String, String> register(String user_name, String password, String confirm_password, String email, String phone_number)
    {
        Map<String,String> map=new HashMap<>();
        if(user_name==null)
        {
            map.put("error_message","用户名不能为空");
            return map;
        }
        if(password==null||confirm_password==null)
        {
            map.put("error_message","密码不能为空哦");
            return map;
        }
        if(email==null)
        {
            map.put("error_message","邮箱不能为空");
            return map;
        }

        if(phone_number==null)
        {
            map.put("error_message","电话号不能为空");
            return map;
        }

        //删除首位空白字符
        user_name=user_name.trim();

//        password=password.trim();
//        confirmPassword=confirmPassword.trim();

        if(user_name.length()==0)
        {
            map.put("error_message","用户名不能为空");
            return map;
        }

        if(user_name.length()>15)
        {
            map.put("error_message","用户名过于长");
        }
        if(password.length()>100||confirm_password.length()>100)
        {
            map.put("error_message","密码长度过于长");
        }
        if(password.length()==0||confirm_password.length()==0)
        {
            map.put("error_message","密码长度为零");
        }
        if(password.equals(confirm_password))
        {
            map.put("error_message","两次密码输入不一样");
        }

        //注册时遇见相同用户名
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",user_name);
        List<User> users=userMapper.selectList(queryWrapper);
        if(!users.isEmpty())
        {
            map.put("error_message","用户名已经存在");
            return map;
        }

        String encodedPassword=passwordEncoder.encode(password);
        String photo = "https://gw.alicdn.com/imgextra/i1/2216844425045/O1CN01qMdADZ1n8f7WSVCjQ_!!2216844425045.jpg_Q75.jpg_.webp";

        User user=new User(null,phone_number,user_name,password,email,photo);

        userMapper.insert(user);

        map.put("error_message","success_register!");
        return map;
    }
}
