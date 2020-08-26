package org.ft.datastore.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

/**
 * @author Prajwal Das
 */
@Configuration
@EnableConfigurationProperties(FeatureDataStoreProperties.class)
@ComponentScan("org.ft.datastore")
@EntityScan("org.ft.datastore")
@EnableJpaRepositories("org.ft.datastore")
public class FeatureDataStoreConfig
{
    @Bean
    @Primary
    @ConfigurationProperties("spring.feature-toggle.datasource")
    public DataSourceProperties dataSourceProperties ()
    {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.feature-toggle.datasource")
    public DataSource memberDataSource ()
    {
        return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
}
