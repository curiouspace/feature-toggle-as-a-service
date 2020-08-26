package org.ft.client.service;

import org.ft.client.config.FeatureToggleConfigProperties;
import org.ft.core.models.FeatureToggle;
import org.ft.core.request.FeatureTogglesRequest;
import org.ft.core.response.FeatureToggleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Prajwal Das
 */
@Component
@AllArgsConstructor
public class FeatureToggleService
{
    private FeatureToggleConfigProperties props;

    private Map<String, FeatureToggle> cacheMap = new HashMap<>();

    public void cache(List<FeatureToggle> toggles) {
        toggles.forEach(d -> cacheMap.put(d.getName(), d));
    }

    public FeatureToggle getFeatureToggle (String featureName) {
        if(cacheMap.get(featureName) == null) {
            FeatureToggle featureToggle = getFeatureStatusFromService(featureName);
            cacheMap.put(featureToggle.getName(), featureToggle);
            return featureToggle;
        }
        return cacheMap.get(featureName);
    }

    public FeatureToggleResponse syncFeatureToggles(List<FeatureToggle> features)
    {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = props.getUrl() + "/features/bulk?appName={appName}";

        ResponseEntity<FeatureToggleResponse> result = restTemplate.postForEntity(
            baseUrl,
            FeatureTogglesRequest.builder().featureToggles(features).build(),
            FeatureToggleResponse.class,
            props.getAppName());

        FeatureToggleResponse response = result.getBody();
        if(response != null) {
            cache(response.getFeatureToggles());
        }
        return response;
    }

    public FeatureToggle getFeatureStatusFromService (String featureName)
    {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = props.getUrl() + "/features/" + featureName + "?appName={appName}";

        ResponseEntity<FeatureToggle> result = restTemplate.getForEntity(
            baseUrl,
            FeatureToggle.class,
            props.getAppName());

        return result.getBody();
    }
}
