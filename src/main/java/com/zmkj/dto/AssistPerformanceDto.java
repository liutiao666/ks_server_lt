package com.zmkj.dto;

import lombok.Data;

@Data
public class AssistPerformanceDto {
    private int modelId;
    private int taskId;
    private String testNo;
    private int tdId;
    private String eventDay;
    private long eventDaytime;
    private int eventBatch;
    private int eventId;
    private String imageIndex;
}
