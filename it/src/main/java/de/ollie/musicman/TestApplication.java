package de.ollie.musicman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * A test application to start the services for testing.
 *
 * @author ollie (23.04.2020)
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("de.ollie")
@EnableJpaRepositories("de.ollie.musicman.persistence.repository")
@EntityScan("de.ollie.musicman.persistence.dbo")
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}