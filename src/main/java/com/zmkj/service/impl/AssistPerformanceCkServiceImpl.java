package com.zmkj.service.impl;

import com.zmkj.dto.AssistPerformanceDto;
import com.zmkj.entity.clickhouse.AssistPerformance;
import com.zmkj.mapper.clickhouse.AssistPerformanceDao;
import com.zmkj.service.AssistPerformanceCkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.Executor;

@Slf4j
@Service
public class AssistPerformanceCkServiceImpl implements AssistPerformanceCkService {
    @Autowired
    private AssistPerformanceDao assistPerformanceDao;

    @Autowired
    Executor asyncClickHouseExecutor;


    @Override
    public void addAssistPerformance(AssistPerformanceDto assistPerformance) {
        assistPerformanceDao.addOne(assistPerformance);
    }

    @Override
    public List<AssistPerformance> searchAll() {
        return assistPerformanceDao.searchAll();
    }

    @Override
    public void insertBatch(int num) {
        int batchSize = 3000;
        for (int i = 0; i < num; i+=batchSize) {
            int end = Math.min(i+batchSize, num);
            log.info("batchSize:{},end:{}", batchSize, end);
            List<AssistPerformance> list = new ArrayList<>();
            for (int j = end - batchSize; j < end; j++) {
                AssistPerformance assistPerformance = new AssistPerformance();
                assistPerformance.setModelId(j);
                assistPerformance.setTaskId(j);
                assistPerformance.setTestNo("TestNo_" + j);
                assistPerformance.setTdId(j);
                assistPerformance.setEventDay(DateUtils.formatDate(new Date()));
                assistPerformance.setEventDaytime(System.currentTimeMillis());
                assistPerformance.setEventBatch(j);
                assistPerformance.setEventId(j);
                assistPerformance.setImageIndex("imageIndex_" + j);
                Map<String, String> map = new HashMap<>();
                for (int k = 0; k < 3; j++) {
                    map.put("extralMap_" + j + "_" + k, k + "");
                }
                assistPerformance.setExtralMap(map);
                list.add(assistPerformance);
            }
            asyncClickHouseExecutor.execute(()->{
                assistPerformanceDao.batchInsertCK(list);
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
