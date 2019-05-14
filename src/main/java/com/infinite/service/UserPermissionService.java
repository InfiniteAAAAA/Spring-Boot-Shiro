package com.infinite.service;

import com.infinite.pojo.Permission;

import java.util.List;

public interface UserPermissionService {
    List<Permission> findByUserName(String userName);
}
