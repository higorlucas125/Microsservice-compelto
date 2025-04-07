package br.com.techtaste.ms_conf_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MsConfServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsConfServerApplication.class, args);
	}

}
