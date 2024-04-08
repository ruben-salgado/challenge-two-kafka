package com.lab.litethinking.kafkaconsumer.dto;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String[] subjects;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String[] getSubjects() {
		return subjects;
	}

	public void setSubjects(String[] subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", subjects=" + Arrays.toString(subjects) + "]";
	}

}
