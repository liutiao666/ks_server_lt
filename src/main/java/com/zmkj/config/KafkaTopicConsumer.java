package com.zmkj.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmkj.entity.clickhouse.AssistPerformance;
import com.zmkj.mapper.clickhouse.AssistPerformanceDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@Component
@Slf4j
public class KafkaTopicConsumer {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrapServers;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeserializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeserializer;

    @Autowired
    private Executor asyncClickHouseExecutor;

    @Autowired
    private AssistPerformanceDao assistPerformanceDao;

    @KafkaListener(topics = "kafka_ck_topic", groupId = "my-group", containerFactory =
            "kafkaListenerContainerFactory")
    public void listen(List<String> records) {
        List<AssistPerformance> list = new ArrayList<>();
        for (String record : records) {
            try {
                AssistPerformance assistPerformance = new ObjectMapper().readValue(record, AssistPerformance.class);
                list.add(assistPerformance);
            } catch (Exception e) {
                log.error("Failed to parse JSON message: {}", record, e);
            }
        }
        int batchSize = 1000;
        for (int i = 0; i < list.size(); i += batchSize) {
            int end = Math.min(i + batchSize, list.size());
            List<AssistPerformance> batch = list.subList(i, end);
            asyncClickHouseExecutor.execute(() -> assistPerformanceDao.batchInsertCK(batch));
//            ack.acknowledge();
        }
    }

}
