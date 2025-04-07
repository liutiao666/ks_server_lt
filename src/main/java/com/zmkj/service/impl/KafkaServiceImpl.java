package com.zmkj.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmkj.config.KafkaConfig;
import com.zmkj.entity.clickhouse.AssistPerformance;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class KafkaServiceImpl {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    KafkaConfig kafkaConfig;

    public void sendMsg(String topic, int num) {
        for (int i = 0; i < num; i++) {
            AssistPerformance assistPerformance = new AssistPerformance();
            assistPerformance.setModelId(i);
            assistPerformance.setTaskId(i);
            assistPerformance.setTestNo("TestNo_" + i);
            assistPerformance.setTdId(i);
            assistPerformance.setEventDay(DateUtils.formatDate(new Date()));
            assistPerformance.setEventDaytime(System.currentTimeMillis());
            assistPerformance.setEventBatch(i);
            assistPerformance.setEventId(i);
            assistPerformance.setImageIndex("imageIndex_" + i);
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < 3; j++) {
                map.put("extralMap_" + i + "_" + j, j + "");
            }
            assistPerformance.setExtralMap(map);
            try {
                String json = new ObjectMapper().writeValueAsString(assistPerformance);
                kafkaTemplate.send(topic, json);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public long getTopicNums(String topic) {
        log.info("获取topic：{}的数据量", topic);
        long totalMessageCount = 0;
        try (KafkaConsumer<String, String> kafkaConsumer = getKafkaConsumer()) {
            if (!kafkaConsumer.listTopics().containsKey(topic)) {
                return 0;
            }
            Set<TopicPartition> partitions = kafkaConsumer.partitionsFor(topic).stream()
                    .map(partitionInfo -> new TopicPartition(topic, partitionInfo.partition()))
                    .collect(Collectors.toSet());

            Map<TopicPartition, Long> endOffsets = kafkaConsumer.endOffsets(partitions);
            Map<TopicPartition, Long> beginningOffsets = kafkaConsumer.beginningOffsets(partitions);
            log.info("endOffsets:{}, beginningOffsets:{}", endOffsets, beginningOffsets);
            int restMsgCount = 0;
            for (TopicPartition partition : partitions) {
                long startOffset = beginningOffsets.get(partition);
                long endOffset = endOffsets.get(partition);
                totalMessageCount += endOffset;
                restMsgCount += (endOffset - startOffset);
            }
            log.info("total msg num:{}, rest msg num:{}", totalMessageCount, restMsgCount);
        }

        return totalMessageCount;
    }

    private KafkaConsumer<String, String> getKafkaConsumer() {
        Map<String, Object> props = kafkaConfig.getProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
        return kafkaConsumer;
    }

}
