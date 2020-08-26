package org.ft.core.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Prajwal Das
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppInfo implements Serializable
{
    private long id;
    private String name;
}
