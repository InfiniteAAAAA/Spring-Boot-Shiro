package com.infinite.pojo;

import lombok.Data;

@Data
public class OpenCustomerReq {
    private String userIdStr;
    private String[] permission;
}
