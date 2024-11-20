package com.kob.backend.service.user.account;

import java.util.Map;

public interface RegisterService
{
    Map<String ,String > register(String user_name, String password, String confirmPassword, String email, String phone_number);

}
