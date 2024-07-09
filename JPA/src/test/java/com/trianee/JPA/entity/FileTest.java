package com.trianee.JPA.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.trianee.JPA.entity.File;
import com.trianee.JPA.entity.Lecture;
import com.trianee.JPA.repositories.FileRepository;
import com.trianee.JPA.repositories.LectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class FileTest {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private LectureRepository lectureRepository;

    @BeforeEach
    public void setUp () {
        fileRepository.deleteAll();
        lectureRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCreateFile () {
        File file = File.builder()
                .name("file1")
                .type("pdf")
                .size(100)
                .url("http://localhost:8080/files/file1")
                .build();
        fileRepository.save(file);
        File fileInDb = fileRepository.findById(file.getId()).get();
        assertEquals(file, fileInDb);
    }

    @Test
    @Transactional
    public void testDeleteFile () {
        File file = File.builder()
                .name("file1")
                .type("pdf")
                .size(100)
                .url("http://localhost:8080/files/file1")
                .build();
        fileRepository.save(file);
        fileRepository.deleteById(file.getId());
        assertEquals(0, fileRepository.count());
    }

    @Test
    @Transactional
    public void testUpdateFile () {
        File file = File.builder()
                .name("file1")
                .type("pdf")
                .size(100)
                .url("http://localhost:8080/files/file1")
                .build();
        fileRepository.save(file);
        file.setName("file2");
        fileRepository.save(file);
        File fileInDb = fileRepository.findById(file.getId()).get();
        assertEquals("file2", fileInDb.getName());
    }

    @Test
    @Transactional
    public void testCreateFileWithLectureShouldFoundOnDbAndFileCanGetLecture () {
        File file = File.builder()
                .name("file1")
                .type("pdf")
                .size(100)
                .url("http://localhost:8080/files/file1")
                .build();
        Lecture lecture = Lecture.builder()
                .name("lecture1")
                .resource(file)
                .build();
        file.setLecture(lecture);
        lecture.setResource(file);
        fileRepository.save(file);
        lectureRepository.save(lecture);
        File fileInDb = fileRepository.findById(file.getId()).get();
        Lecture lectureInDb = lectureRepository.findById(lecture.getId()).get();
        assertEquals(lecture, fileInDb.getLecture());
        assertEquals(file, lectureInDb.getResource());
    }
}