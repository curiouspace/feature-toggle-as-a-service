package org.ft.client.sample.service;

import org.ft.client.sample.config.FeaturesConfig;

/**
 * @author Prajwal Das
 */
public class DefaultFeatureSdk implements FeatureSdk
{
    private FeaturesConfig config;

    public DefaultFeatureSdk (FeaturesConfig config)
    {
        this.config = config;
    }

    public FeatureToggleClient getFeatureToggleClient () {
        return FeatureToggleClient.getInstance(config);
    }
}
