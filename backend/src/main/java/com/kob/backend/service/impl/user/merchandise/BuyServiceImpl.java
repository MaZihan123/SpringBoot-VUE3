package com.kob.backend.service.impl.user.merchandise;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.MerchandiseMapper;
import com.kob.backend.mapper.OrderMapper;
import com.kob.backend.pojo.Merchandise;
import com.kob.backend.pojo.Order;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserLogin;
import com.kob.backend.service.user.merchandise.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BuyServiceImpl implements BuyService
{
    @Autowired
    private MerchandiseMapper merchandiseMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Map<String,String> buy(Map<String, String> data)
    {
        System.out.println("***购买的ServiceImpl开始进行***");
        System.out.println("展示前端传来的信息："+data);
        User user= UserLogin.getUser();

        String mername=data.get("mername");
        String quantity=data.get("quantity");
        int quantityInt=Integer.parseInt(quantity);
        System.out.println("要买的商品个数为："+quantityInt);

        Map<String,String> map=new HashMap<>();

        //判断购买的数量是否达标
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("mername",mername);
        Merchandise merchandise = merchandiseMapper.selectOne(queryWrapper);if (merchandise == null) {
        throw new IllegalArgumentException("商品不存在");
    }
        System.out.println("*根据mername查找到的商品为*："+merchandise);

        if(Integer.parseInt(merchandise.getMernum())<quantityInt)
        {
            map.put("error_message","库存不足");
            return map;
        }
        //减少库存
        int new_mernum=Integer.parseInt(merchandise.getMernum())-quantityInt;
        //更新数据库
        merchandise.setMernum(String.valueOf(new_mernum));
        merchandiseMapper.updateById(merchandise);

        //更新订单数据库
        QueryWrapper queryWrapper1=new QueryWrapper();
        Order order=new Order();

        order.setUserid(user.getId());
        order.setMerchandiseid(merchandise.getId());
        order.setUsername(user.getUsername());
        order.setMername(merchandise.getMername());

        Integer price=Integer.parseInt(merchandise.getMervalue());
        order.setMervalue(merchandise.getMervalue());

        Integer quantity1=Integer.parseInt(merchandise.getMernum());
        order.setQuantity(Integer.parseInt(merchandise.getMernum()));

        int sum=price*quantity1;

        order.setTotalprice(sum);

        merchandiseMapper.updateById(merchandise);
        orderMapper.insert(order);

        map.put("error_massage","success");
        return map;

    }

}
