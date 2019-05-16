package com.infinite.service;

import com.infinite.pojo.UserOnline;

import java.util.List;

public interface SessionService {
    List<UserOnline> list();
    boolean forceLogout(String sessionId);
}