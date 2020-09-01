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
    @Feature(id = "CFT-87654",
        name = "FEATURE 87654",
        enableOn = "2020-09-05",
        dependsOn = {"CFT-12346"}, phase = Phase.DEV,description = "Demo Desc", group = "F1")
    CFT_87654,

    @Feature(id = "CFT-23543", name = "FEATURE 23543", description = "Demo Desc", group = "F2")
    CFT_23543,

    @Feature(id = "CFT-09875", name = "FEATURE 09875",
        dependsOn = {"CFT-12346"}, phase = Phase.PROD, description = "Demo Desc", group = "F2")
    CFT_09875,

    @Feature(id = "CFT-12346", phase = Phase.PROD, name = "FEATURE 12346", enableOn = "2020-09-23", description = "Demo Desc", group = "F1")
    CFT_12346,
}
