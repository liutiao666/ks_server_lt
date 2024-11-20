package com.zmkj.service.impl;

import com.zmkj.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            LocalDateTime now = LocalDateTime.now();
            // 定义日期时间格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // 格式化当前时间
            String dataStr = now.format(formatter);
            kafkaTemplate.send(topic, "19,1,test_2022_0_000014,15," + dataStr + ",1661243258,0," + (i + 1) + ",\"0\",\"8454146.0000000\",\"810002\",未定义,\"04\",线阵CMOS4,\"04\",未定义,EB,\"8454146.0000000\",\"810002\",未定义,\"04\",线阵CMOS4,\"04\",未定义,EB,\"8454146.0000000\",\"810002\",未定义,\"04\",线阵CMOS4,\"04\",未定义,EB,\"8454146.0000000\",\"810002\",未定义,\"04\",线阵CMOS4,\"04\",未定义,EB,\"8454146.0000000\",\"810002\",未定义,\"04\",线阵CMOS4,\"04\",未定义,EB,\"8454146.0000000\",\"810002\",未定义,\"04\",线阵CMOS4,\"04\",未定义,EB,\"8454146.0000000\",\"810002\",未定义,\"04\",线阵CMOS4,\"04\",未定义,EB,\"8454146.0000000\",\"810002\",未定义,\"04\",线阵CMOS4,\"04\",未定义,EB,\"8454146.0000000\",\"810002\",未定义,\"04\",线阵CMOS4,\"04\",未定义,EB,\"8454146.0000000\",\"810002\",未定义,\"04\",线阵CMOS4,\"04\",未定义,EB");
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
