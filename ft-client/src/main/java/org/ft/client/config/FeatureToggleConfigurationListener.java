package org.ft.client.config;

import lombok.AllArgsConstructor;
import org.ft.client.service.FeatureToggleClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Prajwal Das
 */
@Component
@AllArgsConstructor
public class FeatureToggleConfigurationListener implements ApplicationListener<ContextRefreshedEvent>
{
    private FeatureToggleClient featureToggleClient;

    @Override
    public void onApplicationEvent (ContextRefreshedEvent contextRefreshedEvent)
    {
        featureToggleClient.registerAppAndFeatures();
    }
}
