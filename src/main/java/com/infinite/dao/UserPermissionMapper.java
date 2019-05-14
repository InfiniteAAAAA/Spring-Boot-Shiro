package com.infinite.dao;

import com.infinite.pojo.Permission;

import java.util.List;

public interface UserPermissionMapper {
    List<Permission> findByUserName(String userName);
}