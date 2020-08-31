package org.ft.core.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

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
    private String groupName = "DEFAULT";
    private Phase phase = Phase.DEV;
    private String appName;
    private String tenantIdentifier;
    private LocalDate enableOn;

    public boolean validate()
    {
        if (StringUtils.isEmpty(getName())
            || StringUtils.isEmpty(getDescription())
            || StringUtils.isEmpty(getAppName()) ) {
            return false;
        }
        else {
            return true;
        }
    }

}
