package com.kob.backend.service.impl.user.merchandise;

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
        User user= UserLogin.getUser();

        String mername=data.get("mername");
        String mervalue=data.get("mervalue");
        String description=data.get("description");
        String merimg=data.get("merimg");

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

        Date now=new Date();
        Merchandise merchandise=new Merchandise(null,mername,mervalue,description,now,now,merimg);

        merchandiseMapper.insert(merchandise);
        map.put("success_message","添加成功");

        return map;
    }
}
