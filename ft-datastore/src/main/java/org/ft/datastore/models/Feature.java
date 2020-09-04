package org.ft.datastore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ft.core.api.model.Phase;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

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
    @Id
    private String id;
    private String name;
    private String description;
    private String groupName;
    private LocalDate enableOn;

    @Enumerated(EnumType.ORDINAL)
    private Phase phase;

    @ElementCollection
    private Set<String> dependsOn;
}
