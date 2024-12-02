package com.kob.backend.service.impl.user.merchandise;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.MerchandiseMapper;
import com.kob.backend.mapper.OrderMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.Merchandise;
import com.kob.backend.pojo.Orders;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserLogin;
import com.kob.backend.service.user.merchandise.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BuyServiceImpl implements BuyService {
    @Autowired
    private MerchandiseMapper merchandiseMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, String> buy(Map<String, String> data) {
        System.out.println("***购买的ServiceImpl开始进行***");
        System.out.println("展示前端传来的信息：" + data);
        User user = UserLogin.getUser();

        String mername = data.get("mername");
        String quantity = data.get("quantity");
        int quantityInt = Integer.parseInt(quantity);
        System.out.println("要买的商品个数为：" + quantityInt);

        Map<String, String> map = new HashMap<>();

        // 判断购买的数量是否达标
        QueryWrapper<Merchandise> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mername", mername);
        Merchandise merchandise = merchandiseMapper.selectOne(queryWrapper);
        if (merchandise == null) {
            throw new IllegalArgumentException("商品不存在");
        }
        System.out.println("*根据mername查找到的商品为*：" + merchandise);

        if (Integer.parseInt(merchandise.getMernum()) < quantityInt) {
            map.put("error_message", "库存不足");
            return map;
        }
        // 减少库存
        int new_mernum = Integer.parseInt(merchandise.getMernum()) - quantityInt;
        // 更新数据库
        merchandise.setMernum(String.valueOf(new_mernum));
        merchandiseMapper.updateById(merchandise);

        // 创建订单对象并设置属性
        Orders orders1 = new Orders();
        Integer userId=user.getId();
        orders1.setUserid(userId);
        orders1.setMerchandiseid(merchandise.getId());
        orders1.setUsername(user.getUsername());
        orders1.setMername(merchandise.getMername());
        orders1.setDescription(merchandise.getDescription());

        Integer price = Integer.parseInt(merchandise.getMervalue());
        orders1.setMervalue(merchandise.getMervalue());
        orders1.setPhoto(merchandise.getMerimg());

        orders1.setQuantity(quantityInt);
        int sum = price * quantityInt;
        orders1.setTotalprice(sum);

        // 设置创建时间
        orders1.setCreatetime(new Date()); // 确保导入了 java.util.Date
        //user要更新订单数目

        int userNum=user.getMernum();
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();

        queryWrapper1.eq(String.valueOf(userId), user.getId() );
        System.out.println("user的未更新前订单数目为：" + userNum);
        user.setMernum(userNum+1);
        userMapper.updateById(user);

        orderMapper.insert(orders1);

        map.put("error_message", "success");
        return map;
    }
}
