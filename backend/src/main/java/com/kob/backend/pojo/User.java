package com.kob.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

//一个类对应db里的一张表
public class User
{
    //一个属性对应db一行数据
    private String phone_number;
    private String user_name;
    private String password;
    private String email;
}
