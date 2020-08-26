package org.ft.server.service;

import org.ft.core.models.ClientApp;
import org.ft.core.models.FeatureToggle;
import org.ft.core.request.FeatureTogglesRequest;
import org.ft.core.exceptions.FeatureToggleException;
import org.ft.server.repository.FeatureToggleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Prajwal Das
 */
@Service
@AllArgsConstructor
public class FeatureToggleService
{
    private FeatureToggleRepository ftRepository;

    private ClientAppService clientAppService;

    public List<FeatureToggle> createOrUpdateFeatureToggles (FeatureTogglesRequest request,
                                                             String appName) {
        if(appName == null || appName.isEmpty()) {
            throw FeatureToggleException.APP_NOT_REGISTERED;
        }
        ClientApp clientApp = clientAppService.getAppDetails(appName);

        List<FeatureToggle> dbFt = getAllFeatureToggle(appName);
        List<FeatureToggle> featureToggles = request.getFeatureToggles();
        featureToggles.forEach(d -> d.setAppName(clientApp));
        if(dbFt == null || dbFt.isEmpty()) {
            return ftRepository.saveAll(featureToggles);
        } else {
            Map<String, FeatureToggle> ft = dbFt.stream().collect(Collectors.toMap(
                FeatureToggle::getName,
                d -> d));
            List<FeatureToggle> newFt = ftRepository.saveAll(featureToggles.stream().filter(
                d -> ft.get(d.getName()) == null).collect(Collectors.toList()));
            dbFt.addAll(newFt);
            return dbFt;
        }
    }

    public FeatureToggle getFeatureToggle(String name, String appName) {
        return ftRepository.findByNameAndAppNameName(name, appName);
    }

    public List<FeatureToggle> getAllFeatureToggle(String appName) {
        return ftRepository.findByAppNameName(appName);
    }

    public FeatureToggle createOrUpdateFeatureToggle (FeatureToggle featureToggle,
                                                      String appName)
    {
        if(appName == null || appName.isEmpty()) {
            throw FeatureToggleException.APP_NOT_REGISTERED;
        }
        if(featureToggle.getId() != 0) {
            Optional<FeatureToggle> ft = ftRepository.findById(featureToggle.getId());
            if(ft.isPresent()) {
                FeatureToggle fe = ft.get();
                fe.setDescription(featureToggle.getDescription());
                fe.setAppName(featureToggle.getAppName());
                fe.setName(featureToggle.getName());
                fe.setEnabled(featureToggle.isEnabled());
                featureToggle = fe;
            }
        }

        return ftRepository.save(featureToggle);
    }
}
