package com.example.studentcrud.controllers;

import com.example.studentcrud.dtos.StudentDTO;
import com.example.studentcrud.models.Student;
import com.example.studentcrud.services.IStudentService;
import com.example.studentcrud.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/sayHello")
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping("/students")
    public List<StudentDTO> getStudents() {
        List<StudentDTO> studentDtos = new ArrayList<>();
        List<Student> response = studentService.getAllStudents();

        if(response == null) {
            return null;
        }
        for(Student student : response) {
            studentDtos.add(from(student));
        }
        return  studentDtos;
    }

    @GetMapping("/student/{Id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable ("Id") int studentId){
        Student student = studentService.getStudentById(studentId);
        StudentDTO studentDTO = from(student);

        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        // Convert the DTO to a model
        Student student = to(studentDTO);
        // Add the student using the service
        Student createdStudent = studentService.addStudent(student);
        // Convert the created student back to a DTO
        StudentDTO createdStudentDTO = from(createdStudent);
        // Return the created student DTO
        return new ResponseEntity<>(createdStudentDTO, HttpStatus.CREATED);
    }

     private StudentDTO from(Student student) {
        StudentDTO studentDTO = new StudentDTO();
         studentDTO.setName(student.getName());
         studentDTO.setAge(student.getAge());
         studentDTO.setGender(student.getGender());
         studentDTO.setDepartment(student.getDepartment());
         return studentDTO;
     }

    private Student to(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setGender(studentDTO.getGender());
        student.setDepartment(studentDTO.getDepartment());
        return student;
    }

}
