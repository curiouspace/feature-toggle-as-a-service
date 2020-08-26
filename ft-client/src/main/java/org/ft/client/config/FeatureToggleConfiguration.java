package org.ft.client.config;

import org.ft.client.service.DefaultTenantIdentifierService;
import org.ft.core.services.TenantIdentifierService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Prajwal Das
 */
@Configuration
@EnableConfigurationProperties(FeatureProperties.class)
@ComponentScan("org.ft.client")
public class FeatureToggleConfiguration {

    @Bean
    @ConditionalOnMissingBean
    TenantIdentifierService getTenantIdentifierService() {
        return new DefaultTenantIdentifierService();
    }
}
