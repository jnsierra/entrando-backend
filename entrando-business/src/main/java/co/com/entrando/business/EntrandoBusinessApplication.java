package co.com.entrando.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("co.com.entrando")
public class EntrandoBusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntrandoBusinessApplication.class, args);
	}

}
