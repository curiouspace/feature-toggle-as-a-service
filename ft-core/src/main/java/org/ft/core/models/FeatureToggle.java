package org.ft.core.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author Prajwal Das
 */
@Data
@Builder
@Entity
@NoArgsConstructor
public class FeatureToggle implements Serializable
{
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private boolean enabled;

    @ManyToOne
    private ClientApp appName;
    private String description;
}
