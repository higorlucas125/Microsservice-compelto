package br.com.techtaste.microsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicrosserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrosserviceApplication.class, args);
	}

}
