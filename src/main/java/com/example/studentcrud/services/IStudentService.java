package com.example.studentcrud.services;

import com.example.studentcrud.models.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudents();
    Student getStudentById(int id);
    Student addStudent(Student student);
    Student updateStudent(Long studentId, Student student);
}
