package com.example.demo.Student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") String studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") String studentId, @RequestBody Student student){
        studentService.updateStudent(studentId, student);
    }

}
