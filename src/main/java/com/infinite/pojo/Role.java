package com.infinite.pojo;
import lombok.Data;
import java.io.Serializable;

@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 3387196705660406530L;
    private Integer id;
    private String name;
    private String memo;
}
