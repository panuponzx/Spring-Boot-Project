package com.trianee.JPA.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@NamedQueries({
        @NamedQuery(
                name = "Author.findAllByNameQuery",
                query = "SELECT a FROM Author a WHERE a.age = :age"
        ),
        @NamedQuery(
                name = "Author.updateAllAgeByNameQuery",
                query = "UPDATE Author a SET a.age = :age"

        )
})

public class Author extends BaseEntity {
    @Column(
            name = "first_Name",
            length = 100
    )
    private String firstName;

    @Column(
            name = "last_Name",
            length = 100
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            unique = true,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "age",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private int age;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "author_courses",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

}
