package com.kob.backend.service.impl.user.account;


import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.InfoService;
import com.kob.backend.service.user.account.ManageService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class ManageServiceImpl implements ManageService
{
    @Override
    public Map<String, String> manageInfo()
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
        map.put("username",user.getUsername());
        map.put("phone",user.getPhone());
        map.put("email",user.getEmail());
        map.put("photo",user.getPhoto());
        System.out.println("this is UserManager's map:"+map);
        return map;
    }
}
