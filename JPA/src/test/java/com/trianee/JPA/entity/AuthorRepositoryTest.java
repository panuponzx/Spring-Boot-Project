package com.trianee.JPA.entity;

import com.trianee.JPA.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp () {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setEmail("john.doe@example.com");
        author.setAge(30);
        authorRepository.save(author);
    }

    @Test
    @Transactional
    public void testFindAllByNameQuery () {
        // Create a new Author
        Author author = new Author();
        author.setAge(30);
        author.setEmail("test@gmail.com");
        author.setFirstName("John");

        authorRepository.save(author);

        List<Author> authors = authorRepository.findAllByAge(30);

        assertFalse(authors.isEmpty(), "List of authors should not be empty");

        assertEquals("John", authors.get(0).getFirstName(), "First author's first name should be John");
    }

    @Test
    @Transactional
    void testSaveAuthor () {
        Author newAuthor = new Author("Bas", "Panupon", "Baspanupon@example.com", 34, null);
        newAuthor.setCourses(List.of(new Course("Course 1", "Description 1"), new Course("Course 2", "Description 2")));
        Author savedAuthor = authorRepository.save(newAuthor);
        assertNotNull(savedAuthor.getId());
        assertEquals("Bas", savedAuthor.getFirstName());
    }

    //
    @Test
    @Transactional
    void testReadAuthor () {
        Optional<Author> optionalAuthor = authorRepository.findById(1);
        assertTrue(optionalAuthor.isPresent(), "Author with ID 1 not found");

        Author foundAuthor = optionalAuthor.get();
        assertEquals("John", foundAuthor.getFirstName());
    }

    @Test
    @Transactional
    void testDeleteAuthor () {
        authorRepository.deleteById(1);
        assertFalse(authorRepository.findById(1).isPresent());
    }

    @Test
    @Transactional
    void testEqualsAndHashCode () {
        Author author1 = new Author("Bas", "Panupon", "Baspanupon@example.com", 34, null);
        Author author2 = new Author("Bas", "Panupon", "Baspanupon@example.com", 34, null);

        assertEquals(author1, author2);
        assertEquals(author1.hashCode(), author2.hashCode());
    }
}

