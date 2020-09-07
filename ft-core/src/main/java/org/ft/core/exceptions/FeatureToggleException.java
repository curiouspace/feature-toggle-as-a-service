package org.ft.core.exceptions;

/**
 * @author Prajwal Das
 */
public class FeatureToggleException extends RuntimeException
{
    public FeatureToggleException (String message)
    {
        super(message);
    }

    public static final FeatureToggleException FEATURE_NOT_FOUND = new FeatureToggleException(
        "Feature does not exist");

    public static final FeatureToggleException ENABLEMENT_FAILED = new FeatureToggleException(
        "Invalid id or invalid state ");

}
