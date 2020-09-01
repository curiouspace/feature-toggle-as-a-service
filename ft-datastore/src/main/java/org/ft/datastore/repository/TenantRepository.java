package org.ft.datastore.repository;

import org.ft.datastore.models.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityListeners;

@Repository
@EntityListeners({ TenantEntityListener.class })
public interface TenantRepository extends JpaRepository<Tenant, Long>
{

}
