package com.trianee.JPA.repositories;


import com.trianee.JPA.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer>, JpaSpecificationExecutor<Author> {
    @Transactional
    List<Author> findAllByNameQuery(int age);

    @Transactional
    @Modifying
    int updateAllAgeByNameQuery(int age);
    @Transactional
    List<Author> findAll();
    @Modifying
    @Transactional
    @Query("UPDATE Author a SET a.age = :age WHERE a.id = :id")
    int updateAuthorAge(int age, int id);
    @Transactional
    List<Author> findAllByFirstName(String firstName);
    @Transactional
    List<Author> findAllByFirstNameIgnoreCase(String firstName);

    @Transactional
    List<Author> findAllByFirstNameContainingIgnoreCase(String firstName);
    @Transactional
    List<Author> findAllByFirstNameStartingWithIgnoreCase(String firstName);
    @Transactional
    List<Author> findAllByFirstNameEndingWithIgnoreCase(String firstName);
    @Transactional
    List<Author> findAllByFirstNameInIgnoreCase(List<String> firstNames);

    @Transactional
    Author findByEmail(String email);
    @Transactional
    List<Author> findAllByAge (int i);
}
