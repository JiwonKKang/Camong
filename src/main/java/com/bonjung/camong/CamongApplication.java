package com.bonjung.camong;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(servers = {@Server(url = "https://camong.p-e.kr")})
public class CamongApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamongApplication.class, args);
	}

}
