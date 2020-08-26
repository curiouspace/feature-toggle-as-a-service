package org.ft.samples.client.features;

import org.ft.client.annotations.Feature;
import org.ft.client.annotations.FeatureToggles;

/**
 * @author Prajwal Das
 */
@FeatureToggles
public enum SampleFeatures
{
    @Feature(name = "SAMPLE_FEATURE_001", description = "Demo Desc")
    SAMPLE_FEATURE_001,

    @Feature(name = "SAMPLE_FEATURE_002", value = true, description = "Demo Desc")
    SAMPLE_FEATURE_002,

    @Feature(name = "SAMPLE_FEATURE_003", description = "Demo Desc")
    SAMPLE_FEATURE_003,
}
