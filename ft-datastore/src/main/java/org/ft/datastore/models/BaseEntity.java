package org.ft.datastore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Prajwal Das
 */
@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class BaseEntity implements Serializable
{
    @Id
    @GeneratedValue
    private long id;

    private boolean active;
}
