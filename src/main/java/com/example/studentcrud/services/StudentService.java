package com.example.studentcrud.services;

import com.example.studentcrud.clients.FakeStudentApiClient;
import com.example.studentcrud.dtos.FakeStoreStudentDto;
import com.example.studentcrud.dtos.StudentDTO;
import com.example.studentcrud.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStudentApiClient fakeStudentApiClient;


    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreStudentDto[]> response = restTemplate.getForEntity("https://freetestapi.com/api/v1/students", FakeStoreStudentDto[].class);
        if(response.getBody() == null ||
                response.getStatusCode().equals(HttpStatusCode.valueOf(500))) {
            return null;
        }
        for(FakeStoreStudentDto fakeStoreStudentDto : response.getBody()) {
            students.add(from(fakeStoreStudentDto));
        }
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        return from(fakeStudentApiClient.getStudentById(id));
    }

    @Override
    public Student addStudent(Student student) {
        // Convert the Student object to FakeStoreStudentDto for API compatibility
        FakeStoreStudentDto fakeStoreStudentDto = toDto(student);

        // Call the FakeStudentApiClient to add the student
        FakeStoreStudentDto createdStudentDto = fakeStudentApiClient.addStudent(fakeStoreStudentDto);

        // Convert the API response back to a Student object
        return from(createdStudentDto);
    }

    @Override
    public Student updateStudent(Long studentId, Student student) {
        return null;
    }

    private FakeStoreStudentDto toDto(Student student) {
        FakeStoreStudentDto fakeStoreStudentDto = new FakeStoreStudentDto();
        fakeStoreStudentDto.setName(student.getName());
        fakeStoreStudentDto.setAge(student.getAge());
        fakeStoreStudentDto.setDepartment(student.getDepartment());
        fakeStoreStudentDto.setGender(student.getGender());
        return fakeStoreStudentDto;
    }

    private Student from (FakeStoreStudentDto fakeStoreStudentDto) {
        Student student = new Student();

        StudentDTO studentDto = new StudentDTO();
//        productDto.setId(product.getId());
        student.setName(fakeStoreStudentDto.getName());
        student.setAge(fakeStoreStudentDto.getAge());
        student.setDepartment(fakeStoreStudentDto.getDepartment());
        student.setGender(fakeStoreStudentDto.getGender());
        /*if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }*/
        return student;
    }


}
