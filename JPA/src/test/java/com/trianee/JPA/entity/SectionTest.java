package com.trianee.JPA.entity;

import com.trianee.JPA.entity.Course;
import com.trianee.JPA.entity.Lecture;
import com.trianee.JPA.entity.Section;
import com.trianee.JPA.repositories.CourseRepository;
import com.trianee.JPA.repositories.LectureRepository;
import com.trianee.JPA.repositories.SectionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class SectionTest {
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @BeforeEach
    public void setUp () {
        sectionRepository.deleteAll();
        courseRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCreateSection () {
        Section section = Section.builder()
                .name("Section Title")
                .sectionOrder("1")
                .build();
        sectionRepository.save(section);
        assertEquals(1, sectionRepository.count());
    }

    @Test
    @Transactional
    public void testCreateSectionWithCourse () {
        Course course = Course.builder()
                .name("Course Title")
                .build();
        courseRepository.save(course);
        Section section = Section.builder()
                .name("Section Title")
                .sectionOrder("1")
                .course(course)
                .build();
        sectionRepository.save(section);
        assertEquals(1, sectionRepository.count());
        assertEquals(course, sectionRepository.findById(section.getId()).get().getCourse());
    }

    @Test
    @Transactional
    public void testCreateSectionWithCourseAndLectures () {
        Course course = Course.builder()
                .name("Course Title")
                .build();
        courseRepository.save(course);
        Lecture lecture = Lecture.builder()
                .name("Lecture Title")
                .build();
        lectureRepository.save(lecture);
        Section section = Section.builder()
                .name("Section Title")
                .sectionOrder("1")
                .course(course)
                .lectures(List.of(lecture))
                .build();
        sectionRepository.save(section);
        assertEquals(1, sectionRepository.count());
        assertEquals(course, sectionRepository.findById(section.getId()).get().getCourse());
        assertEquals(lecture, sectionRepository.findById(section.getId()).get().getLectures().get(0));
    }

}