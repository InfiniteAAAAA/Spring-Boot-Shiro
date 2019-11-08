package com.infinite.service.impl;

import com.infinite.dao.MonitorConfigMapper;
import com.infinite.pojo.MonitorConfig;
import com.infinite.service.RedisService;
import com.infinite.service.SpiderThresholdValueService;
import com.infinite.util.JacksonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SpiderThresholdValueServiceImpl implements SpiderThresholdValueService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpiderThresholdValueServiceImpl.class);
    /**
     * redis中monitorConfig中的key值
     */
    private static final String REDIS_MONITOR_CONFIG_KEY = "b2b:spider:thresholdValue";
    /**
     * redis,爬虫黑名单key值
     */
    private static final String REDIS_BLACK_LIST = "b2b:spider:ipblacklist";
    /**
     * redis,设定日期常量,一天-秒
     */
    private static final long REDIS_MONITOR_CONFIG_KEY_EXPIRE_TIME = 86400L;
    /**
     * 本地缓存
     */
    private static List<MonitorConfig> MONITOR_CONFIG_LOCAL_CACHE = new CopyOnWriteArrayList<>();
    @Resource
    private RedisService redisService;
    @Resource
    private MonitorConfigMapper monitorConfigMapper;

    /**
     * 加载monitorConfig表的数据
     *
     * @return 返回是否成功初始化加载
     */
    @Override
    @Async
    public void loadSpiderThresholdValue() {
        // 先查询缓存是否有数据,如果有,则更新数据后
        boolean inRedis = false;
        boolean inLocalCache = false;

        String monitorConfigInRedis = null;
        try {
            monitorConfigInRedis = (String) redisService.get(REDIS_MONITOR_CONFIG_KEY);
            inRedis = true;
        } catch (Exception e) {
            LOGGER.warn("Redis读取失败,读取数据库存入到本地缓存中");
        }
        inLocalCache = MONITOR_CONFIG_LOCAL_CACHE.size() > 0;

        if (inRedis) {
            boolean isUpdateRedisMonitorConfig = updateRedisMonitorConfigData(monitorConfigInRedis);
            if (!isUpdateRedisMonitorConfig) {
                if (inLocalCache) {
                    updateLocalCache();
                }
            }
        } else {
            updateLocalCache();
        }

    }

    private List<MonitorConfig> updateLocalCache() {
        List<MonitorConfig> monitorConfigList;
        try {
            monitorConfigList = monitorConfigMapper.getMonitorConfig();
        } catch (Exception e) {
            LOGGER.error("读取monitorConfig表查询数据库失败", e);
            // 原来就有本地缓存的情况
            if (MONITOR_CONFIG_LOCAL_CACHE.size() > 0) {
                return MONITOR_CONFIG_LOCAL_CACHE;
            } else {
                return null;
            }
        }
        String s = "";
        try {
            s = JacksonUtils.obj2json(monitorConfigList);
        } catch (Exception e) {
            LOGGER.error("序列化失败", e);
        }
        if (StringUtils.isNotBlank(s)) {
            updateRedisMonitorConfigData(s);
        }

        MONITOR_CONFIG_LOCAL_CACHE.clear();
        MONITOR_CONFIG_LOCAL_CACHE.addAll(Optional.of(monitorConfigList).get());
        return monitorConfigList;
    }

    /**
     * 更新redis缓存
     *
     * @param monitorConfigInRedis json字符串数据
     * @return set是否成功
     */
    private boolean updateRedisMonitorConfigData(String monitorConfigInRedis) {
        return redisService.set(REDIS_MONITOR_CONFIG_KEY, monitorConfigInRedis, REDIS_MONITOR_CONFIG_KEY_EXPIRE_TIME);
    }

    /**
     * 获取MonitorConfig数据
     *
     * @return redis缓存或本地缓存数据
     */
    @Override
    public List<MonitorConfig> getSpiderThresholdValue() throws Exception {
        String monitorConfigJson = (String) redisService.get(REDIS_MONITOR_CONFIG_KEY);

        if (StringUtils.isNotBlank(monitorConfigJson)) {
            return JacksonUtils.json2list(monitorConfigJson, MonitorConfig.class);
        }

        if (MONITOR_CONFIG_LOCAL_CACHE.size() > 0) {
            return MONITOR_CONFIG_LOCAL_CACHE;
        }

        return updateLocalCache();
    }

    /**
     * 刷新缓存
     *
     * @return 刷新后的数据
     * @throws Exception 查询数据库/redis的异常
     */
    @Override
    public List<MonitorConfig> refreshSpiderThresholdValue() throws Exception {
        List<MonitorConfig> monitorConfig = monitorConfigMapper.getMonitorConfig();
        updateRedisMonitorConfigData(JacksonUtils.obj2json(monitorConfig));
        updateLocalCache();
        return monitorConfig;
    }

    @Override
    public Boolean spiderSwitchOn() {
        return updateMonitorConfigStatus("Y");

    }

    @Override
    public Boolean spiderSwitchOff() {
        return updateMonitorConfigStatus("N");
    }

    private Boolean updateMonitorConfigStatus(String status) {
        Integer count = monitorConfigMapper.updateMonitorConfigSwitch(status);
        return count >= 1;
    }
}
