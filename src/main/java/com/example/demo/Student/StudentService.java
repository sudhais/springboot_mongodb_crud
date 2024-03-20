package com.example.demo.Student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
         studentRepository.findStudentByEmail(student.getEmail())
                .ifPresentOrElse(student1 -> {
                    System.out.println(student1 + "already exist");
                    throw new IllegalStateException("already email exists");
                }, () -> {
                    studentRepository.insert(student);
                    System.out.println("Inserted" + student);
                });
    }

    public void deleteStudent(String studentId) {
        if(!studentRepository.existsById(studentId))
            throw new IllegalStateException("Id does not exists");
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(String studentId, Student student) {
        Student student1 = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("id does not exists"));
        if(student.getFirstName() != null &&
                student.getFirstName().length() > 0 &&
                !student.getFirstName().equals(student1.getFirstName())){
            student1.setFirstName(student.getFirstName());
        }

        if(student.getLastName() != null &&
                student.getLastName().length() > 0 &&
                !student.getLastName().equals(student1.getLastName())){
            student1.setLastName(student.getLastName());
        }

        if(student.getEmail() != null &&
                student.getEmail().length() > 0 &&
                !student.getEmail().equals(student1.getEmail())){

            studentRepository.findStudentByEmail(student.getEmail())
                    .ifPresentOrElse(s -> {
                        System.out.println(s + "already exist");
                        throw new IllegalStateException("already email exists");
                    }, () -> {
                        student1.setEmail(student.getEmail());
                    });


        }

        System.out.println("Updated");
        studentRepository.save(student1);


    }
}
