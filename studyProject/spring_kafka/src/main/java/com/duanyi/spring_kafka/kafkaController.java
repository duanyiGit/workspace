package com.duanyi.spring_kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class kafkaController {
    Logger log = LoggerFactory.getLogger(kafkaController.class);

    @Autowired
    protected KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/send")
    public  void  send(){
        Order order = new Order(1, 1, 2* 1000);

        ListenableFuture<SendResult<String, String>> futureCallback = kafkaTemplate.send("springTopic","0", "this is message");
        futureCallback.addCallback(new SuccessCallback<SendResult<String, String>>() {
                   @Override
                   public void onSuccess(SendResult<String, String> suc) {
                       RecordMetadata record = suc.getRecordMetadata();
                       log.info("发送消息成功topic:"+record.topic()+"|partition:"+record.partition()+"|offset:"+record.offset());
                   }
               },
                new FailureCallback() {
                    @Override
                    public void onFailure(Throwable fail) {
                        log.error("发送消息失败");
                    }
                });
    }

}
