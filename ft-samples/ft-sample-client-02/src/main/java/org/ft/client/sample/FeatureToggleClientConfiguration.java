/*
 *    Copyright 2019-2030 codecuriosity
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.ft.client.sample;

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
            .appName("Sample-02")
            .deploymentPhase(Phase.PROD)
            .basePackage("org.ft")
            .url("http://localhost:8110").build());

        FeatureToggleClient cli = sdk.getFeatureToggleClient();
        cli.init();
        return cli;
    }
}
