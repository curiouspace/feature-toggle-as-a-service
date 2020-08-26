package org.ft.client.config;

import org.ft.client.annotations.Feature;
import org.ft.client.annotations.FeatureToggles;
import org.ft.client.service.FeatureToggleService;
import org.ft.core.models.ClientApp;
import org.ft.core.models.FeatureToggle;
import lombok.AllArgsConstructor;
import org.reflections.Reflections;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Prajwal Das
 */
@Component
@AllArgsConstructor
public class FeatureToggleConfigurationListener implements ApplicationListener<ContextRefreshedEvent>
{
    private FeatureToggleConfigProperties props;

    private FeatureToggleService featureToggleService;

    @Override
    public void onApplicationEvent (ContextRefreshedEvent contextRefreshedEvent)
    {
        Reflections reflections = new Reflections(props.getPackageScan());
        Set<Class<?>> featuredClasses = reflections.getTypesAnnotatedWith(FeatureToggles.class);
        List<FeatureToggle> allFeatures = new ArrayList<>();
        for (Class<?> fc : featuredClasses) {
            Field[] feature = fc.getFields();

            for (int i = 0; i < feature.length; i++) {
                Feature ann = feature[i].getAnnotation(Feature.class);
                if (ann != null) {
                    allFeatures.add(createFeature(ann));
                }
            }
        }
        featureToggleService.registerAppAndFeatureToggles(allFeatures);
    }

    private FeatureToggle createFeature(Feature feature)
    {
        return FeatureToggle.builder()
            .name(feature.name())
            .enabled(feature.value())
            .appName(ClientApp.builder().name(props.getAppName()).build())
            .description(feature.description()).build();
    }
}
