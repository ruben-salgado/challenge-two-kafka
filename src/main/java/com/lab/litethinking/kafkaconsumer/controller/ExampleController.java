package com.lab.litethinking.kafkaconsumer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.litethinking.kafkaconsumer.dto.User;
import com.lab.litethinking.kafkaconsumer.kafka.JsonKafkaProducer;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/school")
public class ExampleController {

    private JsonKafkaProducer jsonKafkaProducer;

    public ExampleController(JsonKafkaProducer jsonKafkaProducer) {
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @PostMapping("/student")
    public ResponseEntity<String> publish_json(@RequestBody User user){;
        jsonKafkaProducer.sendMessage(user);
        return ResponseEntity.ok("Message user sent to the topic");
    }

}
