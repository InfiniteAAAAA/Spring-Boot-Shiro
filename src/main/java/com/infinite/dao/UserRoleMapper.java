package com.infinite.dao;

import com.infinite.pojo.Role;

import java.util.List;

public interface UserRoleMapper {
    List<Role> findByUserName(String userName);
}
