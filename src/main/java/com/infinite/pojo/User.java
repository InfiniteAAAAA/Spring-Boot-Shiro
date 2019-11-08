package com.infinite.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -5633278569387528804L;
    private Integer id;
    private String userName;
    private String password;
    private Date createTime;
    private String status;



}
