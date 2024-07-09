package com.trianee.JPA.entity;

import com.trianee.JPA.repositories.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class ResourceTest {

    @Autowired
    private ResourceRepository resourceRepository;

    @BeforeEach
    public void setUp () {
        resourceRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCreateResource () {
        // Create a new Resource
        Resource resource = Resource.builder()
                .name("Sample Resource")
                .size(1024)
                .url("https://example.com/resource")
                .build();

        // Save the Resource to the database
        resourceRepository.save(resource);

        // Retrieve the Resource from the database and assert its properties
        Resource savedResource = resourceRepository.findById(resource.getId()).orElse(null);
        assertNotNull(savedResource);
        assertEquals("Sample Resource", savedResource.getName());
        assertEquals(1024, savedResource.getSize());
        assertEquals("https://example.com/resource", savedResource.getUrl());
    }
}
