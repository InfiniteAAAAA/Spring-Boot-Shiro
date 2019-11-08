package com.infinite.controller;

import com.infinite.pojo.MonitorConfig;
import com.infinite.pojo.ResponseBase;
import com.infinite.service.SpiderThresholdValueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SpiderThresholdValueController {
    @Resource
    private SpiderThresholdValueService spiderThresholdValueService;

    @PostMapping("customer/spiderThresholdValue")
    public ResponseBase<List<MonitorConfig>> getSpiderThresholdValue() throws Exception {
        ResponseBase<List<MonitorConfig>> resp = new ResponseBase<>();
        List<MonitorConfig> spiderThresholdValue = spiderThresholdValueService.getSpiderThresholdValue();
        return resp.setSuccessStatusAndData(spiderThresholdValue);
    }

    @PostMapping("customer/spiderThresholdValue/fresh")
    public ResponseBase<List<MonitorConfig>> refreshSpiderThresholdValue() throws Exception {
        ResponseBase<List<MonitorConfig>> resp = new ResponseBase<>();
        List<MonitorConfig> spiderThresholdValue = spiderThresholdValueService.refreshSpiderThresholdValue();
        return resp.setSuccessStatusAndData(spiderThresholdValue);
    }

    @GetMapping("customer/spiderThresholdValue/switch/on")
    public ResponseBase<Boolean> spiderSwitchOn() throws Exception {
        ResponseBase<Boolean> resp = new ResponseBase<>();
        Boolean status = spiderThresholdValueService.spiderSwitchOn();
        spiderThresholdValueService.refreshSpiderThresholdValue();
        return resp.setSuccessStatusAndData(status);
    }

    @GetMapping("customer/spiderThresholdValue/switch/off")
    public ResponseBase<Boolean> spiderSwitchOff() throws Exception {
        ResponseBase<Boolean> resp = new ResponseBase<>();
        Boolean status = spiderThresholdValueService.spiderSwitchOff();
        spiderThresholdValueService.refreshSpiderThresholdValue();
        return resp.setSuccessStatusAndData(status);
    }
}
