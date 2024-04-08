package com.lab.litethinking.kafkaconsumer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Service
public class JsonKafkaProducer {

    @Value("${spring.kafka.topic.name}")
    private String topicJsonName;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, byte[]> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Object object){
        LOGGER.info(String.format("Message sent -> %s", object.toString()));
        try {
            byte[] bytes = convertUserToBytes(object);
            Message<byte[]> message = MessageBuilder.withPayload(bytes)
                            .setHeader(KafkaHeaders.TOPIC, topicJsonName)
                    .build();
            kafkaTemplate.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] convertUserToBytes(Object object) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }


}
