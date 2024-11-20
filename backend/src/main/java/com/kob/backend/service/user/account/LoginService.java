package com.kob.backend.service.user.account;

import java.util.Map;

public interface LoginService
{
    Map<String ,String> getToken(String user_name, String password);
}
