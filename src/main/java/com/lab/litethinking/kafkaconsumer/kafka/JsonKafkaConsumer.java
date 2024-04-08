package com.lab.litethinking.kafkaconsumer.kafka;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.lab.litethinking.kafkaconsumer.dto.User;
import com.lab.litethinking.kafkaconsumer.repository.StudentRepository;

@Service
public class JsonKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    private StudentRepository studentRepository;
    
    public JsonKafkaConsumer(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload byte[] data){
        try{
        	User user = (User) convertBytesToObject(data);
            System.out.println("Mensaje recibido => " + user.getId());

            boolean exists = studentRepository.existsById(user.getId());
            if(!exists) {
            	studentRepository.save(user);
            	LOGGER.info("El Alumno ya se registro en la base");
            }else{
            	User tmp = studentRepository.findById(user.getId()).get();
                LOGGER.info("El Alumno ya se encuentra en la base {}",tmp);
            }

        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Object convertBytesToObject(byte[] data) throws IOException, ClassNotFoundException{
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        }
    }

}
