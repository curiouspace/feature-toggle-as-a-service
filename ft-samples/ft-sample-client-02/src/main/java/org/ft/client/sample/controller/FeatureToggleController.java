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

package org.ft.client.sample.controller;

import lombok.AllArgsConstructor;
import org.ft.client.sample.service.FeatureToggleClient;
import org.ft.core.response.FeatureToggleResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Prajwal Das
 */
@Controller
@AllArgsConstructor
@RequestMapping("/ui/features")
public class FeatureToggleController
{
    FeatureToggleClient featureToggleClient;

    @GetMapping
    public String getFeatureInfo(@RequestParam String tenant, Model model) {
        FeatureToggleResponse featureInfo = featureToggleClient.getAllFeaturesForTenant(tenant);
        model.addAttribute("featureToggles", featureInfo.getFeatures());
        return "index";
    }
}
