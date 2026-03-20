package com.curso.ecomers.spring_emcommernce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = DataSourceAutoConfiguration.class)
public class SpringEmcommernceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEmcommernceApplication.class, args);
	}

}
