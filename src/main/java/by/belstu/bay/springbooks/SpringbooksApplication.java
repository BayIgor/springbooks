package by.belstu.bay.springbooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // автоматическая конфигурация Spring, и автоматическое сканирование (scan) всего проекта, чтобы найти компоненты Spring (Controller, Bean, Service, ...)
public class SpringbooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbooksApplication.class, args);
	}

}
