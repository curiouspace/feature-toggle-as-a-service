package org.ft.datastore.services;

import lombok.AllArgsConstructor;
import org.ft.core.api.model.TenantInfo;
import org.ft.core.services.TenantStore;
import org.ft.datastore.models.Tenant;
import org.ft.datastore.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RDBTenantStore implements TenantStore
{
    private TenantRepository tenantRepository;

    @Override
    public void addTenant (String tenantId, String tenantName)
    {
        tenantRepository.save(new Tenant(tenantId, tenantName));
    }

    @Override
    public List<TenantInfo> getAll ()
    {
        List<Tenant> tenantList = tenantRepository.findAll();
        List<TenantInfo> tenantInfoList = new ArrayList<>();
        tenantList.forEach(it -> {
            tenantInfoList.add(new TenantInfo(it.getId(), it.getName()));
        });

        return tenantInfoList;
    }

}
