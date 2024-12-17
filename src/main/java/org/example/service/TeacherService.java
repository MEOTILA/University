package org.example.service;

import org.example.entity.Teacher;

import java.util.List;

public interface TeacherService {
    public Teacher save(Teacher teacher);
    public List<Teacher> findAll();

}
