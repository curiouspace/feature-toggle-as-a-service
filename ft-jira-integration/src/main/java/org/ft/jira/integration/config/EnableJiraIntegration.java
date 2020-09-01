package org.ft.jira.integration.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Prajwal Das
 */
@Import({ FeatureToggleJiraConfiguration.class})
@Target({ ElementType.TYPE})
@Retention(RUNTIME)
public @interface EnableJiraIntegration
{
}
