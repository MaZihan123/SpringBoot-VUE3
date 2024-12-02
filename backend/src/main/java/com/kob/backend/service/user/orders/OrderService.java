package com.kob.backend.service.user.orders;

import com.kob.backend.pojo.Orders;

import java.util.List;

public interface OrderService
{
    List<Orders> getOrder(int userid);
}
