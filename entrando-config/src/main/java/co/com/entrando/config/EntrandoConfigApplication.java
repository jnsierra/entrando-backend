package co.com.entrando.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EntrandoConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntrandoConfigApplication.class, args);
	}

}
