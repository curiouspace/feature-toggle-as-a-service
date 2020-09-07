package org.ft.core.util;

import org.ft.core.api.model.FeatureInfo;


public interface FeaturePropertyValidator
{
    boolean isValid (FeatureInfo featureInfo);

    boolean validateDependencyForFeatureEnablement (String featureId, String tenant);
}
