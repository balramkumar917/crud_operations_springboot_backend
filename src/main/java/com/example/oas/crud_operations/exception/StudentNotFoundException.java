package com.example.oas.crud_operations.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long id){
        super("could not found the student with id "+id);
    }
}
