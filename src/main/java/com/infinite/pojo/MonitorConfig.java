package com.infinite.pojo;

import lombok.Data;

@Data
public class MonitorConfig {
    /**
     * 需要监控的url
     */
    private String url;
    /**
     * 监控的次数 某一ip在time时间内超过此次数则插入记录到数据库
     */
    private Integer count;
    /**
     * 监控的频率
     */
    private Double time;
    /**
     * 监控开关,默认为N
     */
    private String status;
}
