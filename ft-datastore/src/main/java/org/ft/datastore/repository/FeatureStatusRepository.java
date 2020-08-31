package org.ft.datastore.repository;

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
    @Query("Select f from FeatureStatus f where f.feature.name = :featureName and f.tenantIdentifier = :tenantIdentifier")
    Optional<FeatureStatus> getFeatureStatus (String featureName, String tenantIdentifier);

    @Query("Select f from FeatureStatus f where f.tenantIdentifier = :tenantIdentifier order by f.feature.name")
    List<FeatureStatus> getFeatureStatus (String tenantIdentifier);

    @Query("Select distinct f.tenantIdentifier from FeatureStatus f where f.active = true order by f.tenantIdentifier")
    List<String> getAllTenantIdentifiers ();
}
