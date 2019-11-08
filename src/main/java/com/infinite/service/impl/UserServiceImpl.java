package com.infinite.service.impl;

import com.infinite.dao.UserMapper;
import com.infinite.pojo.User;
import com.infinite.service.SpiderThresholdValueService;
import com.infinite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SpiderThresholdValueService spiderThresholdValueService;

    @Override
    public User findByUserName(String userName) {
        spiderThresholdValueService.loadSpiderThresholdValue();
        return userMapper.findByUserName(userName);
    }
}
