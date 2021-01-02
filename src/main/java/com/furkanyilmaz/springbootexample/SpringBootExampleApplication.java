package com.furkanyilmaz.springbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(scanBasePackages = "com.furkanyilmaz")		//Eğer bu şekilde tanımlarsak,
																	//@SpringBootApplication = @Configuration + @ComponentScan + @EnableAutoConfiguration olduğundan bunları tanımlamamıza gerek kalmıyor.
@SpringBootApplication
@ComponentScan(basePackages = "com.furkanyilmaz") 					//Kullanmadığımızda Service,Controller,Repository sınıflarımı Spring tanımaz.
@EntityScan(basePackages = "com.furkanyilmaz.model") 				//Kullanmadığımızda Entity sınıflarımı Spring tanımaz.
@EnableSwagger2 													//http://localhost:8080/swagger-ui.html#/ bağlantısın projenin swagger dökümanını almak için kullandım.
public class SpringBootExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExampleApplication.class, args);
	}

}
