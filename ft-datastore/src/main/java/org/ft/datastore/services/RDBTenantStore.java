/*
 *    Copyright 2019-2030 codecuriosity
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.ft.datastore.services;

import lombok.AllArgsConstructor;
import org.ft.core.api.model.TenantInfo;
import org.ft.core.services.TenantStore;
import org.ft.datastore.models.Tenant;
import org.ft.datastore.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RDBTenantStore implements TenantStore
{
    private TenantRepository tenantRepository;

    @Override
    public void addTenant (TenantInfo tenantInfo)
    {
        Tenant tenant = mapper(tenantInfo);
        tenant.setActive(true);
        tenantRepository.save(tenant);
    }

    @Override
    public List<TenantInfo> getAll ()
    {
        List<Tenant> tenantList = tenantRepository.findAll();
        return tenantList.stream().map(this::mapper).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllTenantIds ()
    {
        return tenantRepository.getAllTenantIds();
    }


    public TenantInfo mapper (Tenant tenant)
    {
        return TenantInfo.builder().id(tenant.getId()).name(tenant.getName()).build();
    }

    public Tenant mapper (TenantInfo tenant)
    {
        return Tenant.builder().id(tenant.getId()).name(tenant.getName()).build();
    }
}
