package org.ft.core.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Prajwal Das
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response implements Serializable
{
    private String errorMsg;
    private int statusCode;
}
