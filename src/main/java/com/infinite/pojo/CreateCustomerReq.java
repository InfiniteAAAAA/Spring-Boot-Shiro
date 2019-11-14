package com.infinite.pojo;

import lombok.Data;

@Data
public class CreateCustomerReq {
    private String agentIdStr;
    private String userIdStr;

    public CreateCustomerReq(String userIdStr, String agentIdStr) {
        this.agentIdStr = agentIdStr;
        this.userIdStr = userIdStr;
    }
}
