package org.ft.datastore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author Prajwal Das
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class App extends BaseEntity implements Serializable
{
    private String name;
}
