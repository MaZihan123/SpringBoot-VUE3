package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order
{
    @TableId(type = IdType.AUTO)//自增
    private Integer id;
    private Integer userid;
    private Integer merchandiseid;
    private String username;
    private String mername;
    private String description;
    private Integer quantity;
    private Integer totalprice;
    private String mervalue;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date createtime;

}
