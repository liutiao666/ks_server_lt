//package com.zmkj.mq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
///**
// * @Description:
// * @Author: 94715
// * @CreateDate: 2025/1/22 14:29
// * @Version: 1.0
// */
//@Slf4j
//@Service
//@RocketMQMessageListener(topic = "one_test", consumerGroup = "test-consumer-group")
//public class RocketMQConsumer implements RocketMQListener<String> {
//    @Override
//    public void onMessage(String s) {
//        log.info("received msg:{}, time:{}", s, new Date());
//    }
//}
