package com.infinite.service.impl;

import com.infinite.dao.UserPermissionMapper;
import com.infinite.pojo.Permission;
import com.infinite.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {
    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Override
    public List<Permission> findByUserName(String userName) {
        return userPermissionMapper.findByUserName(userName);
    }
}
