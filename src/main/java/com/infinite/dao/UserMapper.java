package com.infinite.dao;

import com.infinite.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUserName(String userName);
}
