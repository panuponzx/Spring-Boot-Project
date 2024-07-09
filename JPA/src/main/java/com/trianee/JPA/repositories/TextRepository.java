package com.trianee.JPA.repositories;


import com.trianee.JPA.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<Text, Integer> {
}
