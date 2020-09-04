package org.ft.client.config;

import lombok.Builder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Prajwal Das
 */
@Configuration
@Builder
@EnableConfigurationProperties(FeatureClientProperties.class)
@ComponentScan("org.ft.client")
public class FeatureToggleConfiguration {

    private FeatureClientProperties featureClientProperties;
}
