package org.ft.core.services;

import org.ft.core.api.model.FeatureInfo;
import org.ft.core.api.model.Phase;
import org.ft.core.response.FeatureToggleResponse;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajwal Das
 */
public interface FeatureDataStore
{
    void enable (String featureId, String tenant);

    void disable (String featureId, String tenant);

    void enableForAll (List<String> featureIds);

    void disableForAll (List<String> featureIds);

    Optional<FeatureInfo> getFeature (String featureId, String tenant, Phase phase);

    List<FeatureInfo> getFeatures (String tenant, Phase phase);

    FeatureToggleResponse createOrUpdate (FeatureInfo feature);

    FeatureToggleResponse createOrUpdate (List<FeatureInfo> features);

    void delete (String featureId);

    void syncFeaturesForTenant (String tenantId);
}
