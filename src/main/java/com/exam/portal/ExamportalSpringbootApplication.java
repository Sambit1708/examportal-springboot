package com.exam.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class ExamportalSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamportalSpringbootApplication.class, args);
	}
}
