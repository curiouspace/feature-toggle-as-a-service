package org.ft.core.services;

import java.util.List;

/**
 * @author Prajwal Das
 */
public interface TenantIdentifierService
{
    String DEFAULT= "DEFAULT";

    List<String> getTenantIdentifiers();
}
