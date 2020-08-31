package org.ft.core.services;

import org.ft.core.api.model.FeatureInfo;
import org.springframework.stereotype.Component;

@Component
public class FeaturePropertyValidator
{
    public boolean isValid (FeatureInfo featureInfo)
    {
        return featureInfo.validate();
    }
}
