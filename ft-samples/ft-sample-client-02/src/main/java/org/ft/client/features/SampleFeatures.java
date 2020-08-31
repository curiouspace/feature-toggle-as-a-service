package org.ft.client.features;

import org.ft.client.annotations.Feature;
import org.ft.client.annotations.FeatureToggles;

/**
 * @author Prajwal Das
 */
@FeatureToggles
public enum SampleFeatures
{
    @Feature(id = "DEMO_FEATURE_001", name = "DEMO_FEATURE_001", description = "Demo Desc", group = "D1")
    DEMO_FEATURE_001,

    @Feature(id = "DEMO_FEATURE_002", name = "DEMO_FEATURE_002", description = "Demo Desc", group = "D2")
    DEMO_FEATURE_002,

    @Feature(id = "DEMO_FEATURE_003", name = "DEMO_FEATURE_003", description = "Demo Desc", group = "D2")
    DEMO_FEATURE_003,

    @Feature(id = "DEMO_FEATURE_004", name = "DEMO_FEATURE_004", description = "Demo Desc", group = "D1")
    DEMO_FEATURE_004,

    @Feature(id = "DEMO_FEATURE_005", name = "DEMO_FEATURE_005", description = "Demo Desc", group = "D2")
    DEMO_FEATURE_005,

    @Feature(id = "DEMO_FEATURE_006", name = "DEMO_FEATURE_006", description = "Demo Desc", group = "D2")
    DEMO_FEATURE_006,

    @Feature(id = "DEMO_FEATURE_007", name = "DEMO_FEATURE_007", description = "Demo Desc", group = "D1")
    DEMO_FEATURE_007,

    @Feature(id = "DEMO_FEATURE_008", name = "DEMO_FEATURE_008", description = "Demo Desc", group = "D2")
    DEMO_FEATURE_008,

    @Feature(id = "DEMO_FEATURE_009", name = "DEMO_FEATURE_009", description = "Demo Desc", group = "D2")
    DEMO_FEATURE_009,
}
