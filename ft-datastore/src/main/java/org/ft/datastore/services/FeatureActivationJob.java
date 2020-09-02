package org.ft.datastore.services;

import org.ft.core.services.FeatureActivator;
import org.ft.core.services.FeatureDataStore;
import org.ft.datastore.models.Feature;
import org.ft.datastore.repository.FeatureToggleRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeatureActivationJob implements FeatureActivator
{
    private FeatureToggleRepository featureToggleRepository;

    private RDBTenantStore rdbTenantStore;

    private FeatureDataStore featureDataStore;

    @Scheduled(cron = "0 0 7,19 * * ?")
    public void activate ()
    {
        LocalDate localDate = LocalDate.now();
        List<String> allTenantIds = rdbTenantStore.getAllTenantIds();
        allTenantIds.forEach(it ->{
            List<Feature> featureList = featureToggleRepository.getFeatureByActivationDate(it,localDate);
            for (Feature f : featureList) {
                featureDataStore.enable(f.getId(), it);
            }

        });

    }
}
