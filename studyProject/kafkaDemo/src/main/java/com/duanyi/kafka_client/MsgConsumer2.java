package com.duanyi.kafka_client;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class MsgConsumer2 {
    public static void main(String[] args) throws InterruptedException {
        Properties prop = new Properties();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.150.8:9092");
        // 消费分组名
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "secondGroup");
        // 自动提交offset
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // 自动提交offset的间隔时间
        prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG , "30000");
        // 不自动提交offset,需手动提交，否则会产生重复消费
       // prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        /*心跳时间，服务端broker通过心跳确认consumer是否故障，如果发现故障，就会通过心跳下发
        rebalance的指令给其他的consumer通知他们进行rebalance操作，这个时间可以稍微短一点*/
        prop.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,1000);
        //服务端broker多久感知不到一个consumer心跳就认为他故障了，默认是10秒
        prop.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,10*1000);
        /*如果两次poll操作间隔超过了这个时间，broker就会认为这个consumer处理能力太弱，
        会将其踢出消费组，将分区分配给别的consumer消费*/
        prop.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,30*1000);
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);
        // 消费主题
        String topicName = "firstTopic";
        //普通的消费
        consumer.subscribe(Arrays.asList(topicName));
        //从biginning开始消费
        /*consumer.assign(Arrays.asList(new TopicPartition(topicName, 0)));
        consumer.seekToBeginning(Arrays.asList(new TopicPartition(topicName, 0)));*/
        //指定offset消费
        /*consumer.assign(Arrays.asList(new TopicPartition(topicName, 0)));
        consumer.seek(new TopicPartition(topicName, 0), 0);*/

        while (true) {
            /*
             * poll() API 是拉取消息的长轮询，主要是判断consumer是否还活着，只要我们持续调用poll()，
             * 消费者就会存活在自己所在的group中，并且持续的消费指定partition的消息。
             * 底层是这么做的：消费者向server持续发送心跳，如果一个时间段（session.
             * timeout.ms）consumer挂掉或是不能发送心跳，这个消费者会被认为是挂掉了，
             * 这个Partition也会被重新分配给其他consumer
             */
            ConsumerRecords<String, String> records = consumer.poll(Integer.MAX_VALUE);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("consumer2收到消息：topic:" + record.topic() + "|partition:" + record.partition()
                        + "|offeset:" + record.offset() + "|consumer group:" +prop.getProperty("group.id")+ "|value:" + record.value());
            }
//            if (records.count() > 0) {
//                // 提交offset
//                consumer.commitSync();
//            }
        }
    }

}
