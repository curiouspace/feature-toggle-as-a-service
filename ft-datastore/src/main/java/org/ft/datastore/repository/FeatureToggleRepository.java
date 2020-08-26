package org.ft.datastore.repository;

import org.ft.datastore.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Prajwal Das
 */
@Repository
public interface FeatureToggleRepository extends JpaRepository<Feature, Long>
{

    Optional<Feature> findByName (String featureName);

    @Modifying
    @Query("update FeatureStatus f set f.enabled = :enabled where f.feature.name = :featureName and f.tenantIdentifier = :tenantIdentifier")
    void updateFeatureStatus (String featureName, String tenantIdentifier, Boolean enabled);

    @Modifying
    @Query("update Feature f set f.active = false where f.name = :featureName")
    void deactivateFeature (String featureName);
}
