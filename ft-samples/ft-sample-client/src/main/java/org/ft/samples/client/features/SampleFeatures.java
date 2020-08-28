package org.ft.samples.client.features;

import org.ft.client.annotations.Feature;
import org.ft.client.annotations.FeatureToggles;

/**
 * @author Prajwal Das
 */
@FeatureToggles
public enum SampleFeatures
{
    @Feature(name = "SAMPLE_FEATURE_001", description = "Demo Desc", group = "F1")
    SAMPLE_FEATURE_001,

    @Feature(name = "SAMPLE_FEATURE_002", value = true, description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_002,

    @Feature(name = "SAMPLE_FEATURE_003", description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_003,

    @Feature(name = "SAMPLE_FEATURE_004", description = "Demo Desc", group = "F1")
    SAMPLE_FEATURE_004,

    @Feature(name = "SAMPLE_FEATURE_005", value = true, description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_005,

    @Feature(name = "SAMPLE_FEATURE_006", description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_006,

    @Feature(name = "SAMPLE_FEATURE_007", description = "Demo Desc", group = "F1")
    SAMPLE_FEATURE_007,

    @Feature(name = "SAMPLE_FEATURE_008", value = true, description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_008,

    @Feature(name = "SAMPLE_FEATURE_009", description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_009,
}
