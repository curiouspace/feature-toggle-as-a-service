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
