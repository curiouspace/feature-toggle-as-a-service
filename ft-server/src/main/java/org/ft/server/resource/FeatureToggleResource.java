package org.ft.server.resource;

import lombok.AllArgsConstructor;
import org.ft.core.api.model.FeatureInfo;
import org.ft.core.exceptions.FeatureToggleException;
import org.ft.core.request.FeatureTogglesRequest;
import org.ft.core.response.FeatureToggleResponse;
import org.ft.core.services.FeatureDataStore;
import org.ft.core.services.TenantIdentifierService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return ftService.createOrUpdate(featureInfo).orElseThrow(() -> FeatureToggleException.FEATURE_NOT_FOUND);
    }

    @PostMapping("/bulk")
    public FeatureToggleResponse createFeatures (@RequestBody FeatureTogglesRequest request)
    {
        return FeatureToggleResponse.builder().features(ftService.createOrUpdate(request.getFeatures())).build();
    }

    @GetMapping
    public List<FeatureInfo> getAllFeatureForTenant (@RequestParam(defaultValue = TenantIdentifierService.DEFAULT) String tenant )
    {
        return ftService.getFeatures(tenant);
    }

    @PostMapping("/{featureName}/{status}")
    public void setFeatureStatus (@PathVariable String featureName,
                                  @PathVariable Boolean status,
                                  @RequestParam(defaultValue = TenantIdentifierService.DEFAULT) String tenant)
    {
        if(status) {
            ftService.enable(featureName, tenant);
        } else {
            ftService.disable(featureName, tenant);
        }
    }

    @PostMapping("/{featureName}/enable")
    public void getEnableFeatureToggle (@PathVariable String featureName,
                                        @RequestParam(defaultValue = TenantIdentifierService.DEFAULT) String tenant)
    {
        ftService.enable(featureName, tenant);
    }

    @PostMapping("/{featureName}/disable")
    public void getDisableFeatureToggle (@PathVariable String featureName,
                                         @RequestParam(defaultValue = TenantIdentifierService.DEFAULT) String tenant)
    {
        ftService.disable(featureName, tenant);
    }

    @GetMapping("/tenants")
    public List<String> getAllTenantsIdentifiers ()
    {
        return ftService.getAllTenantsIdentifiers();
    }

    @GetMapping("/{featureName}")
    public FeatureInfo getFeatureToggle (@PathVariable String featureName,
                                         @RequestParam(defaultValue = TenantIdentifierService.DEFAULT) String tenant)
    {
        return ftService.getFeature(
            featureName,
            tenant).orElseThrow(() -> FeatureToggleException.FEATURE_NOT_FOUND);
    }
}
