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

package org.ft.jira.integration.client;

import lombok.AllArgsConstructor;
import org.ft.core.api.model.FeatureInfo;
import org.ft.core.services.FeatureDataStore;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JiraFeaturesSyncJob
{
    FeatureToggleJiraIntegration featureToggleIntegration;

    FeatureDataStore featureDataStore;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void activate ()
    {
        List<FeatureInfo> jiraFeatures = featureToggleIntegration.getAllFeaturesFromJira();
        if(jiraFeatures != null && !jiraFeatures.isEmpty()) {
            featureDataStore.createOrUpdate(jiraFeatures);
        }
    }
}
