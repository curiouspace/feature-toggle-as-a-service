/*
 *    Copyright 2019-2030 codecuriosity
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.ft.datastore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ft.core.api.model.Phase;

import javax.persistence.Column;
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

    @Column(columnDefinition = "TEXT")
    private String description;
    private String groupName;
    private LocalDate enableOn;

    @Enumerated(EnumType.ORDINAL)
    private Phase phase;

    @ElementCollection
    private Set<String> dependsOn;
}
