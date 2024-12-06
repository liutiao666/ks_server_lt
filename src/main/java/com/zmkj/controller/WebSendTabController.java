package com.zmkj.controller;

import com.zmkj.entity.clickhouse.WebSendTab;
import com.zmkj.service.WebSendTabService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ck/webSendTab")
public class WebSendTabController {

    @Autowired
    private WebSendTabService webSendTabService;

    @PostMapping("/addOne")
    public String addOne(@RequestBody WebSendTab webSendTab) {
        try {
            webSendTabService.addOne(webSendTab);
            return "add successed";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("has error:{}", e.getMessage(), e);
            return "add failed";
        }
    }

    @GetMapping("/searchBy")
    public List<WebSendTab> searchBy(@RequestParam("recordKey") String recordKey, @RequestParam("tagKey") String tagKey) {
        return webSendTabService.serachAll(recordKey, tagKey);
    }

}
