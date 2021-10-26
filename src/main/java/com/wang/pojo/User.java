package com.wang.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String userName;
    private String password;
    private String name;
    private String phone;
    private Integer type;
    private String remark;

}
