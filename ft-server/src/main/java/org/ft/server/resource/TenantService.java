package org.ft.server.resource;

import lombok.AllArgsConstructor;
import org.ft.core.api.model.TenantInfo;
import org.ft.core.response.TenantResponse;
import org.ft.datastore.services.RDBTenantStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@AllArgsConstructor
@RequestMapping("/tenants")
public class TenantService
{
    private RDBTenantStore rdbTenantStore;

    @PostMapping
    public TenantResponse createTenant (@RequestBody TenantInfo tenantInfo)
    {
        rdbTenantStore.addTenant(tenantInfo);
        return TenantResponse.builder().tenantInfoList(Collections.singletonList(tenantInfo)).build();
    }

    @GetMapping
    public TenantResponse getAllTenants ()
    {
        return TenantResponse.builder().tenantInfoList(rdbTenantStore.getAll()).build();
    }
}
