package com.infinite.controller;

import com.infinite.pojo.CreateCustomerReq;
import com.infinite.pojo.OpenCustomerReq;
import com.infinite.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("indexToCreate")
    public String indexToCreate(Model model) {
        return "/module/customer/createCustomer";
    }
    @GetMapping("indexToOpen")
    public String indexToOpen(Model model) {
        return "/module/customer/openCustomer";
    }

    @PostMapping("create")
    @ResponseBody
    public Map<String,Object> createCustomer(CreateCustomerReq createCustomerReq) {
        customerService.createCustomer(createCustomerReq);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("msg","成功");
        return result;
    }

    @PostMapping("open")
    @ResponseBody
    public Map<String,Object> openCustomer(OpenCustomerReq openCustomerReq) {
        customerService.openCustomer(openCustomerReq);
        System.out.println(openCustomerReq);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("msg","成功");
        return result;
    }
}
