package org.example.service;

import org.example.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public Student save(Student student);

    public List<Student> findAll();

    public Optional<Student> findById(Long id);

    public void deleteById(Long id);

    public Student update(Student student);

    public Student updatePassword(Student student);

    public boolean studentLogin(String username, String password);

    public void studentLogout();
}
