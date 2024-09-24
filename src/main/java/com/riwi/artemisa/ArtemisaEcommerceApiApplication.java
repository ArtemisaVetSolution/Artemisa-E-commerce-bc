package com.riwi.artemisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class ArtemisaEcommerceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtemisaEcommerceApiApplication.class, args);
		
	}


}
