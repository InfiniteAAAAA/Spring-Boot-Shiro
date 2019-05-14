package com.infinite.service.impl;

import com.infinite.dao.UserRoleMapper;
import com.infinite.pojo.Role;
import com.infinite.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findByUserName(String userName) {
        return userRoleMapper.findByUserName(userName);
    }
}
