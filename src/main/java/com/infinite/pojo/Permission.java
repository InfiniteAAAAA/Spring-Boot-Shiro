package com.infinite.pojo;
import lombok.Data;
import java.io.Serializable;

@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 799903874650230699L;
    private Integer id;
    private String url;
    private String name;
}
