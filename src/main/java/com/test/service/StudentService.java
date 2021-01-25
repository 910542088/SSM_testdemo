package com.test.service;

import com.test.domain.Student;
import org.springframework.lang.Nullable;

import java.util.List;

public interface StudentService {
    int add(Student stu);

    List<Student> showAll();

    int delete(String[] data);

    Student edit(String id);

    int update(Student stu);

    List<Student> one(String name, Integer age);

    List<Student> limit( Integer i, Integer pageSize, String name, Integer age);

}
