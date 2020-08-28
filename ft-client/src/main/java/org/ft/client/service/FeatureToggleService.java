package org.ft.client.service;

import lombok.AllArgsConstructor;
import org.ft.client.config.FeatureProperties;
import org.ft.core.api.model.FeatureInfo;
import org.ft.core.request.FeatureTogglesRequest;
import org.ft.core.response.FeatureToggleResponse;
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
    private FeatureProperties props;

    private Map<String, FeatureInfo> cacheMap = new HashMap<>();

    public void cache(List<FeatureInfo> toggles) {
        toggles.forEach(d -> cacheMap.put(d.getName(), d));
    }

    public FeatureInfo getFeatureToggle (String featureName) {
        if(cacheMap.get(featureName) == null) {
            FeatureInfo featureToggle = getFeatureStatusFromService(featureName);
            cacheMap.put(featureToggle.getName(), featureToggle);
            return featureToggle;
        }
        return cacheMap.get(featureName);
    }

    public FeatureToggleResponse registerAppAndFeatureToggles (List<FeatureInfo> features)
    {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = props.getUrl() + "/features/bulk";

        ResponseEntity<FeatureToggleResponse> result = restTemplate.postForEntity(
            baseUrl,
            FeatureTogglesRequest.builder().features(features).build(),
            FeatureToggleResponse.class,
            props.getAppName());

        FeatureToggleResponse response = result.getBody();
        if(response != null) {
            cache(response.getFeatures());
        }
        return response;
    }

    public FeatureInfo getFeatureStatusFromService (String featureName)
    {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = props.getUrl() + "/features/" + featureName + "?appName={appName}&phase={phase}";

        ResponseEntity<FeatureInfo> result = restTemplate.getForEntity(
            baseUrl,
            FeatureInfo.class,
            props.getAppName(),
            props.getDeploymentPhase());

        return result.getBody();
    }
}
