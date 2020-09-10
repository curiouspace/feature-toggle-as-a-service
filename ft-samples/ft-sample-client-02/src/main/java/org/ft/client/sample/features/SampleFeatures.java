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

package org.ft.client.sample.features;

import org.ft.client.sample.annotations.Feature;
import org.ft.client.sample.annotations.FeatureToggles;

/**
 * @author Prajwal Das
 */
@FeatureToggles
public enum SampleFeatures
{
    @Feature(id = "SCAP-76544", name = "FEATURE SCAP-76544", description = "Demo 08654", group = "D1", dependsOn = { "SCAP-34568", "SCAP-56543"})
    SCAP_76544,

    @Feature(id = "SCAP-53246", name = "FEATURE SCAP-53246", description = "Demo Desc", group = "D2")
    SCAP_53246,

    @Feature(id = "SCAP-87543", name = "FEATURE SCAP-87543", description = "Demo Desc", group = "D2")
    SCAP_87543,

    @Feature(id = "SCAP-34568", name = "FEATURE SCAP-34568", description = "Demo Desc", group = "D1")
    SCAP_34568,

    @Feature(id = "SCAP-12346", name = "FEATURE SCAP-12346", description = "Demo Desc", group = "D2", dependsOn = {"SCAP-87543", "SCAP-97664"})
    SCAP_12346,

    @Feature(id = "SCAP-97664", name = "FEATURE SCAP-97664", description = "Demo Desc", group = "D2")
    SCAP_7664,

    @Feature(id = "SCAP-56543", name = "FEATURE SCAP-56543", description = "Demo Desc", group = "D1")
    SCAP_56543,
}
