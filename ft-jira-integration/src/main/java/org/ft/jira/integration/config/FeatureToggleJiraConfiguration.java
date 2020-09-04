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
