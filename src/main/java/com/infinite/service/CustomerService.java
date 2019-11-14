package com.infinite.service;

import com.infinite.pojo.CreateCustomerReq;
import com.infinite.pojo.CreateCustomerRes;
import com.infinite.pojo.OpenCustomerReq;
import com.infinite.pojo.OpenCustomerRes;


public interface CustomerService {
    public CreateCustomerRes createCustomer(CreateCustomerReq createCustomerReq);

    public OpenCustomerRes openCustomer(OpenCustomerReq openCustomerReq);
}
