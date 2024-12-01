package com.kob.backend.service.impl.user.merchandise;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.MerchandiseMapper;
import com.kob.backend.pojo.Merchandise;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserLogin;
import com.kob.backend.service.user.merchandise.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class RemoveServiceImpl implements RemoveService
{
    @Autowired
    private MerchandiseMapper merchandiseMapper;//mybatis的接口

    @Override
    public Map<String, String> remove(Map<String, String> data)
    {
        System.out.println("展示前端传来的数据"+data);
        User user= UserLogin.getUser();

        //取出来,名字找id，删除id
        String mername = data.get("mername");

        QueryWrapper<Merchandise> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mername", mername);

        Merchandise merchandise = merchandiseMapper.selectOne(queryWrapper);

        Map<String,String>map=new HashMap<>();
        if(merchandise==null)
        {
            map.put("error_message","商品不存在");
            return map;
        }

        int mer_id=merchandise.getId();

        merchandiseMapper.deleteById(mer_id);
        map.put("success_message","success");
        return map;
    }
}
