package org.ft.datastore.repository;

import org.ft.datastore.models.Feature;
import org.ft.datastore.models.FeatureStatus;
import org.ft.datastore.models.Tenant;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;
import java.util.List;

public class TenantEntityListener
{
    @Autowired
    private FeatureToggleRepository featureToggleRepository;

    @Autowired
    private FeatureStatusRepository featureStatusRepository;

    @PostPersist
    public void prePersist (Object object)
    {
        // See https://guylabs.ch/2014/02/22/autowiring-pring-beans-in-hibernate-jpa-entity-listeners/
        AutowireHelper.autowire(this, this.featureToggleRepository);

        // See https://guylabs.ch/2014/02/22/autowiring-pring-beans-in-hibernate-jpa-entity-listeners/
        AutowireHelper.autowire(this, this.featureStatusRepository);

        Tenant tenant = (Tenant)object;

        List<Feature> featureList =featureToggleRepository.findAll();
        featureList.forEach( feature -> {
            FeatureStatus featureStatus = FeatureStatus.builder().enabled(false).feature(
                feature).tenantIdentifier(tenant.getId()).build();
            featureStatus.setActive(true);
            featureStatusRepository.save(featureStatus);

        });

    }

}
