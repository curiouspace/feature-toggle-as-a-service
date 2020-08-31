package org.ft.datastore.services;

import lombok.AllArgsConstructor;
import org.ft.core.api.model.FeatureInfo;
import org.ft.core.exceptions.FeatureToggleException;
import org.ft.core.services.FeatureDataStore;
import org.ft.core.services.FeaturePropertyValidator;
import org.ft.datastore.models.App;
import org.ft.datastore.models.Feature;
import org.ft.datastore.models.FeatureStatus;
import org.ft.datastore.repository.FeatureStatusRepository;
import org.ft.datastore.repository.FeatureToggleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private RDBAppDataStore rdbAppDataStore;

    @Autowired
    private FeaturePropertyValidator featurePropertyValidator;

    @Override
    public void enable (String featureName, String tenant)
    {
        featureRepository.updateFeatureStatus(featureName, tenant, true);
    }

    @Override
    public void disable (String featureName, String tenant)
    {
        featureRepository.updateFeatureStatus(featureName, tenant, false);
    }

    @Override
    public Optional<FeatureInfo> getFeature (String featureName, String tenant)
    {
        return Optional.ofNullable(mapper(featureStatusRepository.getFeatureStatus(
            featureName,
            tenant).orElseThrow(() -> FeatureToggleException.FEATURE_NOT_FOUND)));
    }

    @Override
    public List<FeatureInfo> getFeatures (String tenant)
    {
        return featureStatusRepository.getFeatureStatus(tenant).stream().map(this::mapper).collect(
            Collectors.toList());
    }

    @Override
    public Optional<FeatureInfo> createOrUpdate (FeatureInfo feature)
    {
        if ( !featurePropertyValidator.isValid(feature)) {
            throw FeatureToggleException.APP_NOT_REGISTERED;
        }

        App app = rdbAppDataStore.createIfMissing(feature.getAppName());
        Feature f = createOrUpdateFeature(feature, app);
        FeatureStatus featureStatus = createStatusIfMissing(feature, f);
        return Optional.ofNullable(mapper(featureStatus));
    }

    @Override
    public List<FeatureInfo> createOrUpdate (List<FeatureInfo> features)
    {
        return features.stream().map(this::createOrUpdate).filter(Optional::isPresent).map(
            Optional::get).collect(Collectors.toList());
    }

    @Override
    public Optional<FeatureInfo> update (FeatureInfo feature)
    {
        if (feature.getId() != null && !feature.getId().isEmpty()) {
            throw FeatureToggleException.FEATURE_NOT_FOUND;
        }
        return createOrUpdate(feature);
    }

    public FeatureStatus createStatusIfMissing (FeatureInfo info, Feature feature)
    {
        Optional<FeatureStatus> f = featureStatusRepository.getFeatureStatus(feature.getName(), info.getTenantIdentifier());
        return f.orElseGet(() -> {
            FeatureStatus featureStatus = FeatureStatus.builder().enabled(info.isEnabled()).feature(
                feature).tenantIdentifier(info.getTenantIdentifier()).build();
            featureStatus.setActive(true);
            return featureStatusRepository.save(featureStatus);
        });
    }

    private Feature createOrUpdateFeature (FeatureInfo feature, App app)
    {
        Optional<Feature> f = featureRepository.findByName(feature.getName());
        if(f.isPresent())
        {
            Feature featureToUpdate = f.get();
            featureToUpdate.setApp(app);
            featureToUpdate.setName(feature.getName());
            featureToUpdate.setGroupName(feature.getGroupName());
            featureToUpdate.setEnableOn(feature.getEnableOn());
            featureToUpdate.setDescription(feature.getDescription());
            featureToUpdate.setPhase(feature.getPhase());
            return featureRepository.save(featureToUpdate);

        }else
        {
            Feature fn = mapper(feature, app);
            fn.setActive(true);
            return featureRepository.save(fn);
        }

    }

    @Override
    public void delete (String featureName)
    {
        featureRepository.deactivateFeature(featureName);
    }

    @Override
    public List<String> getAllTenantsIdentifiers ()
    {
        return featureStatusRepository.getAllTenantIdentifiers();
    }

    private Feature mapper (FeatureInfo feature, App app)
    {
        Feature f = Feature.builder()
            .name(feature.getName())
            .description(feature.getDescription())
            .enabled(feature.isEnabled())
            .groupName(feature.getGroupName())
            .phase(feature.getPhase())
            .enableOn(feature.getEnableOn())
            .app(app).build();
        f.setId(feature.getId());
        return f;
    }

    private FeatureInfo mapper (FeatureStatus featureStatus)
    {
        Feature feature = featureStatus.getFeature();
        return FeatureInfo.builder()
            .id(feature.getId())
            .name(feature.getName())
            .phase(feature.getPhase())
            .appName(feature.getApp().getName())
            .description(feature.getDescription())
            .enabled(featureStatus.isEnabled())
            .tenantIdentifier(featureStatus.getTenantIdentifier())
            .enableOn(featureStatus.getFeature().getEnableOn())
            .groupName(feature.getGroupName()).build();
    }
}
