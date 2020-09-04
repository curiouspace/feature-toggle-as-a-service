package org.ft.datastore.repository;

import org.ft.datastore.models.Tenant;
import org.ft.datastore.services.RDBFeatureDataStore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;

public class TenantEntityListener
{
    @Autowired
    private RDBFeatureDataStore featureDataStore;

    @PostPersist
    public void postPersist (Object tenant)
    {
        AutowireHelper.autowire(this, this.featureDataStore);
        featureDataStore.syncFeaturesForTenant(((Tenant)tenant).getId());
    }
}
