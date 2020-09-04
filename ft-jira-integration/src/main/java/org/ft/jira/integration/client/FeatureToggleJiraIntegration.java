package org.ft.jira.integration.client;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import io.atlassian.util.concurrent.Promise;
import lombok.AllArgsConstructor;
import org.ft.core.api.model.FeatureInfo;
import org.ft.core.api.model.Phase;
import org.ft.core.services.FeatureToggleIntegration;
import org.ft.jira.integration.config.FeatureJiraProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prajwal Das
 */
@Service
@AllArgsConstructor
public class FeatureToggleJiraIntegration extends FeatureToggleIntegration
{
    private JiraRestClient jiraClient;

    private FeatureJiraProperties props;

    public List<FeatureInfo> getAllFeaturesFromJira ()
    {
        Promise<SearchResult> promise = jiraClient.getSearchClient().searchJql("issuetype=" + props.getIssueType());
        SearchResult searchResult = promise.claim();
        ArrayList<Issue> issues = (ArrayList<Issue>)searchResult.getIssues();
        return issues.stream().map(issue ->
            FeatureInfo.builder()
                .id(issue.getKey())
                .name(issue.getSummary())
                .groupName(issue.getProject().getKey())
                .description(issue.getDescription())
                .phase(Phase.DEV).build()
            ).collect(Collectors.toList());
    }
}
