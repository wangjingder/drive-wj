package com.wang.pojo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PinglunQuery {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String text;
}
