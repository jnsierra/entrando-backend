package co.com.entrando.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EntrandoDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntrandoDiscoveryApplication.class, args);
	}

}