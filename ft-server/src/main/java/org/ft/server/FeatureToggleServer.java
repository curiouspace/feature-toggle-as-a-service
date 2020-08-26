package org.ft.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Prajwal Das
 */
@SpringBootApplication
@EntityScan("org.ft")
public class FeatureToggleServer
{
	public static void main(String[] args) {
		SpringApplication.run(FeatureToggleServer.class, args);
	}
}
