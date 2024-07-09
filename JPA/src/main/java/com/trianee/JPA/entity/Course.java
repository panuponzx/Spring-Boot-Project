package com.trianee.JPA.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@SuperBuilder
public class Course extends BaseEntity{

    private String name = "name";

    private String description = "description";

    @ManyToMany(mappedBy = "courses")
    private List<Author> authors;

    @OneToMany
    private List<Section> sections;

    public Course (String name, String description) {
    }
}
