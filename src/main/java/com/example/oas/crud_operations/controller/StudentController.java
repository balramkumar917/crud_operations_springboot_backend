package com.example.oas.crud_operations.controller;

import com.example.oas.crud_operations.exception.StudentNotFoundException;
import com.example.oas.crud_operations.model.Student;
import com.example.oas.crud_operations.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("/student")
    Student newStudent(@RequestBody Student newStudent){
        return studentRepo.save(newStudent);
    }

    @GetMapping("/students")
    List<Student> getAllStudents(){
        return studentRepo.findAll();
    }
    @GetMapping("/students/{id}")
    Student getStudentById(@PathVariable Long id){
        return studentRepo.findById(id)
                .orElseThrow(()-> new StudentNotFoundException(id));
    }

    @PutMapping("/students/{id}")
    Student updateStudent(@PathVariable Long id,@RequestBody Student newStudent){
    return studentRepo.findById(id)
            .map(student -> {
                student.setUsername(newStudent.getUsername());
                student.setEmail(newStudent.getEmail());
                student.setName(newStudent.getName());
                return studentRepo.save(student);
            }).orElseThrow(()-> new StudentNotFoundException(id));
    }

    @DeleteMapping("/students/{id}")
    String deleteStudent(@PathVariable Long id){
        if (!studentRepo.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepo.deleteById(id);
        return "Student with ID "+id+" has been successfully deleted";
    }
}
