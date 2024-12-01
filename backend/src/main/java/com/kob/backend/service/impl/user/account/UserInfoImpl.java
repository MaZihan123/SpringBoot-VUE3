package com.kob.backend.service.impl.user.account;

import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.impl.utils.UserLogin;
import com.kob.backend.service.user.account.GetInfoService;
import com.kob.backend.service.user.account.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoImpl implements GetInfoService
{
    @Override
    public Map<String ,String> getPhoneAndEmail()
    {
        System.out.println("走到接口获取中");
        User user=UserLogin.getUser();
//user 填充info
        System.out.println("用户填充完毕");
        Map<String ,String>map=new HashMap<>();
        map.put("error_message","success");
        map.put("phone",user.getPhone());
        map.put("email",user.getEmail());
        map.put("mernum", String.valueOf(user.getMernum()));
        map.put("photo",user.getPhoto());
        return map;
    }
}
