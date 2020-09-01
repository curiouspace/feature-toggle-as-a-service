package org.ft.jira.integration.resource;

import com.atlassian.jira.rest.client.api.domain.Issue;
import lombok.AllArgsConstructor;
import org.ft.jira.integration.client.FeatureToggleJiraIntegration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * @author Prajwal Das
 */
@RestController
@AllArgsConstructor
@RequestMapping("/jira")
public class JiraResource
{
    FeatureToggleJiraIntegration featuresJiraClientIntegration;

    @GetMapping("/{jiraId}")
    public Issue getJiraIssue(@PathVariable String jiraId) throws URISyntaxException
    {
        return featuresJiraClientIntegration.getIssue(jiraId);
    }
}
