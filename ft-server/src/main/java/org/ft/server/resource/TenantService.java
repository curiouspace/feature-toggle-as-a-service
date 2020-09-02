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
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tenants")
public class TenantService
{
    private RDBTenantStore rdbTenantStore;

    @PostMapping
    public TenantResponse createTenant (@RequestBody TenantInfo tenantInfo)
    {
        rdbTenantStore.addTenant(tenantInfo.getId(), tenantInfo.getName());
        return new TenantResponse(Collections.singletonList(tenantInfo));
    }

    @GetMapping
    public TenantResponse getAll ()
    {
        List<TenantInfo> tenantInfoList = rdbTenantStore.getAll();
        return new TenantResponse(tenantInfoList);
    }
}
