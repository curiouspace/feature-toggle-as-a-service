package org.ft.datastore.repository;

import org.ft.core.api.model.Phase;
import org.ft.datastore.models.FeatureStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajwal Das
 */
@Repository
public interface FeatureStatusRepository extends JpaRepository<FeatureStatus, Long>
{
    @Query("Select f from FeatureStatus f where f.feature.id = :featureId and f.tenantIdentifier = :tenantIdentifier and f.feature.phase >= :phase")
    Optional<FeatureStatus> getFeatureStatusByPhase (String featureId, String tenantIdentifier, Phase phase);

    @Query("Select f from FeatureStatus f where f.feature.id = :featureId and f.tenantIdentifier = :tenantIdentifier")
    Optional<FeatureStatus> getFeatureStatusByTenant (String featureId, String tenantIdentifier);

    @Query("Select f from FeatureStatus f where f.tenantIdentifier = :tenantIdentifier and f.feature.phase >= :phase order by f.feature.id")
    List<FeatureStatus> getFeatureStatusByTenant (String tenantIdentifier, Phase phase);

    @Query("Select distinct f.tenantIdentifier from FeatureStatus f where f.active = true order by f.tenantIdentifier")
    List<String> getAllTenantIdentifiers ();
}
