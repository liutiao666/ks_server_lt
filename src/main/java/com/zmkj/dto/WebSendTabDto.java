package com.zmkj.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class WebSendTabDto {
    private long envId;
    private String appName;
    private String appVer;
    private Map<String, String> records = new HashMap<>();
    private String logDate;
    private Map<String, String> tags = new HashMap<>();
}
