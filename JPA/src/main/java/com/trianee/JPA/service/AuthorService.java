package com.trianee.JPA.service;

import com.trianee.JPA.entity.Author;
import com.trianee.JPA.exceptions.AuthorException;
import com.trianee.JPA.exceptions.BaseException;
import com.trianee.JPA.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void create(String firstName, String lastName, String email, String createdBy, int age) throws BaseException {
        if (firstName == null || firstName.isEmpty()) {
            throw AuthorException.createFirstNameNull();
        }
        if (email == null) {
            throw AuthorException.createEmailNull();
        }
        if (lastName == null) {
            throw AuthorException.createLastNameNull();
        }
        if (createdBy == null) {
            throw AuthorException.createCreateByNull();
        }
        if (age < 0) {
            throw AuthorException.createAgeNull();
        }

        Author author = Author.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .createdBy(createdBy)
                .age(age)
                .build();
        Author checkAuthor = authorRepository.findByEmail(email);
        if (checkAuthor != null) {
            throw AuthorException.createEmailExists();
        }
        authorRepository.save(author);
    }
}