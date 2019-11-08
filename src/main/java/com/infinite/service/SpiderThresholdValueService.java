package com.infinite.service;

import com.infinite.pojo.MonitorConfig;

import java.util.List;

public interface SpiderThresholdValueService {
    void loadSpiderThresholdValue();

    /**
     * 获取MonitorConfig数据
     *
     * @return redis缓存或本地缓存数据
     */
    List<MonitorConfig> getSpiderThresholdValue() throws Exception;

    List<MonitorConfig> refreshSpiderThresholdValue() throws Exception;

    Boolean spiderSwitchOn();

    Boolean spiderSwitchOff();
}
