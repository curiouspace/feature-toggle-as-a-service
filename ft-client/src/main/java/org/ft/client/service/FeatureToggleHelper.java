package org.ft.client.service;

import lombok.AllArgsConstructor;
import org.ft.client.annotations.Feature;
import org.ft.client.exception.ParseFeatureEnumException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Prajwal Das
 */
@AllArgsConstructor
@Component
public class FeatureToggleHelper
{
    private FeatureToggleClient fts;

    private static FeatureToggleClient featureToggleClient;

    @PostConstruct
    private void init ()
    {
        FeatureToggleHelper.featureToggleClient = this.fts;
    }

    public static <T extends Enum> boolean isFeatureEnabled (T feature)
    {
        try {
            Feature ft = feature.getClass().getField(feature.name()).getAnnotation(
                Feature.class);
            String featureId = ft.id();
            return featureToggleClient.getFeature(featureId).isEnabled();
        }
        catch (NoSuchFieldException e) {
            throw new ParseFeatureEnumException(feature.name() + " does not exist", e);
        }
    }
}
