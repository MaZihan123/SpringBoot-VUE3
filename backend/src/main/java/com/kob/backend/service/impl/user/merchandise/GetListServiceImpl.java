package com.kob.backend.service.impl.user.merchandise;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.MerchandiseMapper;
import com.kob.backend.pojo.Merchandise;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserLogin;
import com.kob.backend.service.user.merchandise.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetListServiceImpl implements GetListService
{
    @Autowired
    private MerchandiseMapper merchandiseMapper;
    @Override
    public List<Merchandise> getList()
    {
//        User user= UserLogin.getUser();
//
//        QueryWrapper<Merchandise> merchandiseQueryWrapper=new QueryWrapper<>();
//
//        merchandiseQueryWrapper.eq("user_id",user.getId());
//
//        return merchandiseMapper.selectList(merchandiseQueryWrapper);
        System.out.println("this is list:"+merchandiseMapper.selectList(null));
        return merchandiseMapper.selectList(null);
    }
}
