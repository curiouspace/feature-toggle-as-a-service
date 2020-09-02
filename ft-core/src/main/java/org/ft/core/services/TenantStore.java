package org.ft.core.services;

import org.ft.core.api.model.TenantInfo;

import java.util.List;

public interface TenantStore
{
    void  addTenant(String tenantId , String tenantName);

    List<TenantInfo> getAll ();

    List<String> getAllTenantIds ();


}
