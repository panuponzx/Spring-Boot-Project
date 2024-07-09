package com.trianee.JPA.repositories;

import com.trianee.JPA.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}