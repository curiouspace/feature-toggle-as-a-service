package org.ft.jira.integration.config;

import lombok.Builder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Prajwal Das
 */
@Configuration
@Builder
@EnableConfigurationProperties(FeatureJiraProperties.class)
@ComponentScan("org.ft.jira")
public class FeatureToggleJiraConfiguration
{
}
