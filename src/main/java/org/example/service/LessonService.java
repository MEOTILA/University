package org.example.service;

import org.example.entity.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {
    public Lesson save(Lesson lesson);
    public List<Lesson> findAll();
    public Optional<Lesson> findById(Long id);
    public void deleteById(Long id);
    public Lesson update(Lesson lesson);
    }
