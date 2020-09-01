package org.ft.client.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ft.core.api.model.Phase;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Prajwal Das
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.feature-toggle.client")
public class FeatureClientProperties
{
    private String url;
    private String packageScan;
    private String appName;
    private Phase deploymentPhase = Phase.DEV;
}