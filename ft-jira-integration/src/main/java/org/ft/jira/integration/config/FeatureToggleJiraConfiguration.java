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

package org.ft.jira.integration.config;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Prajwal Das
 */
@Configuration
@Builder
@ComponentScan("org.ft.jira")
@AllArgsConstructor
@EnableConfigurationProperties(FeatureJiraProperties.class)
public class FeatureToggleJiraConfiguration
{
    private FeatureJiraProperties featureJiraProperties;

    @Bean
    AsynchronousJiraRestClientFactory asynchronousJiraRestClientFactory() {
        return new AsynchronousJiraRestClientFactory();
    }

    @Bean
    JiraRestClient jiraRestClient() throws URISyntaxException
    {
        final URI jiraServerUri = new URI(featureJiraProperties.getUrl());
        final JiraRestClient jiraClient = asynchronousJiraRestClientFactory().createWithBasicHttpAuthentication(
            jiraServerUri,
            featureJiraProperties.getUsername(),
            featureJiraProperties.getPassword());

        return jiraClient;
    }
}
