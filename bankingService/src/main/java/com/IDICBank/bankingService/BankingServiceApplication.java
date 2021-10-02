package com.IDICBank.bankingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.IDICBank.bankingService")
@EnableJpaRepositories(basePackages =  "com.IDICBank.bankingService.repositories")

public class BankingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingServiceApplication.class, args);
	}

}
