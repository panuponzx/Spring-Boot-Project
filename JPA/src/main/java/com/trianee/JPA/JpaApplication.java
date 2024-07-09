package com.trianee.JPA;

import com.github.javafaker.Faker;
import com.trianee.JPA.entity.Author;
import com.trianee.JPA.specification.AuthorSpecification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.trianee.JPA.repositories.AuthorRepository;
import org.springframework.data.jpa.domain.Specification;


@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(
//
//			AuthorRepository authorRepository) {
//		return args -> {
//            for (int i = 0; i < 10; i++) {
//                Faker faker = new Faker();
//                var author = Author.builder()
//                        .firstName(faker.name().firstName())
//                        .lastName(faker.name().lastName())
//                        .email(faker.name().username()+ "panupon@gmail.com" )
//                        .age(faker.number().numberBetween(19, 50))
//                        .build();
//				authorRepository.save(author);
//            }
//
//            // Update author with id = 1
//			var author = Author.builder()
//					.id(1)
//					.firstName("Panupon")
//					.lastName("Bas")
//					.email("panupon@gmail.com")
//					.age(22)
//					.build();
////			authorRepository.save(author);
//
//			// update Author
////			authorRepository.updateAuthor(22, 1);
//
//			// update all authors
////			authorRepository.updateAllAuthor(99);
//
//			//find by named query
//
////			authorRepository.findByNamedQuery(70)
////					.forEach(System.out.println);
//			//update with named query
//
////			authorRepository.updateByNameQuery(70)
////					.forEach(System.out.println);
//
//			Specification<Author> spec = Specification
//					.where(AuthorSpecification.hasAge(22))
//					.and(AuthorSpecification.firstNameLike("Panupon"));
//					authorRepository.findAll(spec)
//							.forEach(System.out::println);
// 		};
//	}
}
