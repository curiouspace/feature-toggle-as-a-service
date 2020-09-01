package org.ft.jira.integration.client;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import io.atlassian.util.concurrent.Promise;
import lombok.AllArgsConstructor;
import org.ft.core.services.FeatureToggleServiceIntegration;
import org.ft.jira.integration.config.FeatureJiraProperties;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * @author Prajwal Das
 */
@Service
@AllArgsConstructor
public class FeatureToggleJiraIntegration implements FeatureToggleServiceIntegration
{
    private FeatureJiraProperties featureJiraProperties;

    public Issue getIssue (String issueKey) throws URISyntaxException
    {
        final URI jiraServerUri = new URI(featureJiraProperties.getUrl());
        final JiraRestClient restClient = new AsynchronousJiraRestClientFactory().createWithBasicHttpAuthentication(
            jiraServerUri,
            featureJiraProperties.getUsername(),
            featureJiraProperties.getPassword());
        Promise issuePromise = restClient.getIssueClient().getIssue(issueKey);
        return Optional.ofNullable((Issue)issuePromise.claim()).orElseThrow(() -> new RuntimeException(
            "No such issue"));
    }
}
