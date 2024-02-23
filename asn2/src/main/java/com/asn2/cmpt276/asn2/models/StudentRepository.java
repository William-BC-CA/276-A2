package com.asn2.cmpt276.asn2.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    List<Student> findByName(String name);
    List<Student> removeStudentByName(String name);
}
