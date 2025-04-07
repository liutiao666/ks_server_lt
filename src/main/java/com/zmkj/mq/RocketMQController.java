//package com.zmkj.mq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Description:
// * @Author: 94715
// * @CreateDate: 2025/1/22 14:32
// * @Version: 1.0
// */
//@Slf4j
//@RestController
//@RequestMapping("/mq")
//public class RocketMQController {
//
//    @Autowired
//    RocketMQProducer rocketMQProducer;
//
//    @GetMapping("/send")
//    public String sendMsg(String msg) {
//        try {
//            rocketMQProducer.sendMsg("one_test", msg);
//        } catch (Exception e) {
//            log.error("has error:{}",e.getMessage(), e);
//            return "fail";
//        }
//        return "success";
//    }
//}
