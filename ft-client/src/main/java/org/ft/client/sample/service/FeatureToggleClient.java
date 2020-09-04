package org.ft.client.sample.service;

import com.google.common.collect.Sets;
import org.ft.client.sample.annotations.Feature;
import org.ft.client.sample.annotations.FeatureToggles;
import org.ft.client.sample.config.FeaturesConfig;
import org.ft.client.sample.exception.ParseFeatureEnumException;
import org.ft.core.api.model.FeatureInfo;
import org.ft.core.request.FeatureTogglesRequest;
import org.ft.core.response.FeatureToggleResponse;
import org.reflections.Reflections;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Prajwal Das
 */
public class FeatureToggleClient implements FeatureClient
{
    private static FeatureToggleClient INSTANCE;

    private FeaturesConfig config;

    private FeatureToggleClient (FeaturesConfig config)
    {
        this.config = config;
    }

    public static FeatureToggleClient getInstance(FeaturesConfig config) {
        if(INSTANCE == null) {
            INSTANCE = new FeatureToggleClient(config);
        }
        return INSTANCE;
    }


    @Override
    public void init ()
    {
        registerAppAndFeatures();
    }

    public FeatureToggleResponse getAllFeaturesForTenant (String tenant)
    {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl =
            config.getUrl() + "/features?tenant={tenant}&appName={appName}&phase={phase}";

        ResponseEntity<FeatureToggleResponse> result = restTemplate.getForEntity(baseUrl,
            FeatureToggleResponse.class,
            tenant,
            config.getAppName(),
            config.getDeploymentPhase());

        return result.getBody();
    }

    private FeatureToggleResponse registerAppAndFeatures ()
    {

        List<FeatureInfo> features = processAnnotations();
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl =
            config.getUrl() + "/features/bulk" + "?appName={appName}&phase={phase}";

        ResponseEntity<FeatureToggleResponse> result = restTemplate.postForEntity(baseUrl,
            FeatureTogglesRequest.builder().features(features).build(),
            FeatureToggleResponse.class,
            config.getAppName(),
            config.getDeploymentPhase());

        return result.getBody();
    }

    private List<FeatureInfo> processAnnotations ()
    {
        Reflections reflections = new Reflections(config.getBasePackage());
        Set<Class<?>> featuredClasses = reflections.getTypesAnnotatedWith(FeatureToggles.class);
        List<FeatureInfo> allFeatures = new ArrayList<>();
        for (Class<?> fc : featuredClasses) {
            Field[] feature = fc.getFields();

            List<FeatureInfo> featuresInfo = Arrays.stream(feature).map(ann -> ann.getAnnotation(
                Feature.class)).filter(Objects::nonNull).map(this::createFeature).collect(
                Collectors.toList());

            allFeatures.addAll(featuresInfo);

        }
        return allFeatures;
    }

    private FeatureInfo createFeature (Feature feature)
    {
        LocalDate enableOn = null;
        if (!feature.enableOn().isEmpty()) {
            enableOn = LocalDate.parse(feature.enableOn());
        }
        return FeatureInfo.builder().id(feature.id()).name(feature.name()).groupName(
            feature.group()).phase(feature.phase()).enableOn(enableOn).dependsOn(Sets.newHashSet(
            feature.dependsOn())).description(feature.description()).build();
    }

    public FeatureInfo getFeature (String featureId)
    {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = config.getUrl() + "/features/" + featureId
            + "?appName={appName}&phase={phase}";

        ResponseEntity<FeatureInfo> result = restTemplate.getForEntity(baseUrl,
            FeatureInfo.class,
            config.getAppName(),
            config.getDeploymentPhase());

        return result.getBody();
    }

    public <T extends Enum> boolean isFeatureEnabled (T feature)
    {
        try {
            Feature ft = feature.getClass().getField(feature.name()).getAnnotation(Feature.class);
            String featureId = ft.id();
            return getFeature(featureId).isEnabled();
        }
        catch (NoSuchFieldException e) {
            throw new ParseFeatureEnumException(feature.name() + " does not exist", e);
        }
    }
}
