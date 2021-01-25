package com.test.service.impl;

import com.test.dao.StudentDao;
import com.test.domain.Student;
import com.test.service.StudentService;
import com.test.util.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao dao;

    @Override
    public int add(Student stu) {
        stu.setId(MyUUID.get());
        return dao.add(stu);
    }

    @Override
    public List<Student> showAll() {
        return dao.showAll();
    }

    @Override
    public int delete(String[] data) {
        return dao.delete(data);
    }

    @Override
    public Student edit(String id) {
        return dao.edit(id);
    }

    @Override
    public int update(Student stu) {
        return dao.update(stu);
    }

    @Override
    public List<Student> one(String name, Integer age) {
        return dao.one(name,age);
    }

    @Override
    public List<Student> limit(Integer i,Integer pageSize, String name, Integer age) {
        return dao.limit(i,pageSize,name,age);
    }
}
