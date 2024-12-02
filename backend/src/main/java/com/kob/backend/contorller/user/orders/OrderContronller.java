package com.kob.backend.contorller.user.orders;


import com.kob.backend.mapper.OrderMapper;
import com.kob.backend.pojo.Orders;
import com.kob.backend.service.user.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderContronller
{
    @Autowired
    private OrderService orderService;
    @GetMapping("/myorder/")
    public List<Orders> getOrders(int userid)
    {
        System.out.println("***查询订单,用户名ID："+userid+"***");

        return orderService.getOrder(userid);
    }
}
