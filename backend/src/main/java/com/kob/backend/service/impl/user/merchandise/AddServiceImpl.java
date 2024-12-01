package com.kob.backend.service.impl.user.merchandise;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.MerchandiseMapper;
import com.kob.backend.pojo.Merchandise;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserLogin;
import com.kob.backend.service.user.merchandise.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService
{

    @Autowired
    private MerchandiseMapper merchandiseMapper;

    @Override
    public Map<String, String> add(Map<String, String> data)
    {
        System.out.println("前端传来的新加商品数据为："+data);
        System.out.println("执行AddServiceImpl");
        User user= UserLogin.getUser();

        String mername=data.get("mername");
        String mervalue=data.get("mervalue");
        String description=data.get("description");
        String merimg=data.get("merimg");
        System.out.println("merimg="+merimg);
        if(merimg==null||merimg.length()==0)
        {
            System.out.println("merimg为空");
            merimg="https://img.alicdn.com/imgextra/i2/2200720004236/O1CN01Zz1Z2o1C5Z2Q6Q6Zz_!!2200720004236.jpg";
        }

        Map<String,String>map=new HashMap<>();

        if(description==null||description.length()==0)
        {
            description="来也匆匆，简介为空～";
        }
        if(description.length()>300)
        {
            map.put("error_message","简介不能超过300字");
        }
        if(mername==null||mername.length()==0)
        {
            map.put("error_message","商品名不能为空");
            return map;
        }

        if(mervalue==null||mervalue.length()==0)
        {
            map.put("error_message","价格不能为空");
            return map;
        }

        // 检查商品名是否已存在
        QueryWrapper<Merchandise> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mername", mername); // 根据商品名查询
        Merchandise existingMerchandise = merchandiseMapper.selectOne(queryWrapper);

        if (existingMerchandise != null) {
            map.put("error_message", "商品名已存在，请更换商品名称");
            return map;
        }

        System.out.println("获取数据"+map);

        Date now=new Date();
        Merchandise merchandise=new Merchandise(null,mername,mervalue,description,now,now,merimg);

        merchandiseMapper.insert(merchandise);
        map.put("success_message","success");
        System.out.println("插入成功,展示map"+map);

        return map;
    }
}
