package co.com.entrando.datos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("co.com.entrando")
public class EntrandoAccesoDatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntrandoAccesoDatosApplication.class, args);
	}

}