package com.lab.litethinking.kafkaconsumer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lab.litethinking.kafkaconsumer.dto.User;

@Repository
public interface StudentRepository extends CrudRepository<User, Long> {}
