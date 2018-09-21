package com.hcyq.jblog.user.dao;

import com.hcyq.jblog.user.bean.UserInfo;

public interface UserInfoMapper {
    UserInfo getUserById(UserInfo info);

    UserInfo getUserByEmail(String email);

    UserInfo getUserByUserName(String name);

    int addUser(UserInfo info);
}
