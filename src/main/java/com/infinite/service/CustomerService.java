package com.infinite.service;

import com.infinite.pojo.CreateCustomerReq;
import com.infinite.pojo.CreateCustomerRes;

import java.security.NoSuchAlgorithmException;

public interface CustomerService {
    public CreateCustomerRes createCustomer(CreateCustomerReq createCustomerReq) throws NoSuchAlgorithmException;
}
