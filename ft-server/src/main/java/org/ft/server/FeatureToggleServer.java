package org.ft.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Prajwal Das
 */
@SpringBootApplication
public class FeatureToggleServer
{
	public static void main(String[] args) {
		SpringApplication.run(FeatureToggleServer.class, args);
	}
}
