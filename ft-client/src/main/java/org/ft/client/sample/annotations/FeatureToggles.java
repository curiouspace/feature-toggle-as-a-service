package org.ft.client.sample.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Prajwal Das
 */
@Target({ ElementType.TYPE})
@Retention(RUNTIME)
public @interface FeatureToggles
{
}
