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
                .phase(getPhase(issue)).build()
            ).collect(Collectors.toList());
    }

    private Phase getPhase(Issue issue) {
        switch (issue.getStatus().getName()) {
        case "Done":
            return Phase.PROD;
        default:
            return Phase.DEV;
        }
    }
}
