package com.zmkj.controller;

import com.zmkj.service.impl.KafkaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {

    @Autowired
    private KafkaServiceImpl kafkaService;

    @GetMapping("/send")
    public String sendMsg(@RequestParam("topic") String topic, @RequestParam("num") int num) {
        log.info("插入到topic：{}，数据量：{}", topic, num);
        try {
            Instant start = Instant.now();
            kafkaService.sendMsg(topic, num);
            Instant end = Instant.now();
            long useTime = Duration.between(start, end).toMillis();
            log.info("插入到topic：{}，总共：{}条，耗时：{}", topic, num, useTime);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    @GetMapping("/topicNum")
    public String topicNums(@RequestParam("topic") String topic) {
        long topicNums = 0;
        try{
            topicNums = kafkaService.getTopicNums(topic);
        } catch (Exception e) {
            log.error("has error:{}", e.getMessage(), e);
        }
        return String.valueOf(topicNums);
    }
}
