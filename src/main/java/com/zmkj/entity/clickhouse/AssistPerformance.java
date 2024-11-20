package com.zmkj.entity.clickhouse;

import lombok.Data;

@Data
public class AssistPerformance {
    private int modelId;
    private int taskId;
    private String testNo;
    private int tdId;
    private String eventDay;
    private String eventDaytime;
    private String eventBatch;
    private int eventId;
    private String imageIndex;
}
