/*
 *    Copyright 2019-2030 codecuriosity
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.ft.samples.client.features;

import org.ft.client.sample.annotations.Feature;
import org.ft.client.sample.annotations.FeatureToggles;
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
