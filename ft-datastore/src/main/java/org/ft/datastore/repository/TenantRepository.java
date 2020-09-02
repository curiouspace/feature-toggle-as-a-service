package org.ft.datastore.repository;

import org.ft.datastore.models.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityListeners;
import java.util.List;

@Repository
@EntityListeners({ TenantEntityListener.class })
public interface TenantRepository extends JpaRepository<Tenant, Long>
{

    @Query("Select distinct f.id from Tenant f where f.active = true")
    List<String> getAllTenantIdentifiers ();

}
