package com.kob.backend.service.user.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.pojo.Merchandise;
import com.kob.backend.pojo.User;

import java.util.List;

public interface GetUserListService
{
    List<User> getUserList();
}
