package org.ft.server.repository;

import org.ft.core.models.FeatureToggle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Prajwal Das
 */
@Repository
public interface FeatureToggleRepository extends JpaRepository<FeatureToggle, Long>
{
    FeatureToggle findByName(String name);

    FeatureToggle findByNameAndAppNameName(String name, String appName);

    List<FeatureToggle> findByAppNameName (String appName);
}
