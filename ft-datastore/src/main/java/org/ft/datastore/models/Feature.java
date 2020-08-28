package org.ft.datastore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ft.core.api.model.Phase;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

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
    private LocalDate enableOn;

    @Enumerated(EnumType.ORDINAL)
    private Phase phase;
    private boolean enabled;

    @ManyToOne
    private App app;
}
