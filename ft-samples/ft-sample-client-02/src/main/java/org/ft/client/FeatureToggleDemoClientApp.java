package org.ft.client;

import org.ft.client.annotations.EnableFeatureToggleClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Prajwal Das
 */
@SpringBootApplication
@EnableFeatureToggleClient
public class FeatureToggleDemoClientApp
{
	public static void main(String[] args) {
		SpringApplication.run(FeatureToggleDemoClientApp.class, args);
	}
}