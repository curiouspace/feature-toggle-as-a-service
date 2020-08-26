package org.ft.server.repository;

import org.ft.core.models.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Prajwal Das
 */
@Repository
public interface ClientAppRepository extends JpaRepository<ClientApp, Long>
{
    Optional<ClientApp> findByName(String name);
}
