package com.trianee.JPA.entity;


import com.trianee.JPA.entity.Author;
import com.trianee.JPA.entity.Course;
import com.trianee.JPA.entity.Section;
import com.trianee.JPA.repositories.AuthorRepository;
import com.trianee.JPA.repositories.CourseRepository;
import com.trianee.JPA.repositories.SectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @BeforeEach
    public void setUp () {
        courseRepository.deleteAll();
        authorRepository.deleteAll();
        Author author = Author.builder()
                .firstName("Author")
                .lastName("Author")
                .email("author@gmail.com")
                .build();
        authorRepository.save(author);
    }


    @Test
    @Transactional
    public void testCreateCourse () {
        Course course = Course.builder()
                .name("Course Title")
                .build();

        courseRepository.save(course);
        assertEquals(1, courseRepository.count());
    }

    @Test
    @Transactional
    public void testCreateCourseWithAuthor () {
        Course course = Course.builder()
                .name("Course Title")
                .build();
        List<Author> authors = authorRepository.findAll();
        course.setAuthors(authors);

        courseRepository.save(course);
        assertEquals(1, courseRepository.count());
        assertEquals(authors, courseRepository.findAll().get(0).getAuthors());
    }

    @Test
    @Transactional
    public void testCreateCourseWithAuthorAndSection () {
        Course course = Course.builder()
                .name("Course Title")
                .build();
        List<Author> authors = authorRepository.findAll();
        Section section = Section.builder()
                .name("Section 1")
                .build();
        section.setCourse(course);
        sectionRepository.save(section);
        course.setSections(List.of(section));
        course.setAuthors(authors);

        courseRepository.save(course);
        assertEquals(1, courseRepository.count());
        assertEquals(authors, courseRepository.findAll().get(0).getAuthors());
        assertEquals(List.of(section), courseRepository.findAll().get(0).getSections());
    }
}
