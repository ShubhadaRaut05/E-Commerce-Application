package com.shubhada.userservice.clients;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerClient {
    //create kafka template
    private KafkaTemplate<String,String> kafkaTemplate;
    public KafkaProducerClient(KafkaTemplate<String,String> kafkaTemplate){

        this.kafkaTemplate=kafkaTemplate;
    }
    //sending message within the topic
    public void sendMessage(String topic,String message){
        kafkaTemplate.send(topic,message);

    }
    //message is the json string of whatever data you want to send
}
