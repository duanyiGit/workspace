package com.duanyi.spring_kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class kafkaConsumer {
    Logger log = LoggerFactory.getLogger(kafkaConsumer.class);

    @KafkaListener(topics ="springTopic",groupId = "1")
    public void getMessage(ConsumerRecord<String,String> record){
        log.error("偏移量:"+record.offset());
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
    }
}
