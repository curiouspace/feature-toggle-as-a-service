package org.ft.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Prajwal Das
 */
@Data
@ConfigurationProperties(prefix = "spring.feature-toggle")
public class FeatureProperties
{
    private String url;
    private String packageScan;
    private String appName;
}