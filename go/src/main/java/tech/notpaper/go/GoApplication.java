package tech.notpaper.go;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoApplication.class, args);
	}
}
