package org.ft.core.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Prajwal Das
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeatureInfo
{
    private long id;
    private String name;
    private boolean enabled;
    private String description;
    private String groupName;
    private Phase phase;
    private String appName;
    private String tenantIdentifier;
}
