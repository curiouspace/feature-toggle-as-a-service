package org.ft.server.config;

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
@ConfigurationProperties(prefix = "spring.feature-toggle.server")
public class FeatureServerProperties
{
    private Phase deploymentPhase = Phase.DEV;
}