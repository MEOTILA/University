package org.example.service;

import org.example.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    public Teacher save(Teacher teacher);
    public List<Teacher> findAll();
    public Optional<Teacher> findById(Long id);
    public void deleteById(Long id);
    public Teacher update(Teacher teacher);
    }
