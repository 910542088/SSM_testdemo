package com.test.dao;

import com.test.domain.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import java.util.List;

public interface StudentDao {

    int add(Student stu);

    List<Student> showAll();

    int delete(String[] data);

    Student edit(String id);

    int update(Student stu);

    List<Student> one(@Param("name")String name,@Param("age") Integer age);

    List<Student> limit(@Param("i")  Integer i,
                        @Param("pageSize")  Integer pageSize,
                        @Param("name") @Nullable String name,
                        @Param("age") @Nullable Integer age);
}
