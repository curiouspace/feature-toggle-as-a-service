package org.ft.client.exception;

/**
 * @author Prajwal Das
 */
public class ParsingFeatureEnumFailed extends RuntimeException
{
    public ParsingFeatureEnumFailed (String message)
    {
        super(message);
    }

    public ParsingFeatureEnumFailed (String message, Throwable cause)
    {
        super(message, cause);
    }

    public ParsingFeatureEnumFailed (Throwable cause)
    {
        super(cause);
    }
}
