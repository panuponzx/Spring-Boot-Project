package com.trianee.JPA.entity;

import com.trianee.JPA.entity.Lecture;
import com.trianee.JPA.entity.Video;
import com.trianee.JPA.repositories.LectureRepository;
import com.trianee.JPA.repositories.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class VideoTest {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private LectureRepository lectureRepository;

    @BeforeEach
    public void setUp () {
        videoRepository.deleteAll();
        lectureRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCreateVideo () {
        Video video = Video.builder()
                .name("Video Title")
                .url("https://www.youtube.com/watch?v=12345")
                .size(1000)
                .length(60)
                .build();
        videoRepository.save(video);
        assertEquals(video, videoRepository.findAll().get(0));
    }

    @Test
    @Transactional
    public void testCreateVideoWithLecture () {
        Video video = Video.builder()
                .name("Video Title")
                .url("https://www.youtube.com/watch?v=12345")
                .size(1000)
                .length(60)
                .build();
        Lecture lecture = Lecture.builder()
                .name("Lecture Title")
                .resource(video)
                .build();
        video.setLecture(lecture);
        lectureRepository.save(lecture);
        videoRepository.save(video);
        Video videoFromDb = videoRepository.findAll().get(0);
        Lecture lectureFromDb = lectureRepository.findAll().get(0);
        assertEquals(video, videoFromDb);
        assertEquals(lecture, videoFromDb.getLecture());
        assertEquals(video, lectureFromDb.getResource());

    }

}