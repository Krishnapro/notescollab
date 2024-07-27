package com.notescollab.notescollab;

import com.notescollab.notescollab.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecurityConfig.RsaKeyProperties.class)
public class NotescollabApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotescollabApplication.class, args);
	}

}
