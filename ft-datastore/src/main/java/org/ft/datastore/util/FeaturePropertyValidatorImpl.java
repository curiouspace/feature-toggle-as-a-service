/*
 *    Copyright 2019-2030 codecuriosity
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
