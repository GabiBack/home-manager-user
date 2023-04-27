package com.example.homemanageruser.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class UserMessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessage(String message) {
        kafkaTemplate.send("my-topic", message);
    }
}
