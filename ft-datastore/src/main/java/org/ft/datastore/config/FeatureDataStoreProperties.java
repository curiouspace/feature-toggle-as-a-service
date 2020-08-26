package org.ft.datastore.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Prajwal Das
 */
@Data
@ConfigurationProperties(prefix = "spring.feature-toggle.datasource")
public class FeatureDataStoreProperties
{
    private String url;
    private String username;
    private String password;
}