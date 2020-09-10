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

package org.ft.samples.client;

import org.ft.client.sample.config.FeaturesConfig;
import org.ft.client.sample.service.FeatureToggleClient;
import org.ft.client.sample.service.FeatureSdk;
import org.ft.core.api.model.Phase;
import org.ft.core.response.FeatureToggleResponse;

/**
 * @author Prajwal Das
 */
public class StandaloneClientTest
{
    public static void main (String[] args)
    {
        FeatureSdk sdk = FeatureSdk.getDefaultSdk(FeaturesConfig.builder()
            .appName("ft-client-prod")
            .deploymentPhase(Phase.DEV)
            .basePackage("org.ft")
            .url("http://localhost:8110").build());

        FeatureToggleClient client = sdk.getFeatureToggleClient();
        FeatureToggleResponse res = client.getAllFeaturesForTenant("s4All-01");
        System.out.println(res);
    }
}
