package com.wang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pinglun {

    private Integer id;
    private String text;
    private String userName;
    private Integer userId;
}
