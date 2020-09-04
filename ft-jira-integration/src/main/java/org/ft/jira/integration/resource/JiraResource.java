package org.ft.jira.integration.resource;

import lombok.AllArgsConstructor;
import org.ft.core.api.model.FeatureInfo;
import org.ft.jira.integration.client.FeatureToggleJiraIntegration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Prajwal Das
 */
@RestController
@AllArgsConstructor
@RequestMapping("/jira")
public class JiraResource
{
    FeatureToggleJiraIntegration featuresJiraClientIntegration;

    @GetMapping("/features")
    public List<FeatureInfo> getJiraIssue() throws URISyntaxException
    {
        return featuresJiraClientIntegration.getAllFeaturesFromJira();
    }
}
