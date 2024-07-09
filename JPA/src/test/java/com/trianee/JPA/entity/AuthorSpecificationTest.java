package com.trianee.JPA.entity;

import com.trianee.JPA.entity.Author;
import com.trianee.JPA.exceptions.BaseException;
import com.trianee.JPA.repositories.AuthorRepository;
import com.trianee.JPA.service.AuthorService;
import com.trianee.JPA.specification.AuthorSpecification;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class AuthorSpecificationTest {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    public void setUp () throws BaseException {
        authorRepository.deleteAll();
        authorService.create(
                AuthorData.FIRST_NAME,
                AuthorData.LAST_NAME,
                AuthorData.EMAIL,
                AuthorData.CREATE_BY,
                AuthorData.AGE
        );
    }

    @Test
    @Transactional
    public void testQuerySpecificationHasAge () {
        Specification<Author> spec = Specification.where(
                AuthorSpecification.hasAge(AuthorData.AGE)
        );
        List<Author> authors = authorRepository.findAll(spec);

        assertEquals(1, authors.size());
    }

    @Test
    @Transactional
    public void testQuerySpecificationHasAgeNotFound () {
        Specification<Author> spec = Specification.where(
                AuthorSpecification.hasAge(AuthorData.AGE + 2)
        );
        List<Author> authors = authorRepository.findAll(spec);

        assertEquals(0, authors.size());
    }

    @Test
    @Transactional
    public void testQuerySpecificationFirstNameLike () {
        Specification<Author> spec = Specification.where(
                AuthorSpecification.firstNameLike(AuthorData.FIRST_NAME)
        );
        List<Author> authors = authorRepository.findAll(spec);

        assertEquals(1, authors.size());
    }

    @Test
    @Transactional
    public void testQuerySpecificationFirstNameLikeNotFound () {
        Specification<Author> spec = Specification.where(
                AuthorSpecification.firstNameLike(AuthorData.FIRST_NAME + "a")
        );
        List<Author> authors = authorRepository.findAll(spec);

        assertEquals(0, authors.size());
    }

    @Test
    @Transactional
    public void testQuerySpecificationFirstNameLikeAndHasAgeFound () {
        Specification<Author> spec = Specification.where(
                AuthorSpecification.firstNameLike(AuthorData.FIRST_NAME).and(AuthorSpecification.hasAge(AuthorData.AGE))
        );
        List<Author> authors = authorRepository.findAll(spec);

        assertEquals(1, authors.size());
    }

    @Test
    @Transactional
    public void testQuerySpecificationFirstNameLikeAndHasAgeNotFound () {
        Specification<Author> spec = Specification.where(
                AuthorSpecification.firstNameLike(AuthorData.FIRST_NAME + "A").and(AuthorSpecification.hasAge(AuthorData.AGE))
        );
        List<Author> authors = authorRepository.findAll(spec);

        assertEquals(0, authors.size());
    }

    @Test
    @Transactional
    public void testQuerySpecificationFirstNameLikeOrHasAgeFound () {
        Specification<Author> spec = Specification.where(
                AuthorSpecification.firstNameLike(AuthorData.FIRST_NAME + "A").or(AuthorSpecification.hasAge(AuthorData.AGE))
        );
        List<Author> authors = authorRepository.findAll(spec);

        assertEquals(1, authors.size());
    }

    interface AuthorData {
        Faker faker = new Faker();
        String FIRST_NAME = faker.name().firstName();
        String LAST_NAME = faker.name().lastName();
        String EMAIL = faker.internet().emailAddress();
        String CREATE_BY = faker.name().fullName();
        int AGE = faker.number().numberBetween(19, 50);
    }
}
