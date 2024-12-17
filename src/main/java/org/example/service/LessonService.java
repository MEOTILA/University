package org.example.service;

import org.example.entity.Lesson;

import java.util.List;

public interface LessonService {
    public Lesson save(Lesson lesson);
    public List<Lesson> findAll();

}
