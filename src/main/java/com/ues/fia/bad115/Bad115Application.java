package com.ues.fia.bad115;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication (exclude = {
		SecurityAutoConfiguration.class
})
@EnableJpaRepositories("com.ues.fia.bad115.repository")
@EntityScan("com.ues.fia.bad115.clase")
@EnableTransactionManagement
@EnableVaadin

public class Bad115Application {

	public static void main(String[] args) {
		System.setProperty("server.port","9090");
		SpringApplication.run(Bad115Application.class, args);
	}

}
