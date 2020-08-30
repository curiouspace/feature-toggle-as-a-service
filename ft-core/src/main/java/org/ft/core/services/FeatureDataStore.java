package org.ft.core.services;

import org.ft.core.api.model.FeatureInfo;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajwal Das
 */
public interface FeatureDataStore
{
    void enable(String featureName, String tenant);

    void disable(String featureName, String tenant);

    Optional<FeatureInfo> getFeature(String featureName, String tenant);

    List<FeatureInfo> getFeatures(String tenant);

    Optional<FeatureInfo> create(FeatureInfo feature);

    List<FeatureInfo> createOrUpdate (List<FeatureInfo> features);

    Optional<FeatureInfo> update(FeatureInfo feature);

    void delete(String featureName);

    List<String> getAllTenantsIdentifiers ();
}
