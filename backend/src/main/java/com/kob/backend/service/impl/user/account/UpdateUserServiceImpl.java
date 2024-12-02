package com.kob.backend.service.impl.user.account;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserLogin;
import com.kob.backend.service.user.account.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UpdateUserServiceImpl implements UpdateUserService
{
    @Autowired
    UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> updateUser(Map<String, String> data)
    {
        User user1= UserLogin.getUser();

        //取出来,用商品名进行查找
        String username=data.get("username");
        String password=data.get("password");
        String phone=data.get("phone");
        String email=data.get("email");
        String photo=data.get("photo");
        String userlevel=data.get("userlevel");
        System.out.println("username:"+username);

        Map<String,String>map=new HashMap<>();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        User user = userMapper.selectOne(queryWrapper);
        //Merchandise merchandise = merchandiseMapper.selectOne(queryWrapper);
        System.out.println("***show the user which will be updated:"+user);


       //判定*************************8
        if (username == null) {
            map.put("error_message", "用户名不能为空1");
            return map;
        }
        if (password == null ) {
            map.put("error_message", "密码不能为空2");
            return map;
        }

        username = username.trim();
        if (username.length() == 0) {
            map.put("error_message", "用户名不能为空3");
            return map;
        }

        if (password.length() == 0 ) {
            map.put("error_message", "密码不能为空4");
            return map;
        }

        if (username.length() > 100) {
            map.put("error_message", "用户名长度不能大于100  5");
            return map;
        }

        if (password.length() > 100 ) {
            map.put("error_message", "密码长度不能大于100   6");
            return map;
        }

        String encodedPassword=passwordEncoder.encode(password);
        if(photo==null)photo = "https://gw.alicdn.com/imgextra/i1/2216844425045/O1CN01qMdADZ1n8f7WSVCjQ_!!2216844425045.jpg_Q75.jpg_.webp";

        //User newUser=new User(null,phone,username,encodedPassword,email,photo,null,userlevel;
        //判定结束
        //User(Integer id, String phone, String username, String password, String email, String photo, Integer mernum, Integer userlevel)
        User newuser = new User(user.getId(),phone,username, encodedPassword,  email, photo,user.getMernum(),  Integer.parseInt(userlevel));

        userMapper.updateById(newuser);

        map.put("success_message","success");

        return map;
    }
}
