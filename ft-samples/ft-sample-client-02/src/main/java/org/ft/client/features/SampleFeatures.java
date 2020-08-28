package org.ft.client.features;

import org.ft.client.annotations.Feature;
import org.ft.client.annotations.FeatureToggles;

/**
 * @author Prajwal Das
 */
@FeatureToggles
public enum SampleFeatures
{
    @Feature(name = "DEMO_FEATURE_001", description = "Demo Desc", group = "D1")
    DEMO_FEATURE_001,

    @Feature(name = "DEMO_FEATURE_002", value = true, description = "Demo Desc", group = "D2")
    DEMO_FEATURE_002,

    @Feature(name = "DEMO_FEATURE_003", description = "Demo Desc", group = "D2")
    DEMO_FEATURE_003,

    @Feature(name = "DEMO_FEATURE_004", description = "Demo Desc", group = "D1")
    DEMO_FEATURE_004,

    @Feature(name = "DEMO_FEATURE_005", value = true, description = "Demo Desc", group = "D2")
    DEMO_FEATURE_005,

    @Feature(name = "DEMO_FEATURE_006", description = "Demo Desc", group = "D2")
    DEMO_FEATURE_006,

    @Feature(name = "DEMO_FEATURE_007", description = "Demo Desc", group = "D1")
    DEMO_FEATURE_007,

    @Feature(name = "DEMO_FEATURE_008", value = true, description = "Demo Desc", group = "D2")
    DEMO_FEATURE_008,

    @Feature(name = "DEMO_FEATURE_009", description = "Demo Desc", group = "D2")
    DEMO_FEATURE_009,
}
