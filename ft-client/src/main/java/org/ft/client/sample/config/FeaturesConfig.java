package org.ft.client.sample.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ft.core.api.model.Phase;

/**
 * @author Prajwal Das
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeaturesConfig
{
    private String url;
    private String basePackage;
    private String appName;
    private Phase deploymentPhase = Phase.DEV;
}