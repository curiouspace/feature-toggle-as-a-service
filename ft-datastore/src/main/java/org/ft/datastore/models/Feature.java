package org.ft.datastore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ft.core.api.model.Phase;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author Prajwal Das
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Feature extends BaseEntity implements Serializable
{
    private String name;
    private String description;
    private String groupName;
    @Enumerated
    private Phase phase;
    private boolean enabled;

    @ManyToOne
    private App app;
}
