package org.ft.server;

import org.ft.jira.integration.config.EnableJiraIntegration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Prajwal Das
 */
@SpringBootApplication
@EnableJiraIntegration
public class FeatureToggleServer
{
	public static void main(String[] args) {
		SpringApplication.run(FeatureToggleServer.class, args);
	}
}
