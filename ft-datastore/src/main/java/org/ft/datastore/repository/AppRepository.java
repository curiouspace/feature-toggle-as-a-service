package org.ft.datastore.repository;

import org.ft.datastore.models.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Prajwal Das
 */
@Repository
public interface AppRepository extends JpaRepository<App, Long>
{
    Optional<App> findByName (String name);

    @Modifying
    @Transactional
    @Query("update App a set a.active = false where a.name = :appName")
    Optional<App> deactivateApp (String appName);
}
