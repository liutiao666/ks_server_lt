package com.zmkj.elasticsearch.dto;

import lombok.Data;

@Data
public class SearchQuery {
    private String field; // 要查询的字段名
    private String value; // 查询值
}
