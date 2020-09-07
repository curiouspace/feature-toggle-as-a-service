package org.ft.samples.client;

import lombok.AllArgsConstructor;
import org.ft.client.sample.config.FeaturesConfig;
import org.ft.client.sample.service.FeatureToggleClient;
import org.ft.client.sample.service.FeatureSdk;
import org.ft.core.api.model.Phase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Prajwal Das
 */
@AllArgsConstructor
@Configuration
public class FeatureToggleClientConfiguration
{
    @Bean
    public FeatureToggleClient featureToggleClient() {
        FeatureSdk sdk = FeatureSdk.getDefaultSdk(FeaturesConfig.builder()
            .appName("Sample-01")
            .deploymentPhase(Phase.DEV)
            .basePackage("org.ft")
            .url("http://localhost:8110").build());

        FeatureToggleClient cli = sdk.getFeatureToggleClient();
        cli.init();
        return cli;
    }
}
