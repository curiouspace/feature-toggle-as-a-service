/*
 *    Copyright 2019-2030 codecuriosity
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

/**
 * @author Prajwal Das
 */
@Configuration
@EnableConfigurationProperties(FeatureDataStoreProperties.class)
@ComponentScan("org.ft")
@EntityScan("org.ft.datastore")
@EnableJpaRepositories("org.ft.datastore")
@EnableScheduling
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
