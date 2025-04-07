package com.zmkj.entity.clickhouse;

import lombok.Data;

import java.util.Map;

@Data
public class AssistPerformance {
    private int modelId;
    private int taskId;
    private String testNo;
    private int tdId;
    private String eventDay;
    private long eventDaytime;
    private int eventBatch;
    private int eventId;
    private String imageIndex;
    private Map<String, String> extralMap;
}
