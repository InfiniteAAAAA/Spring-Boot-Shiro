package com.infinite.dao;

import com.infinite.pojo.User;

public interface UserMapper {
    User findByUserName(String userName);
}
