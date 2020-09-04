package com.duanyi.kafka_client;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MsgProducer {
    public static void main(String[] args) {
        try {
            Properties prop = new Properties();
            prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.150.8:9092");
            //发出消息持久化机制参数
            prop.put(ProducerConfig.ACKS_CONFIG, "1");
            //发送失败会重试，默认重试间隔100ms，重试能保证消息发送的可靠性，但是也可能造成消息重复发送，
            // 比如网络抖动，所以需要在接收者那边做好消息接收的幂等性处理
            prop.put(ProducerConfig.RETRIES_CONFIG, 3);
            //重试间隔设置
            prop.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 300);
            //设置发送消息的本地缓冲区，如果设置了该缓冲区，消息会先发送到本地缓冲区，
            // 可以提高消息发送性能，默认值是33554432，即32MB
            prop.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
            //kafka本地线程会从缓冲区取数据，批量发送到broker，
            //设置批量发送消息的大小，默认值是16384，即16kb，就是说一个batch满了16kb就发送出去
            prop.put(ProducerConfig.BATCH_SIZE_CONFIG, 16);
            //如果100毫秒内，batch没满，那么也必须把消息发送出去，不能让消息的发送延迟时间太长
            prop.put(ProducerConfig.LINGER_MS_CONFIG, 100);
            //把发送消息key从字符串序列化为字节数组
            prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            //把发送消息value从字符串序列化为字节数组
            prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            KafkaProducer producer = new KafkaProducer(prop);
            int msgNum = 2;
            CountDownLatch countDownLatch = new CountDownLatch(msgNum);
            for (int i = 0; i < msgNum; i++) {
                Order order = new Order(i, 1, i * 1000);
                //未指定发送分区，具体发送的分区计算公式：hash(key)%partitionNum
                ProducerRecord<String, String> producerRecord = new ProducerRecord("myTopic",0
                , String.valueOf(order.getOrderId()), JSON.toJSONString(order));
                //指定发送分区
                //ProducerRecord producerRecord = new ProducerRecord("firstTopic", 0, String.valueOf(order.getOrderId()), JSON.toJSONString(order));
                //异步发送
                producer.send(producerRecord, (recordMetadata, e) -> {
                    if (e != null) {
                        System.out.println("send error");
                    }
                    if (recordMetadata != null) {
                        System.out.println("异步发送消息结果: topic-" + recordMetadata.topic()
                                + "|partition-" + recordMetadata.partition() + "|offset-" + recordMetadata.offset());
                    }
                    countDownLatch.countDown();
                });
                //同步发送
              /* RecordMetadata metadata = (RecordMetadata) producer.send(producerRecord).get();
                System.out.println("异步发送消息结果: topic-"+metadata.topic()
                        +"|partition-"+metadata.partition()+"|offset"+metadata.offset());*/
            }
            countDownLatch.await(5, TimeUnit.SECONDS);
            producer.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
