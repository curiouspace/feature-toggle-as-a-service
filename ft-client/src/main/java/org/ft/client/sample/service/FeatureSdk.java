package org.ft.client.sample.service;

import org.ft.client.sample.config.FeaturesConfig;

/**
 * @author Prajwal Das
 */
public interface FeatureSdk
{
    static FeatureSdk getDefaultSdk (FeaturesConfig clientConfig) {
        return new DefaultFeatureSdk(clientConfig);
    }

    FeatureToggleClient getFeatureToggleClient ();
}
