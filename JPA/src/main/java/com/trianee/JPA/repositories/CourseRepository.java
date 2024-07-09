package com.trianee.JPA.repositories;


import com.trianee.JPA.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}