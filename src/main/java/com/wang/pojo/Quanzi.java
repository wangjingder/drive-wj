package com.wang.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quanzi {
    private Integer id;
    private String text;
    private String imglist;
    private Integer userInfo;
    private String userName;
}
