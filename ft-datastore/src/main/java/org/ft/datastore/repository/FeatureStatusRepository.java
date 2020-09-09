package org.ft.datastore.repository;

import org.ft.core.api.model.Phase;
import org.ft.datastore.models.FeatureStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Prajwal Das
 */
@Repository
public interface FeatureStatusRepository extends JpaRepository<FeatureStatus, Long>
{
    @Query("Select f from FeatureStatus f where f.feature.id = :featureId and f.tenantId = :tenantId and f.feature.phase >= :phase")
    Optional<FeatureStatus> getFeatureStatusByPhase (String featureId, String tenantId, Phase phase);

    @Query("Select f from FeatureStatus f where f.feature.id = :featureId and f.tenantId = :tenantId")
    Optional<FeatureStatus> getFeatureStatusByTenant (String featureId, String tenantId);

    @Query("Select f from FeatureStatus f where f.tenantId = :tenantId and f.feature.phase >= :phase order by f.feature.id")
    List<FeatureStatus> getFeatureStatusByTenant (String tenantId, Phase phase);

    @Modifying
    @Transactional
    @Query(value = "update feature_status set enabled = :enabled where feature_id = :featureId and tenant_id = :tenantId", nativeQuery = true)
    void updateFeatureStatus (String featureId, String tenantId, Boolean enabled);

    @Modifying
    @Transactional
    @Query(value = "update feature_status set access_count = access_count+1 where feature.id = :featureId and tenant_id = :tenantId ", nativeQuery = true)
    void updateFeatureFetchCount (String featureId, String tenantId);

    @Modifying
    @Transactional
    @Query(value = "update feature_status set enabled = :enabled where feature.id in :featureIds", nativeQuery = true)
    void updateFeatureStatusForAll (List<String> featureIds, Boolean enabled);

    @Query("Select f from FeatureStatus f where f.feature.id IN (:featureIdList)and f.tenantId = :tenantId")
    List<FeatureStatus> getFeatureStatus (List<String> featureIdList, String tenantId);
}
