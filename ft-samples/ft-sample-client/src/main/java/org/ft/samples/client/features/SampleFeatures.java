package org.ft.samples.client.features;

import org.ft.client.annotations.Feature;
import org.ft.client.annotations.FeatureToggles;
import org.ft.core.api.model.Phase;

/**
 * @author Prajwal Das
 */
@FeatureToggles
public enum SampleFeatures
{
    @Feature(name = "SAMPLE_FEATURE_001", enableOn = "2020-09-05", phase = Phase.PROD,description = "Demo Desc", group = "F1")
    SAMPLE_FEATURE_001,

    @Feature(name = "SAMPLE_FEATURE_002", value = true, description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_002,

    @Feature(name = "SAMPLE_FEATURE_003", phase = Phase.PROD, description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_003,

    @Feature(name = "SAMPLE_FEATURE_004", enableOn = "2020-09-23", description = "Demo Desc", group = "F1")
    SAMPLE_FEATURE_004,

    @Feature(name = "SAMPLE_FEATURE_005", value = true, description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_005,

    @Feature(name = "SAMPLE_FEATURE_006", enableOn = "2020-10-14", phase = Phase.PROD, description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_006,

    @Feature(name = "SAMPLE_FEATURE_007", description = "Demo Desc", group = "F1")
    SAMPLE_FEATURE_007,

    @Feature(name = "SAMPLE_FEATURE_008", phase = Phase.PROD, value = true, description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_008,

    @Feature(name = "SAMPLE_FEATURE_009", description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_009,
}
