package org.ft.client.service;

import org.ft.core.services.TenantIdentifierService;

import java.util.Arrays;
import java.util.List;

/**
 * @author Prajwal Das
 */
public class DefaultTenantIdentifierService implements TenantIdentifierService
{
    @Override
    public List<String> getTenantIdentifiers ()
    {
        return Arrays.asList("DEFAULT");
    }
}
