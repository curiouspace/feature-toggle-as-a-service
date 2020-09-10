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

package org.ft.core.services;

import org.ft.core.api.model.FeatureInfo;
import org.ft.core.api.model.Phase;
import org.ft.core.response.FeatureToggleResponse;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajwal Das
 */
public interface FeatureDataStore
{
    void enable (String featureId, String tenant);

    void disable (String featureId, String tenant);

    void enableForAll (List<String> featureIds);

    void disableForAll (List<String> featureIds);

    Optional<FeatureInfo> getFeature (String featureId, String tenant, Phase phase);

    List<FeatureInfo> getFeatures (String tenant, Phase phase);

    FeatureToggleResponse createOrUpdate (FeatureInfo feature);

    FeatureToggleResponse createOrUpdate (List<FeatureInfo> features);

    void delete (String featureId);

    void syncFeaturesForTenant (String tenantId);

    void updateFetchCount(String featureId, String tenantId);
}
