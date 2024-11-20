package com.zmkj.controller;

import com.zmkj.dto.AssistPerformanceDto;
import com.zmkj.entity.clickhouse.AssistPerformance;
import com.zmkj.service.AssistPerformanceCkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

}
