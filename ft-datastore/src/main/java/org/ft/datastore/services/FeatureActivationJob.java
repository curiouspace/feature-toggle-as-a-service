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

package org.ft.datastore.services;

import lombok.AllArgsConstructor;
import org.ft.core.services.FeatureActivator;
import org.ft.core.services.FeatureDataStore;
import org.ft.datastore.repository.FeatureToggleRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FeatureActivationJob implements FeatureActivator
{
    private final FeatureToggleRepository featureToggleRepository;

    private final RDBTenantStore rdbTenantStore;

    private final FeatureDataStore featureDataStore;

    @Scheduled(cron = "0 0 7,19 * * ?")
    public void activate ()
    {
        LocalDate localDate = LocalDate.now();
        List<String> featureList = featureToggleRepository.getFeatureIdsByActivationDate(
            localDate);
        featureDataStore.enableForAll(featureList);
    }
}
