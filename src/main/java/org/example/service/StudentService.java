package org.example.service;

import org.example.entity.Student;

import java.util.List;

public interface StudentService {
    public Student save(Student student);
    public List<Student> findAll();
}
