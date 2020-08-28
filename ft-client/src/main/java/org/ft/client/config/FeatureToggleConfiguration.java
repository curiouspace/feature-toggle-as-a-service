package org.ft.client.config;

import lombok.Builder;
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
@Builder
@EnableConfigurationProperties(FeatureProperties.class)
@ComponentScan("org.ft.client")
public class FeatureToggleConfiguration {

    private FeatureProperties featureProperties;

    @Bean
    @ConditionalOnMissingBean
    TenantIdentifierService getTenantIdentifierService() {
        return new DefaultTenantIdentifierService();
    }
}
