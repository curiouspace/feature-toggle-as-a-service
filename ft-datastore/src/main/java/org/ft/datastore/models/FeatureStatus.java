package org.ft.datastore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
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
public class FeatureStatus extends BaseEntity implements Serializable
{
    @ManyToOne
    private Feature feature;
    private String tenantIdentifier;
    private boolean enabled;
}
