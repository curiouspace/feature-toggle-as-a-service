package org.ft.core.services;

import org.ft.core.api.model.AppInfo;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajwal Das
 */
public interface AppDataStore
{

    Optional<AppInfo> getApp(String appName);

    List<AppInfo> getApps();

    Optional<AppInfo> create(AppInfo app);

    Optional<AppInfo> update(AppInfo app);

    Optional<AppInfo> delete(String appName);
}
