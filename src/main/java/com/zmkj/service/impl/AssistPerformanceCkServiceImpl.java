package com.zmkj.service.impl;

import com.zmkj.dto.AssistPerformanceDto;
import com.zmkj.entity.clickhouse.AssistPerformance;
import com.zmkj.mapper.clickhouse.AssistPerformanceDao;
import com.zmkj.service.AssistPerformanceCkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistPerformanceCkServiceImpl implements AssistPerformanceCkService {
    @Autowired
    private AssistPerformanceDao assistPerformanceDao;


    @Override
    public void addAssistPerformance(AssistPerformanceDto assistPerformance) {
        assistPerformanceDao.addOne(assistPerformance);
    }

    @Override
    public List<AssistPerformance> searchAll() {
        return assistPerformanceDao.searchAll();
    }
}
