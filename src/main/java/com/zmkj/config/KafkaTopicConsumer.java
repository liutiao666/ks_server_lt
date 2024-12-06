package com.zmkj.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Slf4j
public class KafkaTopicConsumer {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrapServers;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeserializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeserializer;

//    @KafkaListener(topics = "kafka_test_topic", groupId = "my-group")
    public void listen(String msg) {
        String[] words = msg.split(",");
        log.info("消费的消息num：{}", words[7]);
    }

}
