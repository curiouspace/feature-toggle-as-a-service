package org.ft.datastore.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.ft.core.api.model.FeatureInfo;
import org.ft.core.util.FeaturePropertyValidator;
import org.ft.datastore.models.Feature;
import org.ft.datastore.models.FeatureStatus;
import org.ft.datastore.repository.FeatureStatusRepository;
import org.ft.datastore.repository.FeatureToggleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FeaturePropertyValidatorImpl implements FeaturePropertyValidator
{
    private FeatureStatusRepository featureStatusRepository;

    private FeatureToggleRepository featureToggleRepository;

    public boolean isValid (FeatureInfo featureInfo)
    {
        return featureInfo.validate();
    }

    @Override
    public boolean validateDependencyForFeatureEnablement (String featureId,
                                                           String tenant)
    {

        Optional<Feature> feature = featureToggleRepository.findById(featureId);
        if (!feature.isPresent()) {
            return false;
        }

        Set<String> featureIdList = feature.get().getDependsOn();
        if (featureIdList != null) {
            for (String it : featureIdList) {
                Optional<FeatureStatus> featureStatus = featureStatusRepository.getFeatureStatusByTenant(
                    it, tenant);
                if (featureStatus.isPresent() && !featureStatus.get().isEnabled()) {
                    return false;
                }
            }

        }

        return true;

    }
}
