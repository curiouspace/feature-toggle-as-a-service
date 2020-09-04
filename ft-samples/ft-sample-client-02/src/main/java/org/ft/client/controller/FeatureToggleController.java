package org.ft.client.controller;

import lombok.AllArgsConstructor;
import org.ft.client.service.FeatureToggleClient;
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
