package com.infinite.dao;

import com.infinite.pojo.MonitorConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MonitorConfigMapper {
    List<MonitorConfig> getMonitorConfig();

    /**
     * 防爬功能总开关 Y=开启,N=关闭
     *
     * @param status Y=开启,N=关闭
     * @return 更新的count
     */
    Integer updateMonitorConfigSwitch(@Param("status") String status);
}
