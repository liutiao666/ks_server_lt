//package com.zmkj.mq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @Description:
// * @Author: 94715
// * @CreateDate: 2025/1/22 14:20
// * @Version: 1.0
// */
//@Slf4j
//@Service
//public class RocketMQProducer {
//
//    @Autowired
//    RocketMQTemplate rocketMQTemplate;
//
//    public void sendMsg(String topic, String msg) {
//        rocketMQTemplate.convertAndSend(topic, msg);
//        log.info("send topic:{}, msg:{}", topic, msg);
//    }
//}
