package com.example.studentcrud;

import com.example.studentcrud.controllers.StudentController;
import com.example.studentcrud.dtos.StudentDTO;
import com.example.studentcrud.models.Student;
import com.example.studentcrud.services.IStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentCrudApplicationTests {

    @Autowired
    private StudentController studentcontroller;

   @MockitoBean
    private IStudentService studentService;
@Test
   public void Test_GetStudent_WithValid_Id_Runs_Successfully(){
    int studId = 2;

    //arrange
    when(studentService.getStudentById(studId)).thenReturn(new Student());

    //act
    ResponseEntity<StudentDTO> studentDTOResponseEntity =
            studentcontroller.getStudentById(studId);

    assertNotNull(studentDTOResponseEntity);
    assertNotNull(studentDTOResponseEntity.getBody());

    //assert

   }

}
