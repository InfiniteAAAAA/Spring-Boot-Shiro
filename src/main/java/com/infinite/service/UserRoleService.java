package com.infinite.service;

import com.infinite.pojo.Role;

import java.util.List;

public interface UserRoleService {
    List<Role> findByUserName(String userName);
}
