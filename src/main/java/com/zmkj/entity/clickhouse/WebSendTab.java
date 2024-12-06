package com.zmkj.entity.clickhouse;

import lombok.Data;

import java.util.Map;

@Data
public class WebSendTab {
    private long envId;
    private String appName;
    private String appVer;
    private Map<String, String> records;
    private String logDate;
    private Map<String, String> tags;

    @Override
    public String toString() {
        return "WebSendTab{" +
                "envId=" + envId +
                ", appName='" + appName + '\'' +
                ", appVer='" + appVer + '\'' +
                ", records=" + records +
                ", logDate='" + logDate + '\'' +
                ", tags=" + tags +
                '}';
    }
}
