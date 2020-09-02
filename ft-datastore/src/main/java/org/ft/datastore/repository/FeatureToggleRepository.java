package org.ft.datastore.repository;

import org.ft.core.api.model.Phase;
import org.ft.datastore.models.Feature;
import org.ft.datastore.models.FeatureStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Prajwal Das
 */
@Repository
public interface FeatureToggleRepository extends JpaRepository<Feature, String>
{

    @Query("Select f from Feature f and f.tenantIdentifier = :tenantIdentifier and f.enableOn = :enableOn")
    List<Feature> getFeatureByActivationDate (String tenantIdentifier, LocalDate enableOn);

    @Modifying
    @Transactional
    @Query("update Feature f set f.active = false where f.id = :featureId")
    void deactivateFeature (String featureId);
}
