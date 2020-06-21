package com.alexxstepan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = {"com.alexxstepan.dao.repositories"})
@SpringBootApplication
//@EnableTransactionManagement
//@EnableAsync
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
