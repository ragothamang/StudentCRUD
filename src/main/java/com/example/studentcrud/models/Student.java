package com.example.studentcrud.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends BaseModel {
    private String name;
    private int age;
    private String gender;
    private String department;
}
