package org.ft.datastore.services;

import lombok.AllArgsConstructor;
import org.ft.core.api.model.AppInfo;
import org.ft.core.exceptions.FeatureToggleException;
import org.ft.core.services.AppDataStore;
import org.ft.datastore.models.App;
import org.ft.datastore.repository.AppRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Prajwal Das
 */
@Service
@AllArgsConstructor
public class RDBAppDataStore implements AppDataStore
{
    private AppRepository appRepository;

    @Override
    public Optional<AppInfo> getApp (String appName)
    {
        Optional<App> app = appRepository.findByName(appName);
        return Optional.ofNullable(mapper(app.orElseThrow(() -> FeatureToggleException.APP_NOT_REGISTERED)));
    }

    public App createIfMissing(String appName) {
        Optional<App> app = appRepository.findByName(appName);
        return app.orElseGet(() -> {
            App a = App.builder().name(appName).build();
            a.setActive(true);
            return appRepository.save(a);
        });
    }

    @Override
    public List<AppInfo> getApps ()
    {
        return appRepository.findAll().stream().map(this::mapper).collect(Collectors.toList());
    }

    @Override
    public Optional<AppInfo> create (AppInfo app)
    {
        App mapper = mapper(app);
        mapper.setActive(true);
        return Optional.ofNullable(mapper(appRepository.save(mapper)));
    }

    @Override
    public Optional<AppInfo> update (AppInfo app)
    {
        if (app == null || app.getId() == 0) {
            throw FeatureToggleException.APP_NOT_REGISTERED;
        }
        return create(app);
    }

    @Override
    public Optional<AppInfo> delete (String appName)
    {
        return Optional.ofNullable(mapper(appRepository.deactivateApp(appName).orElseThrow(
            () -> FeatureToggleException.APP_NOT_REGISTERED)));
    }

    public AppInfo mapper (App app)
    {

        return AppInfo.builder().name(app.getName()).id(app.getId()).build();
    }

    public App mapper (AppInfo app)
    {

        App build = App.builder().name(app.getName()).build();
        build.setId(app.getId());
        return build;
    }
}
