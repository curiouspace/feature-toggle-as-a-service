package org.ft.client.annotations;

import org.ft.core.api.model.Phase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Prajwal Das
 */
@Target({ ElementType.FIELD })
@Retention(RUNTIME)
public @interface Feature
{
    String id ();

    String name ();

    String description () default "";

    String group () default "";

    Phase phase () default Phase.DEV;

    String enableOn () default "";

    String[] dependsOn () default {};
}
