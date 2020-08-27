package org.ft.server.resource;

import lombok.AllArgsConstructor;
import org.ft.core.api.model.FeatureInfo;
import org.ft.core.exceptions.FeatureToggleException;
import org.ft.core.request.FeatureTogglesRequest;
import org.ft.core.response.FeatureToggleResponse;
import org.ft.core.services.FeatureDataStore;
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
    private FeatureDataStore ftService;

    @PostMapping
    public FeatureInfo createFeature (@RequestBody FeatureInfo featureInfo)
    {
        return ftService.create(featureInfo).orElseThrow(() -> FeatureToggleException.FEATURE_NOT_FOUND);
    }

    @PostMapping("/bulk")
    public FeatureToggleResponse createFeatures (@RequestBody FeatureTogglesRequest request,
                                                @PathParam("appName") String appName)
    {
        return FeatureToggleResponse.builder().features(ftService.create(request.getFeatures())).build();
    }

    @GetMapping
    public List<FeatureInfo> getAllFeatureForTenant (@PathParam("tenant") String tenant)
    {
        return ftService.getFeatures(tenant);
    }

    @GetMapping("/{featureName}/enable")
    public void getEnableFeatureToggle (@PathVariable String featureName,
                                        @PathParam("tenant") String tenant)
    {
        ftService.enable(featureName, tenant);
    }

    @GetMapping("/{featureName}/disable")
    public void getDisableFeatureToggle (@PathVariable String featureName,
                                         @PathParam("tenant") String tenant)
    {
        ftService.disable(featureName, tenant);
    }

    @GetMapping("/{featureName}")
    public FeatureInfo getFeatureToggle (@PathVariable String featureName,
                                         @PathParam("tenant") String tenant)
    {
        return ftService.getFeature(
            featureName,
            tenant).orElseThrow(() -> FeatureToggleException.FEATURE_NOT_FOUND);
    }
}
