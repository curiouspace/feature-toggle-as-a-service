package org.ft.datastore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
public class IdEntity implements Serializable
{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private boolean active;
}
