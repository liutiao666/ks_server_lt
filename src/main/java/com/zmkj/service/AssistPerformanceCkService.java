package com.zmkj.service;

import com.zmkj.dto.AssistPerformanceDto;
import com.zmkj.entity.clickhouse.AssistPerformance;

import java.util.List;

public interface AssistPerformanceCkService {
    void addAssistPerformance(AssistPerformanceDto assistPerformance);

    List<AssistPerformance> searchAll();
}
