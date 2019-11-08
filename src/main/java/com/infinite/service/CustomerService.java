package com.infinite.service;

import com.infinite.pojo.CreateCustomerReq;
import com.infinite.pojo.CreateCustomerRes;

public interface CustomerService {
    public CreateCustomerRes createCustomer(CreateCustomerReq createCustomerReq);
}
