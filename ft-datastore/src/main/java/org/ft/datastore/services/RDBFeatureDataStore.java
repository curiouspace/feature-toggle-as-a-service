package org.ft.datastore.services;

import lombok.AllArgsConstructor;
import org.ft.core.api.model.FeatureInfo;
import org.ft.core.api.model.Phase;
import org.ft.core.api.model.TenantInfo;
import org.ft.core.exceptions.FeatureToggleException;
import org.ft.core.services.FeatureDataStore;
import org.ft.core.services.FeaturePropertyValidator;
import org.ft.core.services.TenantStore;
import org.ft.datastore.models.Feature;
import org.ft.datastore.models.FeatureStatus;
import org.ft.datastore.repository.FeatureStatusRepository;
import org.ft.datastore.repository.FeatureToggleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Prajwal Das
 */
@Service
@AllArgsConstructor
public class RDBFeatureDataStore implements FeatureDataStore
{
    private FeatureToggleRepository featureRepository;

    private FeatureStatusRepository featureStatusRepository;

    private FeaturePropertyValidator featurePropertyValidator;

    private TenantStore tenantStore;

    @Override
    public void enable (String featureId, String tenant)
    {
        featureStatusRepository.updateFeatureStatus(featureId, tenant, true);
    }

    @Override
    public void disable (String featureId, String tenant)
    {
        featureStatusRepository.updateFeatureStatus(featureId, tenant, false);
    }

    @Override
    public Optional<FeatureInfo> getFeature (String featureId, String tenant, Phase phase)
    {
        return Optional.ofNullable(mapper(featureStatusRepository.getFeatureStatusByPhase(
            featureId,
            tenant,
            phase).orElseThrow(() -> FeatureToggleException.FEATURE_NOT_FOUND)));
    }

    @Override
    public List<FeatureInfo> getFeatures (String tenant, Phase phase)
    {
        return featureStatusRepository.getFeatureStatusByTenant(tenant, phase).stream().map(this::mapper).collect(
            Collectors.toList());
    }

    @Override
    public Optional<FeatureInfo> createOrUpdate (FeatureInfo feature)
    {
        if ( !featurePropertyValidator.isValid(feature)) {
            throw FeatureToggleException.APP_NOT_REGISTERED;
        }

        Feature f = createOrUpdateFeature(feature);
        createFeatureStatusForTenants(f);
        return Optional.of(feature);
    }

    @Override
    public List<FeatureInfo> createOrUpdate (List<FeatureInfo> features)
    {
        return features.stream().map(this::createOrUpdate).filter(Optional::isPresent).map(
            Optional::get).collect(Collectors.toList());
    }

    private void createFeatureStatusForTenants(Feature feature) {
        List<TenantInfo> tenants = tenantStore.getAll();
        for (TenantInfo tenant: tenants) {
            createStatusIfMissing(tenant, feature);
        }
    }

    public FeatureStatus createStatusIfMissing (TenantInfo tenant, Feature feature)
    {
        Optional<FeatureStatus> f = featureStatusRepository.getFeatureStatusByTenant(
            feature.getId(),
            tenant.getId());
        return f.orElseGet(() -> {
            FeatureStatus featureStatus = FeatureStatus.builder()
                .enabled(false)
                .feature(feature)
                .tenantId(tenant.getId()).build();
            featureStatus.setActive(true);
            return featureStatusRepository.save(featureStatus);
        });
    }

    private Feature createOrUpdateFeature (FeatureInfo feature)
    {
        Optional<Feature> f = featureRepository.findById(feature.getId());
        Feature fn;
        if(f.isPresent()) {
            fn = f.get();
            fn.setName(feature.getName());
            fn.setDescription(feature.getDescription());
            fn.setGroupName(feature.getGroupName());
            fn.setEnableOn(feature.getEnableOn());
            fn.setDependsOn(feature.getDependsOn());
            fn.setPhase(feature.getPhase());

        }else
        {
            fn = mapper(feature);
            fn.setActive(true);
        }
        return featureRepository.save(fn);
    }

    @Override
    public void delete (String featureId)
    {
        featureRepository.deactivateFeature(featureId);
    }

    private Feature mapper (FeatureInfo feature)
    {
        Feature f = Feature.builder()
            .name(feature.getName())
            .description(feature.getDescription())
            .groupName(feature.getGroupName())
            .phase(feature.getPhase())
            .dependsOn(feature.getDependsOn())
            .enableOn(feature.getEnableOn()).build();
        f.setId(feature.getId());
        return f;
    }

    private FeatureInfo mapper (FeatureStatus featureStatus)
    {
        if(featureStatus == null) {
            return null;
        }
        Feature feature = featureStatus.getFeature();
        return FeatureInfo.builder()
            .id(feature.getId())
            .name(feature.getName())
            .phase(feature.getPhase())
            .description(feature.getDescription())
            .enabled(featureStatus.isEnabled())
            .enableOn(featureStatus.getFeature().getEnableOn())
            .dependsOn(featureStatus.getFeature().getDependsOn())
            .groupName(feature.getGroupName()).build();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void syncFeaturesForTenant (String tenantId) {
        List<Feature> featureList = featureRepository.findAll();
        featureList.forEach( feature -> {
            FeatureStatus featureStatus = FeatureStatus.builder().enabled(false).feature(
                feature).tenantId(tenantId).build();
            featureStatus.setActive(true);
            featureStatusRepository.save(featureStatus);
        });
    }

}
