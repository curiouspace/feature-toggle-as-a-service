package org.ft.datastore.repository;

import org.ft.datastore.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author Prajwal Das
 */
@Repository
public interface FeatureToggleRepository extends JpaRepository<Feature, String>
{

    @Modifying
    @Transactional
    @Query("update FeatureStatus f set f.enabled = :enabled where f.feature.id in (select fe.id from Feature fe where fe.id = :featureId) and f.tenantIdentifier = :tenantIdentifier")
    void updateFeatureStatus (String featureId, String tenantIdentifier, Boolean enabled);

    @Modifying
    @Transactional
    @Query("update Feature f set f.active = false where f.id = :featureId")
    void deactivateFeature (String featureId);
}
