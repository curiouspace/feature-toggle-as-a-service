package org.ft.core.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Set;

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
    private String groupName = "DEFAULT";
    private Phase phase = Phase.DEV;
    private String tenantIdentifier;
    private LocalDate enableOn;
    private Set<String> dependsOn;

    public boolean validate()
    {
        if (StringUtils.isEmpty(getName())
            || StringUtils.isEmpty(getDescription())) {
            return false;
        }
        else {
            return true;
        }
    }

}
