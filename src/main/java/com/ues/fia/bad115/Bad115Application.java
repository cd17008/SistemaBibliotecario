package com.ues.fia.bad115;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class
}, scanBasePackages = { "com.ues.fia.bad115" })
@EnableJpaRepositories("com.ues.fia.bad115.repository")
@ComponentScan("com.ues.fia.bad115")
@EntityScan("com.ues.fia.bad115.clase")
@EnableTransactionManagement
@EnableVaadin

public class Bad115Application extends SpringBootServletInitializer implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(Bad115Application.class, args);
	}

}
