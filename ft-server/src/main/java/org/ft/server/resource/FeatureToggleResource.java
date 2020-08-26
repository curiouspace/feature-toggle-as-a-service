package org.ft.server.resource;

import org.ft.core.models.FeatureToggle;
import org.ft.core.request.FeatureTogglesRequest;
import org.ft.core.response.FeatureToggleResponse;
import org.ft.server.service.FeatureToggleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author Prajwal Das
 */
@RestController
@AllArgsConstructor
@RequestMapping("/features")
public class FeatureToggleResource
{
    private FeatureToggleService ftService;

    @PostMapping
    public FeatureToggle createOrUpdateFeatureToggles (@RequestBody FeatureToggle featureToggle, @PathParam("appName") String appName) {
        return ftService.createOrUpdateFeatureToggle(featureToggle, appName);
    }

    @PostMapping("/bulk")
    public FeatureToggleResponse createOrUpdateFeatureToggles (@RequestBody FeatureTogglesRequest request, @PathParam("appName") String appName) {
        List<FeatureToggle> featureToggles = ftService.createOrUpdateFeatureToggles(request, appName);
        return FeatureToggleResponse.builder().featureToggles(featureToggles).build();
    }

    @GetMapping
    public List<FeatureToggle> getAllFeatureToggles(@PathParam("appName") String appName) {
        return ftService.getAllFeatureToggle(appName);
    }

    @GetMapping("/{featureName}")
    public FeatureToggle getFeatureToggle (@PathVariable String featureName, @PathParam("appName") String appName) {
        return ftService.getFeatureToggle(featureName, appName);
    }
}
