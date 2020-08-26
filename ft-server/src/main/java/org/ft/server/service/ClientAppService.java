package org.ft.server.service;

import lombok.AllArgsConstructor;
import org.ft.core.models.ClientApp;
import org.ft.server.exceptions.FeatureToggleException;
import org.ft.server.repository.ClientAppRepository;
import org.springframework.stereotype.Service;

/**
 * @author Prajwal Das
 */
@Service
@AllArgsConstructor
public class ClientAppService
{
    private ClientAppRepository clientAppRepository;

    public ClientApp getAppDetails (String appName)
    {
        if (appName == null || appName.isEmpty()) {
            throw FeatureToggleException.APP_NOT_REGISTERED;
        }
        return clientAppRepository.findByName(appName).orElseGet(() -> clientAppRepository.save(
            ClientApp.builder().name(appName).build()));
    }
}
