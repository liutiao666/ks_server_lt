package com.zmkj.controller;

import com.zmkj.dto.AssistPerformanceDto;
import com.zmkj.entity.clickhouse.AssistPerformance;
import com.zmkj.service.AssistPerformanceCkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/clickhouse")
public class ClickHouseController {

    @Autowired
    private AssistPerformanceCkService assistPerformanceCkService;

    @PostMapping("/create")
    public String create(@RequestBody AssistPerformanceDto assistPerformance) {
        try {
            assistPerformanceCkService.addAssistPerformance(assistPerformance);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }


    @GetMapping("/searchAll")
    public List<AssistPerformance> search() {
        return assistPerformanceCkService.searchAll();
    }

    @GetMapping("/insertBatch")
    public String insertBatch(int num) {
        long startTime = System.currentTimeMillis();
        log.info("insert batch startTime:{}", startTime);
        try {
            assistPerformanceCkService.insertBatch(num);
        } catch (Exception e) {
            log.error("has error:{}", e.getMessage(), e);
            return "failed";
        }
        long endTime = System.currentTimeMillis();
        log.info("insert batch endTime:{}", endTime);
        log.info("insert batch cost:{}", (endTime - startTime));
        return "success";
    }
}
