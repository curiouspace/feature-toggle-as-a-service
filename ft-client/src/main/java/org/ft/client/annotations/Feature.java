package org.ft.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Prajwal Das
 */
@Target({ ElementType.FIELD})
@Retention(RUNTIME)
public @interface Feature
{
    String description () default "";
    String name () default "";
    boolean value () default false;
}
