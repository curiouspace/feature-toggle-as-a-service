package org.ft.client.service;

import org.ft.client.annotations.Feature;
import org.ft.client.exception.ParsingFeatureEnumFailed;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Prajwal Das
 */
@AllArgsConstructor
@Component
public class FeatureToggleHelper
{
    private FeatureToggleService fts;

    private static FeatureToggleService featureToggleService;

    @PostConstruct
    private void init ()
    {
        FeatureToggleHelper.featureToggleService = this.fts;
    }

    public static <T extends Enum> boolean isFeatureEnabled (T feature)
    {
        try {
            Feature ft = feature.getClass().getField(feature.name()).getAnnotation(
                Feature.class);
            String toggleName = ft.name();
            return featureToggleService.getFeatureToggle(toggleName).isEnabled();
        }
        catch (NoSuchFieldException e) {
            throw new ParsingFeatureEnumFailed(feature.name() + " does not exist", e);
        }
    }
}
