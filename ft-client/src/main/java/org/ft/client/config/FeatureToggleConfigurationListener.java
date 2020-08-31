package org.ft.client.config;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.ft.client.annotations.Feature;
import org.ft.client.annotations.FeatureToggles;
import org.ft.client.service.FeatureToggleService;
import org.ft.core.api.model.FeatureInfo;
import org.ft.core.services.TenantIdentifierService;
import org.reflections.Reflections;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Prajwal Das
 */
@Component
@AllArgsConstructor
public class FeatureToggleConfigurationListener implements ApplicationListener<ContextRefreshedEvent>
{
    private FeatureProperties props;

    private FeatureToggleService featureToggleService;

    private TenantIdentifierService tenantIdentifierService;

    @Override
    public void onApplicationEvent (ContextRefreshedEvent contextRefreshedEvent)
    {
        Reflections reflections = new Reflections(props.getPackageScan());
        Set<Class<?>> featuredClasses = reflections.getTypesAnnotatedWith(FeatureToggles.class);
        List<FeatureInfo> allFeatures = new ArrayList<>();
        List<String> tenants = tenantIdentifierService.getTenantIdentifiers();
        for (Class<?> fc : featuredClasses) {
            Field[] feature = fc.getFields();

            for (int i = 0; i < feature.length; i++) {
                Feature ann = feature[i].getAnnotation(Feature.class);
                if (ann != null) {
                    allFeatures.addAll(createFeature(ann, tenants));
                }
            }
        }
        featureToggleService.registerAppAndFeatureToggles(allFeatures);
    }

    private List<FeatureInfo> createFeature (Feature feature, List<String> tenants)
    {
        return tenants.stream().map(tenant -> {
            LocalDate enableOn = null;
            if(!feature.enableOn().isEmpty()) {
                enableOn = LocalDate.parse(feature.enableOn());
            }
            return FeatureInfo.builder()
                .id(feature.id())
                .name(feature.name())
                .groupName(feature.group())
                .phase(feature.phase())
                .tenantIdentifier(tenant)
                .enableOn(enableOn)
                .dependsOn(Sets.newHashSet(feature.dependsOn()))
                .description(feature.description()).build();
        })
            .collect(Collectors.toList());
    }
}
