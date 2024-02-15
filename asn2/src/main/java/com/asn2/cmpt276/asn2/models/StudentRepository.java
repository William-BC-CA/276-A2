package com.asn2.cmpt276.asn2.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Double>{
    List<Student> findByNameAndPassword(String name, String password);
}
