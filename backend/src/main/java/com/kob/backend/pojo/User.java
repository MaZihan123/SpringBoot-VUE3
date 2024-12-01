package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
//一个类对应db里的一张表
public class User
{
    //一个属性对应db一行数据
    @TableId(type = IdType.AUTO)//自增
    private Integer id;
    private String phone;
    private String username;
    private String password;
    private String email;
    private String photo;
    private Integer mernum;
}
