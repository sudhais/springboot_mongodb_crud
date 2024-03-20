package com.example.demo;

import com.example.demo.Student.Address;
import com.example.demo.Student.Gender;
import com.example.demo.Student.Student;
import com.example.demo.Student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class SpringbootMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongodbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			Address address = new Address("Sri Lanka", "puttalam", "96425");
			Student student = new Student(
					"jamila",
					"ahmed",
					"jimila@gmail.com",
					Gender.MALE,
					address,
					List.of("computer science"),
					BigDecimal.TEN
			);
//			usingMongoTemplateAndQuery(repository, mongoTemplate, student);

//			usingMongoRepository(repository, student);
		};

	}

	private static void usingMongoRepository(StudentRepository repository, Student student) {
		repository.findStudentByEmail(student.getEmail())
				.ifPresentOrElse(s -> {
					System.out.println(s + "already exist");
				}, ()->{
					repository.insert(student);
					System.out.println("inserted");
				});
	}

	private static void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is("jimila@gmail.com"));
		List<Student> students = mongoTemplate.find(query, Student.class);

		if(students.size() > 1)
			throw new IllegalStateException("many email exists");
		if(students.isEmpty()){
			repository.insert(student);
			System.out.println("inserted");
		}else
			System.out.println("already exist");
	}

}
