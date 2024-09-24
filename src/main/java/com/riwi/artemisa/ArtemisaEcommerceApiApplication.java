package com.riwi.artemisa;

import com.riwi.artemisa.infrastructure.config.JwtTokenGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ArtemisaEcommerceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtemisaEcommerceApiApplication.class, args);
		String token = JwtTokenGenerator.generateJwtToken();
		System.out.println(token);
	}


}
