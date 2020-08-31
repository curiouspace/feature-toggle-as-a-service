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
    @Feature(id = "SAMPLE_FEATURE_001",
        name = "SAMPLE_FEATURE_001",
        enableOn = "2020-09-05",
        dependsOn = {"SAMPLE_FEATURE_003", "SAMPLE_FEATURE_006"},phase = Phase.PROD,description = "Demo Desc", group = "F1")
    SAMPLE_FEATURE_001,

    @Feature(id = "SAMPLE_FEATURE_002", name = "SAMPLE_FEATURE_002", description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_002,

    @Feature(id = "SAMPLE_FEATURE_003", name = "SAMPLE_FEATURE_003",
        dependsOn = {"SAMPLE_FEATURE_007", "SAMPLE_FEATURE_004"}, phase = Phase.PROD, description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_003,

    @Feature(id = "SAMPLE_FEATURE_004", name = "SAMPLE_FEATURE_004", enableOn = "2020-09-23", description = "Demo Desc", group = "F1")
    SAMPLE_FEATURE_004,

    @Feature(id = "SAMPLE_FEATURE_005", name = "SAMPLE_FEATURE_005", description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_005,

    @Feature(id = "SAMPLE_FEATURE_006", name = "SAMPLE_FEATURE_006", enableOn = "2020-10-14", phase = Phase.PROD, description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_006,

    @Feature(id = "SAMPLE_FEATURE_007", name = "SAMPLE_FEATURE_007", description = "Demo Desc", group = "F1")
    SAMPLE_FEATURE_007,

    @Feature(id = "SAMPLE_FEATURE_008", name = "SAMPLE_FEATURE_008", phase = Phase.PROD, description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_008,

    @Feature(id = "SAMPLE_FEATURE_009", name = "SAMPLE_FEATURE_009", description = "Demo Desc", group = "F2")
    SAMPLE_FEATURE_009,
}
