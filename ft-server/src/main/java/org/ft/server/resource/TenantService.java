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
