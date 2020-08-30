package org.ft.core.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Prajwal Das
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeatureInfo
{
    private String id;
    private String name;
    private boolean enabled;
    private String description;
    private String groupName;
    private Phase phase;
    private String appName;
    private String tenantIdentifier;
    private LocalDate enableOn;
}
