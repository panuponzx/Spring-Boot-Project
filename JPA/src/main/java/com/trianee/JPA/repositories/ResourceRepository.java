package com.trianee.JPA.repositories;

import com.trianee.JPA.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}

