package com.example.demo.students;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    private final static List<Student> STUDENTS = Arrays.asList(
            new Student(1,"Ali"),
            new Student(2,"Fatma"),
            new Student(3,"Kadir")
    );


    @GetMapping("")
    public List<Student> getAllStudents() {
        return  STUDENTS;
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println(student);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println(studentId);
    }

    @PutMapping("{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId,@RequestBody Student student) {
        System.out.println("Student: " + student);
    }

}
