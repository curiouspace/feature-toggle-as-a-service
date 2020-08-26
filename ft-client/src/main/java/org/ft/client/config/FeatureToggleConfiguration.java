package org.ft.client.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Prajwal Das
 */
@Configuration
@EnableConfigurationProperties(FeatureToggleConfigProperties.class)
@ComponentScan("org.ft.client")
public class FeatureToggleConfiguration {
}
