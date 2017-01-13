package com.mike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
The @SpringBootApplication annotation is shorthand for:

	- @Configuration: 			Source of beans for the Spring context
	- @EnableAutoConfiguration: Indicates that beans should be added from the classpath
	- @EnableWebMvc: 			- Added if spring-webmvc is in the classpath
					 			- Activates key behaviours, such as Dispatcher servlets and other common settins needed in MVC projects
	- @ComponentScan: 			Indicates that components reside in the com.mike package

Note that, if you define any configuration beans manually (such as a SpringTemplateEngine), then Spring Boot will not load one itself and use yours instead
 */
@SpringBootApplication
public class PlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaygroundApplication.class, args);
	}
}
