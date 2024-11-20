package com.kob.backend.service.impl.user.account;


import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.UserDetailsServiceImpl;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.kob.backend.service.user.account.InfoService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

//根据token来获取一个用户信息
@Service
public class InfoServiceImpl implements InfoService
{
    @Override
    public Map<String ,String> getinfo()
    {
        //getcontext()方法返回一个SecurityContext对象，该对象保存了SecurityContext
        //getAuthentication()方法返回一个Authentication对象，保存了当前用户的信息
        UsernamePasswordAuthenticationToken authenticationToken=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser=(UserDetailsImpl) authenticationToken.getPrincipal();
        User user=loginUser.getUser();
//user 填充info
        Map<String ,String>map=new HashMap<>();
        map.put("error_message","success");
        map.put("id",user.getId().toString());
        map.put("username",user.getUser_name());
        map.put("phone_number",user.getPhone_number());
        map.put("email",user.getEmail());
        return map;
    }
}
