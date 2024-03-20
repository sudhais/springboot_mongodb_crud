package com.example.demo.Student;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favouriteSubject;
    private BigDecimal totalSpendInBooks;


    public Student(String firstName,
                   String lastName,
                   String email,
                   Gender gender,
                   Address address,
                   List<String> favouriteSubject,
                   BigDecimal totalSpendInBooks
                   ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favouriteSubject = favouriteSubject;
        this.totalSpendInBooks = totalSpendInBooks;
    }
}
