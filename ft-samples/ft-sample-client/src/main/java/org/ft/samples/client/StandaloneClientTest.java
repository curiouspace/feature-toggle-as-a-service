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
