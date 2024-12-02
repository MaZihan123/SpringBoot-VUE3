package com.kob.backend.service.impl.user.merchandise;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.MerchandiseMapper;
import com.kob.backend.pojo.Merchandise;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserLogin;
import com.kob.backend.service.user.merchandise.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService
{
    @Autowired
    MerchandiseMapper merchandiseMapper;

    @Override
    public Map<String, String> update(Map<String, String> data)
    {
        User user= UserLogin.getUser();

        //取出来,用商品名进行查找
        String mername=data.get("mername");
        String mervalue=data.get("mervalue");
        String description=data.get("description");
        String merimg=data.get("merimg");
        String mernum=data.get("mernum");

        Map<String,String>map=new HashMap<>();

        QueryWrapper<Merchandise> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mername", mername);

        Merchandise merchandise = merchandiseMapper.selectOne(queryWrapper);
        System.out.println("***show the merchandis which will be updated:"+merchandise);
        //以上是加载前端传来的数据部分，现在开始判断重复、合法
        // 检查商品名是否已存在
//        QueryWrapper<Merchandise> queryWrapper2 = new QueryWrapper<>();
//        queryWrapper.eq("mername", mername); // 根据商品名查询
//        Merchandise existingMerchandise = merchandiseMapper.selectOne(queryWrapper2);
//        System.out.println("***show the existing merchandise:"+existingMerchandise);

//        if (existingMerchandise !=merchandise) {
//            map.put("error_message", "商品名已存在，请更换商品名称");
//            return map;
//        }

        if(mernum==null||mernum.length()==0)
        {
            map.put("error_message","库存不能为空");
        }
        if(mernum=="0")
        {
            map.put("error_message","库存不能为0");
            return map;
        }


        if(merchandise==null)
        {
            map.put("error_message","商品不存在或已被删除");
            return map;
        }

        if(description==null||description.length()==0)
        {
            description="来也匆匆，简介为空～";
        }
        if(description.length()>300)
        {
            map.put("error_message","简介不能超过300字");
        }

        if(mervalue==null||mervalue.length()==0)
        {
            map.put("error_message","价格不能为空");
            return map;
        }
        if(Double.parseDouble(mervalue)<0)
        {
            map.put("error_message","价格不能为负数");
            return map;
        }
        //判断mervalue是否为数字
        try {
            Double.parseDouble(mervalue);
        } catch (NumberFormatException e) {
            map.put("error_message", "价格必须为数字");
            return map;
        }

        Merchandise newMerchandise = new Merchandise(
                merchandise.getId(), mername, mervalue, mernum,description,
                merchandise.getCreatetime(), new Date(),
                merimg);

        merchandiseMapper.updateById(newMerchandise);

        map.put("success_message","success");

        return map;
    }
}
