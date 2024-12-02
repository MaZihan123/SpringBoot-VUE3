package com.kob.backend.service.impl.user.orders;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.MerchandiseMapper;
import com.kob.backend.mapper.OrderMapper;
import com.kob.backend.pojo.Merchandise;
import com.kob.backend.pojo.Orders;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserLogin;
import com.kob.backend.service.user.merchandise.GetListService;
import com.kob.backend.service.user.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<Orders> getOrder(@RequestParam int userid)
    {
        User user= UserLogin.getUser();
//
        QueryWrapper<Orders> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("userid",userid);
//        return merchandiseMapper.selectList(merchandiseQueryWrapper);
        System.out.println("order's list is:"+orderMapper.selectList(queryWrapper));

        return orderMapper.selectList(queryWrapper);
    }
}
